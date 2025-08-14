package com.automationframework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Abstract base class for all page objects
 * Implements common functionality and follows DRY principles
 */
public abstract class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor jsExecutor;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Waits for element to be visible and returns it
     * @param element The WebElement to wait for
     * @return The visible WebElement
     * @throws WebExceptions if element is not found or visible
     */
    public WebElement findElement(WebElement element) throws WebExceptions {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.ELEMENT_NOT_FOUND,
                    "Element not found or not visible: " + element);
        }
    }
    
    /**
     * Waits for element to be clickable and returns it
     * @param element The WebElement to wait for
     * @return The clickable WebElement
     * @throws WebExceptions if element is not clickable
     */
    public WebElement findClickableElement(WebElement element) throws WebExceptions {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.ELEMENT_NOT_CLICKABLE,
                    "Element not clickable: " + element);
        }
    }
    
    /**
     * Clicks on an element after ensuring it's clickable
     * @param element The WebElement to click
     * @throws WebExceptions if element cannot be clicked
     */
    public void clickElement(WebElement element) throws WebExceptions {
        try {
            WebElement clickableElement = findClickableElement(element);
            clickableElement.click();
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.ELEMENT_NOT_CLICKABLE,
                    "Failed to click element: " + element);
        }
    }
    
    /**
     * Enters text into an element after ensuring it's visible
     * @param element The WebElement to enter text into
     * @param text The text to enter
     * @throws WebExceptions if text cannot be entered
     */
    public void enterText(WebElement element, String text) throws WebExceptions {
        try {
            WebElement visibleElement = findElement(element);
            visibleElement.clear();
            visibleElement.sendKeys(text);
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.ELEMENT_NOT_FOUND,
                    "Failed to enter text in element: " + element);
        }
    }
    
    /**
     * Gets text from an element after ensuring it's visible
     * @param element The WebElement to get text from
     * @return The text content of the element
     * @throws WebExceptions if text cannot be retrieved
     */
    public String getElementText(WebElement element) throws WebExceptions {
        try {
            WebElement visibleElement = findElement(element);
            return visibleElement.getText();
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.ELEMENT_NOT_FOUND,
                    "Failed to get text from element: " + element);
        }
    }
    
    /**
     * Checks if an element is visible
     * @param element The WebElement to check
     * @return true if element is visible, false otherwise
     */
    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Waits for element to be visible by locator
     * @param locator The By locator
     * @return The visible WebElement
     * @throws WebExceptions if element is not found or visible
     */
    public WebElement findElementByLocator(By locator) throws WebExceptions {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.ELEMENT_NOT_FOUND,
                    "Element not found or not visible by locator: " + locator);
        }
    }
    
    /**
     * Scrolls to an element using JavaScript
     * @param element The WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.err.println("Failed to scroll to element: " + e.getMessage());
        }
    }
    
    /**
     * Performs a double-click on an element
     * @param element The WebElement to double-click
     * @throws WebExceptions if double-click fails
     */
    public void doubleClickElement(WebElement element) throws WebExceptions {
        try {
            WebElement clickableElement = findClickableElement(element);
            actions.doubleClick(clickableElement).perform();
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.ELEMENT_NOT_CLICKABLE,
                    "Failed to double-click element: " + element);
        }
    }
    
    /**
     * Waits for page to load completely
     */
    public void waitForPageLoad() {
        try {
            wait.until(webDriver -> jsExecutor.executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            System.err.println("Page load wait failed: " + e.getMessage());
        }
    }
    
    /**
     * Gets the current page title
     * @return The page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Gets the current page URL
     * @return The current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
