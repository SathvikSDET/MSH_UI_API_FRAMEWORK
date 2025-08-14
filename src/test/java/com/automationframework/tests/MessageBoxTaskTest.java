package com.automationframework.tests;

import com.automationframework.core.BaseTest;
import com.automationframework.core.WebExceptions;
import com.automationframework.pages.*;
import com.automationframework.utils.TestDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Message Box Task automation
 * Implements the complete test flow with assertions
 */
public class MessageBoxTaskTest extends BaseTest {
    
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AutomationPage automationPage;
    private TaskBotPage taskBotPage;
    private TaskBotActionsPage taskBotActionsPage;
    
    @Override
    protected void navigateToApplication() {
        driver.get(TestDataProvider.LOGIN_URL);
    }
    
    /**
     * Test Case: Complete Message Box Task Creation Flow
     * Steps:
     * 1. Log in to the application
     * 2. Navigate to Automation from the left-hand menu
     * 3. Click on the Create dropdown and select Task Bot
     * 4. Fill in all mandatory details and click the Create button
     * 5. In the Actions panel, search for Message Box and double-click to add it
     * 6. On the right panel, verify every UI element interaction
     * 7. Save the configuration
     */
    @Test(description = "Complete Message Box Task Creation Flow")
    public void testMessageBoxTaskCreation() {
        try {
            // Step 1: Log in to the application
            performLogin();
            
            // Step 2: Navigate to Automation from the left-hand menu
            navigateToAutomation();
            
            // Step 3: Click on the Create dropdown and select Task Bot
            navigateToTaskBot();
            
            // Step 4: Fill in all mandatory details and click the Create button
            createTaskBot();
            
            // Step 5: In the Actions panel, search for Message Box and double-click to add it
            addMessageBoxAction();
            
            // Step 6: On the right panel, verify every UI element interaction
            verifyUIElements();
            
            // Step 7: Save the configuration
            saveConfiguration();
            
            // Final validation
            validateSuccessfulCreation();
            
        } catch (WebExceptions e) {
            Assert.fail("Test failed due to WebExceptions: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: UI Element Visibility Validation
     * Validates that all required UI elements are visible at each step
     */
    @Test(description = "UI Element Visibility Validation")
    public void testUIElementVisibility() {
        try {
            // Login page elements
            performLogin();
            Assert.assertTrue(loginPage.areAllElementsVisible(), "Login page elements should be visible");
            
            // Dashboard elements
            navigateToAutomation();
            Assert.assertTrue(dashboardPage.areAllElementsVisible(), "Dashboard elements should be visible");
            
            // Automation page elements
            Assert.assertTrue(automationPage.areAllElementsVisible(), "Automation page elements should be visible");
            
            // Task Bot creation form elements
            navigateToTaskBot();
            Assert.assertTrue(taskBotPage.areAllFormElementsVisible(), "Task Bot form elements should be visible");
            
            // Actions panel elements
            createTaskBot();
            Assert.assertTrue(taskBotActionsPage.isActionsPanelVisible(), "Actions panel should be visible");
            
            // Configuration elements
            addMessageBoxAction();
            Assert.assertTrue(taskBotActionsPage.isRightPanelVisible(), "Right panel should be visible");
            Assert.assertTrue(taskBotActionsPage.areAllConfigElementsVisible(), "Configuration elements should be visible");
            
        } catch (WebExceptions e) {
            Assert.fail("UI Element Visibility test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Functional Flow Validation
     * Validates the complete functional flow and expected behaviors
     */
    @Test(description = "Functional Flow Validation")
    public void testFunctionalFlow() {
        try {
            // Complete flow execution
            performLogin();
            navigateToAutomation();
            navigateToTaskBot();
            createTaskBot();
            addMessageBoxAction();
            verifyUIElements();
            saveConfiguration();
            
            // Validate functional flow
            validateFunctionalFlow();
            
        } catch (WebExceptions e) {
            Assert.fail("Functional Flow test failed: " + e.getMessage());
        }
    }
    
    /**
     * Performs login with valid credentials
     * @throws WebExceptions if login fails
     */
    private void performLogin() throws WebExceptions {
        loginPage = new LoginPage(driver);
        
        // Verify login page elements are visible
        Assert.assertTrue(loginPage.areAllElementsVisible(), "Login page elements should be visible");
        
        // Perform login
        loginPage.login(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);
        
        // Verify successful login
        Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "Error message should not be displayed after valid login");
    }
    
    /**
     * Navigates to the Automation section
     * @throws WebExceptions if navigation fails
     */
    private void navigateToAutomation() throws WebExceptions {
        dashboardPage = new DashboardPage(driver);
        
        // Verify dashboard elements are visible
        Assert.assertTrue(dashboardPage.areAllElementsVisible(), "Dashboard elements should be visible");
        
        // Navigate to Automation
        automationPage = dashboardPage.navigateToAutomation();
        
        // Verify navigation to Automation page
        Assert.assertTrue(automationPage.isOnAutomationPage(), "Should be on Automation page");
        Assert.assertTrue(automationPage.areAllElementsVisible(), "Automation page elements should be visible");
    }
    
    /**
     * Navigates to Task Bot creation
     * @throws WebExceptions if navigation fails
     */
    private void navigateToTaskBot() throws WebExceptions {
        // Navigate to Task Bot
        taskBotPage = automationPage.navigateToTaskBot();
        
        // Verify Task Bot page elements are visible
        Assert.assertTrue(taskBotPage.areAllFormElementsVisible(), "Task Bot form elements should be visible");
    }
    
    /**
     * Creates a Task Bot with mandatory details
     * @throws WebExceptions if Task Bot creation fails
     */
    private void createTaskBot() throws WebExceptions {
        // Create Task Bot with mandatory details
        taskBotActionsPage = taskBotPage.createTaskBot(
            TestDataProvider.TASK_NAME,
            TestDataProvider.TASK_DESCRIPTION,
            TestDataProvider.TASK_CATEGORY,
            TestDataProvider.TASK_PRIORITY,
            TestDataProvider.TASK_ASSIGNEE
        );
        
        // Verify Task Bot creation success
        Assert.assertTrue(taskBotPage.isSuccessMessageDisplayed(), "Success message should be displayed after Task Bot creation");
    }
    
    /**
     * Adds Message Box action to the Task Bot
     * @throws WebExceptions if Message Box action addition fails
     */
    private void addMessageBoxAction() throws WebExceptions {
        // Verify actions panel is visible
        Assert.assertTrue(taskBotActionsPage.isActionsPanelVisible(), "Actions panel should be visible");
        
        // Verify Message Box action is available
        Assert.assertTrue(taskBotActionsPage.isMessageBoxActionAvailable(), "Message Box action should be available");
        
        // Search for Message Box action
        taskBotActionsPage.searchForMessageBox();
        
        // Add Message Box action
        taskBotActionsPage.addMessageBoxAction();
        
        // Verify right panel is visible after adding action
        Assert.assertTrue(taskBotActionsPage.isRightPanelVisible(), "Right panel should be visible after adding Message Box action");
    }
    
    /**
     * Verifies all UI elements in the configuration panel
     * @throws WebExceptions if verification fails
     */
    private void verifyUIElements() throws WebExceptions {
        // Verify all configuration elements are visible
        Assert.assertTrue(taskBotActionsPage.areAllConfigElementsVisible(), "All configuration elements should be visible");
        
        // Verify configuration title
        String configTitle = taskBotActionsPage.getConfigTitle();
        Assert.assertNotNull(configTitle, "Configuration title should not be null");
        Assert.assertFalse(configTitle.isEmpty(), "Configuration title should not be empty");
    }
    
    /**
     * Saves the Message Box configuration
     * @throws WebExceptions if save operation fails
     */
    private void saveConfiguration() throws WebExceptions {
        // Configure Message Box with test data
        taskBotActionsPage.configureMessageBox(
            TestDataProvider.MESSAGE_TEXT,
            TestDataProvider.MESSAGE_TYPE,
            TestDataProvider.MESSAGE_DURATION
        );
        
        // Save configuration
        taskBotActionsPage.clickSaveConfigButton();
        
        // Verify save success
        Assert.assertTrue(taskBotActionsPage.isSaveSuccessMessageDisplayed(), "Save success message should be displayed");
    }
    
    /**
     * Validates successful creation and configuration
     * @throws WebExceptions if validation fails
     */
    private void validateSuccessfulCreation() throws WebExceptions {
        // Verify save success message
        String successMessage = taskBotActionsPage.getSaveSuccessMessage();
        Assert.assertNotNull(successMessage, "Success message should not be null");
        Assert.assertFalse(successMessage.isEmpty(), "Success message should not be empty");
        
        // Verify all panels are still visible
        Assert.assertTrue(taskBotActionsPage.isActionsPanelVisible(), "Actions panel should remain visible");
        Assert.assertTrue(taskBotActionsPage.isRightPanelVisible(), "Right panel should remain visible");
    }
    
    /**
     * Validates the complete functional flow
     * @throws WebExceptions if validation fails
     */
    private void validateFunctionalFlow() throws WebExceptions {
        // Verify complete flow success
        Assert.assertTrue(taskBotActionsPage.isSaveSuccessMessageDisplayed(), "Configuration should be saved successfully");
        
        // Verify all UI elements remain functional
        Assert.assertTrue(taskBotActionsPage.areAllConfigElementsVisible(), "Configuration elements should remain visible");
        Assert.assertTrue(taskBotActionsPage.isMessageBoxActionAvailable(), "Message Box action should remain available");
    }
}
