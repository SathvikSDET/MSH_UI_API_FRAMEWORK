package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Task Bot Actions functionality
 * Contains elements for managing actions like Message Box and configuration
 */
public class TaskBotActionsPage extends BasePage {
    
    // Actions panel elements
    @FindBy(className = "actions-panel")
    private WebElement actionsPanel;
    
    @FindBy(id = "action-search")
    private WebElement actionSearchField;
    
    @FindBy(xpath = "//div[contains(text(),'Message Box')]")
    private WebElement messageBoxAction;
    
    @FindBy(className = "action-list")
    private WebElement actionList;
    
    // Right panel configuration elements
    @FindBy(className = "right-panel")
    private WebElement rightPanel;
    
    @FindBy(className = "message-box-config")
    private WebElement messageBoxConfig;
    
    @FindBy(id = "message-text")
    private WebElement messageTextField;
    
    @FindBy(id = "message-type")
    private WebElement messageTypeDropdown;
    
    @FindBy(id = "message-duration")
    private WebElement messageDurationField;
    
    @FindBy(id = "save-config-button")
    private WebElement saveConfigButton;
    
    @FindBy(className = "cancel-config-button")
    private WebElement cancelConfigButton;
    
    @FindBy(className = "config-title")
    private WebElement configTitle;
    
    @FindBy(className = "save-success-message")
    private WebElement saveSuccessMessage;
    
    public TaskBotActionsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Searches for an action in the actions panel
     * @param actionName The name of the action to search for
     * @throws WebExceptions if search fails
     */
    public void searchForAction(String actionName) throws WebExceptions {
        enterText(actionSearchField, actionName);
    }
    
    /**
     * Searches for Message Box action
     * @throws WebExceptions if search fails
     */
    public void searchForMessageBox() throws WebExceptions {
        searchForAction("Message Box");
    }
    
    /**
     * Double-clicks on the Message Box action to add it
     * @throws WebExceptions if double-click fails
     */
    public void addMessageBoxAction() throws WebExceptions {
        doubleClickElement(messageBoxAction);
        waitForPageLoad();
    }
    
    /**
     * Enters message text in the configuration
     * @param messageText The text of the message
     * @throws WebExceptions if message text cannot be entered
     */
    public void enterMessageText(String messageText) throws WebExceptions {
        enterText(messageTextField, messageText);
    }
    
    /**
     * Selects message type
     * @param messageType The type of message to select
     * @throws WebExceptions if message type cannot be selected
     */
    public void selectMessageType(String messageType) throws WebExceptions {
        clickElement(messageTypeDropdown);
        // Additional logic for selecting specific message type
    }
    
    /**
     * Enters message duration
     * @param duration The duration of the message
     * @throws WebExceptions if duration cannot be entered
     */
    public void enterMessageDuration(String duration) throws WebExceptions {
        enterText(messageDurationField, duration);
    }
    
    /**
     * Clicks the Save Configuration button
     * @throws WebExceptions if save button cannot be clicked
     */
    public void clickSaveConfigButton() throws WebExceptions {
        clickElement(saveConfigButton);
        waitForPageLoad();
    }
    
    /**
     * Clicks the Cancel Configuration button
     * @throws WebExceptions if cancel button cannot be clicked
     */
    public void clickCancelConfigButton() throws WebExceptions {
        clickElement(cancelConfigButton);
    }
    
    /**
     * Configures Message Box with all details
     * @param messageText The text of the message
     * @param messageType The type of message
     * @param duration The duration of the message
     * @throws WebExceptions if configuration fails
     */
    public void configureMessageBox(String messageText, String messageType, String duration) throws WebExceptions {
        enterMessageText(messageText);
        selectMessageType(messageType);
        enterMessageDuration(duration);
    }
    
    /**
     * Completes the Message Box configuration and saves it
     * @param messageText The text of the message
     * @param messageType The type of message
     * @param duration The duration of the message
     * @throws WebExceptions if save operation fails
     */
    public void completeMessageBoxConfiguration(String messageText, String messageType, String duration) throws WebExceptions {
        configureMessageBox(messageText, messageType, duration);
        clickSaveConfigButton();
    }
    
    /**
     * Gets the configuration title
     * @return The configuration title text
     * @throws WebExceptions if title cannot be retrieved
     */
    public String getConfigTitle() throws WebExceptions {
        return getElementText(configTitle);
    }
    
    /**
     * Checks if save success message is displayed
     * @return true if success message is visible, false otherwise
     */
    public boolean isSaveSuccessMessageDisplayed() {
        return isElementVisible(saveSuccessMessage);
    }
    
    /**
     * Gets the save success message
     * @return The success message text
     * @throws WebExceptions if success message cannot be retrieved
     */
    public String getSaveSuccessMessage() throws WebExceptions {
        return getElementText(saveSuccessMessage);
    }
    
    /**
     * Verifies that actions panel is visible
     * @return true if actions panel is visible, false otherwise
     */
    public boolean isActionsPanelVisible() {
        return isElementVisible(actionsPanel) && isElementVisible(actionSearchField);
    }
    
    /**
     * Verifies that right panel is visible
     * @return true if right panel is visible, false otherwise
     */
    public boolean isRightPanelVisible() {
        return isElementVisible(rightPanel) && isElementVisible(messageBoxConfig);
    }
    
    /**
     * Verifies that all configuration elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllConfigElementsVisible() {
        return isElementVisible(messageTextField) && 
               isElementVisible(messageTypeDropdown) && 
               isElementVisible(messageDurationField) && 
               isElementVisible(saveConfigButton);
    }
    
    /**
     * Verifies that Message Box action is available
     * @return true if Message Box action is visible, false otherwise
     */
    public boolean isMessageBoxActionAvailable() {
        return isElementVisible(messageBoxAction);
    }
}
