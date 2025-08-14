package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Dashboard functionality
 * Contains navigation elements and dashboard actions
 */
public class DashboardPage extends BasePage {
    
    // Navigation elements
    @FindBy(xpath = "//a[contains(text(),'Automation')]")
    private WebElement automationMenu;
    
    @FindBy(className = "user-profile")
    private WebElement userProfile;
    
    @FindBy(className = "logout-button")
    private WebElement logoutButton;
    
    @FindBy(className = "dashboard-title")
    private WebElement dashboardTitle;
    
    @FindBy(className = "welcome-message")
    private WebElement welcomeMessage;
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Clicks on the Automation menu item
     * @throws WebExceptions if Automation menu cannot be clicked
     */
    public void clickAutomationMenu() throws WebExceptions {
        clickElement(automationMenu);
        waitForPageLoad();
    }
    
    /**
     * Navigates to the Automation section
     * @return AutomationPage instance
     * @throws WebExceptions if navigation fails
     */
    public AutomationPage navigateToAutomation() throws WebExceptions {
        clickAutomationMenu();
        return new AutomationPage(driver);
    }
    
    /**
     * Clicks on the user profile
     * @throws WebExceptions if user profile cannot be clicked
     */
    public void clickUserProfile() throws WebExceptions {
        clickElement(userProfile);
    }
    
    /**
     * Clicks on the logout button
     * @throws WebExceptions if logout button cannot be clicked
     */
    public void clickLogoutButton() throws WebExceptions {
        clickElement(logoutButton);
    }
    
    /**
     * Performs logout action
     * @throws WebExceptions if logout fails
     */
    public void logout() throws WebExceptions {
        clickUserProfile();
        clickLogoutButton();
        waitForPageLoad();
    }
    
    /**
     * Gets the dashboard title
     * @return The dashboard title text
     * @throws WebExceptions if title cannot be retrieved
     */
    public String getDashboardTitle() throws WebExceptions {
        return getElementText(dashboardTitle);
    }
    
    /**
     * Gets the welcome message
     * @return The welcome message text
     * @throws WebExceptions if message cannot be retrieved
     */
    public String getWelcomeMessage() throws WebExceptions {
        return getElementText(welcomeMessage);
    }
    
    /**
     * Verifies that user is on dashboard page
     * @return true if on dashboard, false otherwise
     */
    public boolean isOnDashboard() {
        return isElementVisible(dashboardTitle) && isElementVisible(automationMenu);
    }
    
    /**
     * Verifies that all dashboard elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllElementsVisible() {
        return isElementVisible(automationMenu) && 
               isElementVisible(userProfile) && 
               isElementVisible(dashboardTitle);
    }
}
