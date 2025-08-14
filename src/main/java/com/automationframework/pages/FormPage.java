package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Form creation functionality
 * Contains elements for creating forms with mandatory details
 */
public class FormPage extends BasePage {
    
    // Form creation form elements
    @FindBy(id = "form-name")
    private WebElement formNameField;
    
    @FindBy(id = "form-description")
    private WebElement formDescriptionField;
    
    @FindBy(id = "form-category")
    private WebElement formCategoryDropdown;
    
    @FindBy(id = "form-type")
    private WebElement formTypeDropdown;
    
    @FindBy(id = "form-access")
    private WebElement formAccessDropdown;
    
    @FindBy(id = "create-form-button")
    private WebElement createFormButton;
    
    @FindBy(className = "cancel-form-button")
    private WebElement cancelFormButton;
    
    @FindBy(className = "form-creation-title")
    private WebElement formCreationTitle;
    
    @FindBy(className = "validation-error")
    private WebElement validationError;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    public FormPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enters form name
     * @param formName The name of the form
     * @throws WebExceptions if form name cannot be entered
     */
    public void enterFormName(String formName) throws WebExceptions {
        enterText(formNameField, formName);
    }
    
    /**
     * Enters form description
     * @param description The description of the form
     * @throws WebExceptions if form description cannot be entered
     */
    public void enterFormDescription(String description) throws WebExceptions {
        enterText(formDescriptionField, description);
    }
    
    /**
     * Selects form category
     * @param category The category to select
     * @throws WebExceptions if category cannot be selected
     */
    public void selectFormCategory(String category) throws WebExceptions {
        clickElement(formCategoryDropdown);
        // Additional logic for selecting specific category
    }
    
    /**
     * Selects form type
     * @param formType The form type to select
     * @throws WebExceptions if form type cannot be selected
     */
    public void selectFormType(String formType) throws WebExceptions {
        clickElement(formTypeDropdown);
        // Additional logic for selecting specific form type
    }
    
    /**
     * Selects form access level
     * @param accessLevel The access level to select
     * @throws WebExceptions if access level cannot be selected
     */
    public void selectFormAccess(String accessLevel) throws WebExceptions {
        clickElement(formAccessDropdown);
        // Additional logic for selecting specific access level
    }
    
    /**
     * Clicks the Create Form button
     * @throws WebExceptions if Create Form button cannot be clicked
     */
    public void clickCreateFormButton() throws WebExceptions {
        clickElement(createFormButton);
        waitForPageLoad();
    }
    
    /**
     * Clicks the Cancel Form button
     * @throws WebExceptions if Cancel Form button cannot be clicked
     */
    public void clickCancelFormButton() throws WebExceptions {
        clickElement(cancelFormButton);
    }
    
    /**
     * Fills all mandatory form details
     * @param formName The name of the form
     * @param description The description of the form
     * @param category The category of the form
     * @param formType The type of the form
     * @param accessLevel The access level of the form
     * @throws WebExceptions if any field cannot be filled
     */
    public void fillMandatoryDetails(String formName, String description, String category, 
                                   String formType, String accessLevel) throws WebExceptions {
        enterFormName(formName);
        enterFormDescription(description);
        selectFormCategory(category);
        selectFormType(formType);
        selectFormAccess(accessLevel);
    }
    
    /**
     * Creates a form with all mandatory details
     * @param formName The name of the form
     * @param description The description of the form
     * @param category The category of the form
     * @param formType The type of the form
     * @param accessLevel The access level of the form
     * @return FormDesignerPage instance
     * @throws WebExceptions if form creation fails
     */
    public FormDesignerPage createForm(String formName, String description, String category, 
                                     String formType, String accessLevel) throws WebExceptions {
        fillMandatoryDetails(formName, description, category, formType, accessLevel);
        clickCreateFormButton();
        return new FormDesignerPage(driver);
    }
    
    /**
     * Gets the form creation title
     * @return The form creation title text
     * @throws WebExceptions if title cannot be retrieved
     */
    public String getFormCreationTitle() throws WebExceptions {
        return getElementText(formCreationTitle);
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
     * Verifies that all form creation elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllFormElementsVisible() {
        return isElementVisible(formNameField) && 
               isElementVisible(formDescriptionField) && 
               isElementVisible(formCategoryDropdown) && 
               isElementVisible(formTypeDropdown) && 
               isElementVisible(formAccessDropdown) && 
               isElementVisible(createFormButton);
    }
}
