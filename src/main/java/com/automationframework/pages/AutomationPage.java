package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Automation functionality
 * Contains elements for creating and managing automation tasks
 */
public class AutomationPage extends BasePage {
    
    // Create dropdown elements
    @FindBy(className = "create-dropdown")
    private WebElement createDropdown;
    
    @FindBy(xpath = "//a[contains(text(),'Task Bot')]")
    private WebElement taskBotOption;
    
    @FindBy(xpath = "//a[contains(text(),'Form')]")
    private WebElement formOption;
    
    @FindBy(className = "automation-title")
    private WebElement automationTitle;
    
    @FindBy(className = "task-list")
    private WebElement taskList;
    
    @FindBy(className = "create-new-button")
    private WebElement createNewButton;
    
    public AutomationPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Clicks on the Create dropdown
     * @throws WebExceptions if Create dropdown cannot be clicked
     */
    public void clickCreateDropdown() throws WebExceptions {
        clickElement(createDropdown);
    }
    
    /**
     * Selects Task Bot from the Create dropdown
     * @throws WebExceptions if Task Bot option cannot be selected
     */
    public void selectTaskBot() throws WebExceptions {
        clickCreateDropdown();
        clickElement(taskBotOption);
        waitForPageLoad();
    }
    
    /**
     * Navigates to Task Bot creation
     * @return TaskBotPage instance
     * @throws WebExceptions if navigation fails
     */
    public TaskBotPage navigateToTaskBot() throws WebExceptions {
        selectTaskBot();
        return new TaskBotPage(driver);
    }
    
    /**
     * Selects Form from the Create dropdown
     * @throws WebExceptions if Form option cannot be selected
     */
    public void selectForm() throws WebExceptions {
        clickCreateDropdown();
        clickElement(formOption);
        waitForPageLoad();
    }
    
    /**
     * Navigates to Form creation
     * @return FormPage instance
     * @throws WebExceptions if navigation fails
     */
    public FormPage navigateToForm() throws WebExceptions {
        selectForm();
        return new FormPage(driver);
    }
    
    /**
     * Gets the automation page title
     * @return The automation title text
     * @throws WebExceptions if title cannot be retrieved
     */
    public String getAutomationTitle() throws WebExceptions {
        return getElementText(automationTitle);
    }
    
    /**
     * Clicks on the Create New button
     * @throws WebExceptions if Create New button cannot be clicked
     */
    public void clickCreateNewButton() throws WebExceptions {
        clickElement(createNewButton);
    }
    
    /**
     * Verifies that user is on automation page
     * @return true if on automation page, false otherwise
     */
    public boolean isOnAutomationPage() {
        return isElementVisible(automationTitle) && isElementVisible(createDropdown);
    }
    
    /**
     * Verifies that all automation page elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllElementsVisible() {
        return isElementVisible(createDropdown) && 
               isElementVisible(automationTitle) && 
               isElementVisible(createNewButton);
    }
}
