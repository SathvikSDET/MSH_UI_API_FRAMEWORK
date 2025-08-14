package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Login functionality
 * Contains all login-related elements and actions
 */
public class LoginPage extends BasePage {
    
    // Page elements using @FindBy annotations
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enters username in the username field
     * @param username The username to enter
     * @throws WebExceptions if username cannot be entered
     */
    public void enterUsername(String username) throws WebExceptions {
        enterText(usernameField, username);
    }
    
    /**
     * Enters password in the password field
     * @param password The password to enter
     * @throws WebExceptions if password cannot be entered
     */
    public void enterPassword(String password) throws WebExceptions {
        enterText(passwordField, password);
    }
    
    /**
     * Clicks the login button
     * @throws WebExceptions if login button cannot be clicked
     */
    public void clickLoginButton() throws WebExceptions {
        clickElement(loginButton);
    }
    
    /**
     * Performs complete login action
     * @param username The username to enter
     * @param password The password to enter
     * @throws WebExceptions if login fails
     */
    public void login(String username, String password) throws WebExceptions {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        waitForPageLoad();
    }
    
    /**
     * Checks if error message is displayed
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(errorMessage);
    }
    
    /**
     * Gets the error message text
     * @return The error message text
     * @throws WebExceptions if error message cannot be retrieved
     */
    public String getErrorMessage() throws WebExceptions {
        return getElementText(errorMessage);
    }
    
    /**
     * Checks if success message is displayed
     * @return true if success message is visible, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementVisible(successMessage);
    }
    
    /**
     * Gets the success message text
     * @return The success message text
     * @throws WebExceptions if success message cannot be retrieved
     */
    public String getSuccessMessage() throws WebExceptions {
        return getElementText(successMessage);
    }
    
    /**
     * Verifies that all login page elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllElementsVisible() {
        return isElementVisible(usernameField) && 
               isElementVisible(passwordField) && 
               isElementVisible(loginButton);
    }
}
