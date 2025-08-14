package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Task Bot creation functionality
 * Contains elements for creating Task Bots with mandatory details
 */
public class TaskBotPage extends BasePage {
    
    // Task Bot creation form elements
    @FindBy(id = "task-name")
    private WebElement taskNameField;
    
    @FindBy(id = "task-description")
    private WebElement taskDescriptionField;
    
    @FindBy(id = "task-category")
    private WebElement taskCategoryDropdown;
    
    @FindBy(id = "task-priority")
    private WebElement taskPriorityDropdown;
    
    @FindBy(id = "task-assignee")
    private WebElement taskAssigneeField;
    
    @FindBy(id = "create-button")
    private WebElement createButton;
    
    @FindBy(className = "cancel-button")
    private WebElement cancelButton;
    
    @FindBy(className = "form-title")
    private WebElement formTitle;
    
    @FindBy(className = "validation-error")
    private WebElement validationError;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    public TaskBotPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enters task name
     * @param taskName The name of the task
     * @throws WebExceptions if task name cannot be entered
     */
    public void enterTaskName(String taskName) throws WebExceptions {
        enterText(taskNameField, taskName);
    }
    
    /**
     * Enters task description
     * @param description The description of the task
     * @throws WebExceptions if task description cannot be entered
     */
    public void enterTaskDescription(String description) throws WebExceptions {
        enterText(taskDescriptionField, description);
    }
    
    /**
     * Selects task category
     * @param category The category to select
     * @throws WebExceptions if category cannot be selected
     */
    public void selectTaskCategory(String category) throws WebExceptions {
        // Implementation for dropdown selection
        clickElement(taskCategoryDropdown);
        // Additional logic for selecting specific category
    }
    
    /**
     * Selects task priority
     * @param priority The priority to select
     * @throws WebExceptions if priority cannot be selected
     */
    public void selectTaskPriority(String priority) throws WebExceptions {
        // Implementation for dropdown selection
        clickElement(taskPriorityDropdown);
        // Additional logic for selecting specific priority
    }
    
    /**
     * Enters task assignee
     * @param assignee The assignee name
     * @throws WebExceptions if assignee cannot be entered
     */
    public void enterTaskAssignee(String assignee) throws WebExceptions {
        enterText(taskAssigneeField, assignee);
    }
    
    /**
     * Clicks the Create button
     * @throws WebExceptions if Create button cannot be clicked
     */
    public void clickCreateButton() throws WebExceptions {
        clickElement(createButton);
        waitForPageLoad();
    }
    
    /**
     * Clicks the Cancel button
     * @throws WebExceptions if Cancel button cannot be clicked
     */
    public void clickCancelButton() throws WebExceptions {
        clickElement(cancelButton);
    }
    
    /**
     * Fills all mandatory task details
     * @param taskName The name of the task
     * @param description The description of the task
     * @param category The category of the task
     * @param priority The priority of the task
     * @param assignee The assignee of the task
     * @throws WebExceptions if any field cannot be filled
     */
    public void fillMandatoryDetails(String taskName, String description, String category, 
                                   String priority, String assignee) throws WebExceptions {
        enterTaskName(taskName);
        enterTaskDescription(description);
        selectTaskCategory(category);
        selectTaskPriority(priority);
        enterTaskAssignee(assignee);
    }
    
    /**
     * Creates a task bot with all mandatory details
     * @param taskName The name of the task
     * @param description The description of the task
     * @param category The category of the task
     * @param priority The priority of the task
     * @param assignee The assignee of the task
     * @return TaskBotActionsPage instance
     * @throws WebExceptions if task creation fails
     */
    public TaskBotActionsPage createTaskBot(String taskName, String description, String category, 
                                          String priority, String assignee) throws WebExceptions {
        fillMandatoryDetails(taskName, description, category, priority, assignee);
        clickCreateButton();
        return new TaskBotActionsPage(driver);
    }
    
    /**
     * Gets the form title
     * @return The form title text
     * @throws WebExceptions if title cannot be retrieved
     */
    public String getFormTitle() throws WebExceptions {
        return getElementText(formTitle);
    }
    
    /**
     * Checks if validation error is displayed
     * @return true if validation error is visible, false otherwise
     */
    public boolean isValidationErrorDisplayed() {
        return isElementVisible(validationError);
    }
    
    /**
     * Gets the validation error message
     * @return The validation error text
     * @throws WebExceptions if error message cannot be retrieved
     */
    public String getValidationErrorMessage() throws WebExceptions {
        return getElementText(validationError);
    }
    
    /**
     * Checks if success message is displayed
     * @return true if success message is visible, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementVisible(successMessage);
    }
    
    /**
     * Gets the success message
     * @return The success message text
     * @throws WebExceptions if success message cannot be retrieved
     */
    public String getSuccessMessage() throws WebExceptions {
        return getElementText(successMessage);
    }
    
    /**
     * Verifies that all form elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllFormElementsVisible() {
        return isElementVisible(taskNameField) && 
               isElementVisible(taskDescriptionField) && 
               isElementVisible(taskCategoryDropdown) && 
               isElementVisible(taskPriorityDropdown) && 
               isElementVisible(taskAssigneeField) && 
               isElementVisible(createButton);
    }
}
