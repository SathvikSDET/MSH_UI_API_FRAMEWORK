package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Form Preview and Submission functionality
 * Contains elements for form preview, text input, file upload, and submission
 */
public class FormPreviewPage extends BasePage {
    
    // Form preview elements
    @FindBy(className = "form-preview-container")
    private WebElement formPreviewContainer;
    
    @FindBy(className = "form-preview-title")
    private WebElement formPreviewTitle;
    
    // Form input elements
    @FindBy(id = "textbox-input")
    private WebElement textboxInput;
    
    @FindBy(id = "file-upload-input")
    private WebElement fileUploadInput;
    
    @FindBy(className = "file-upload-button")
    private WebElement fileUploadButton;
    
    @FindBy(className = "file-name-display")
    private WebElement fileNameDisplay;
    
    // Form submission elements
    @FindBy(id = "submit-form-button")
    private WebElement submitFormButton;
    
    @FindBy(id = "reset-form-button")
    private WebElement resetFormButton;
    
    // Response and status elements
    @FindBy(className = "upload-status")
    private WebElement uploadStatus;
    
    @FindBy(className = "upload-progress")
    private WebElement uploadProgress;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    @FindBy(className = "form-submission-response")
    private WebElement formSubmissionResponse;
    
    public FormPreviewPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enters text in the textbox input field
     * @param text The text to enter
     * @throws WebExceptions if text cannot be entered
     */
    public void enterTextInTextbox(String text) throws WebExceptions {
        enterText(textboxInput, text);
    }
    
    /**
     * Gets the text entered in the textbox
     * @return The text in the textbox
     * @throws WebExceptions if text cannot be retrieved
     */
    public String getTextboxText() throws WebExceptions {
        return getElementText(textboxInput);
    }
    
    /**
     * Uploads a file using the file input element
     * @param filePath The path to the file to upload
     * @throws WebExceptions if file upload fails
     */
    public void uploadFile(String filePath) throws WebExceptions {
        try {
            fileUploadInput.sendKeys(filePath);
            waitForPageLoad();
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.GENERAL_EXCEPTION,
                    "Failed to upload file: " + e.getMessage());
        }
    }
    
    /**
     * Clicks the file upload button
     * @throws WebExceptions if file upload button cannot be clicked
     */
    public void clickFileUploadButton() throws WebExceptions {
        clickElement(fileUploadButton);
    }
    
    /**
     * Gets the name of the uploaded file
     * @return The uploaded file name
     * @throws WebExceptions if file name cannot be retrieved
     */
    public String getUploadedFileName() throws WebExceptions {
        return getElementText(fileNameDisplay);
    }
    
    /**
     * Clicks the Submit Form button
     * @throws WebExceptions if Submit Form button cannot be clicked
     */
    public void clickSubmitFormButton() throws WebExceptions {
        clickElement(submitFormButton);
        waitForPageLoad();
    }
    
    /**
     * Clicks the Reset Form button
     * @throws WebExceptions if Reset Form button cannot be clicked
     */
    public void clickResetFormButton() throws WebExceptions {
        clickElement(resetFormButton);
        waitForPageLoad();
    }
    
    /**
     * Submits the form with text and file
     * @param text The text to enter in the textbox
     * @param filePath The path to the file to upload
     * @throws WebExceptions if form submission fails
     */
    public void submitForm(String text, String filePath) throws WebExceptions {
        enterTextInTextbox(text);
        uploadFile(filePath);
        clickSubmitFormButton();
    }
    
    /**
     * Gets the upload status message
     * @return The upload status text
     * @throws WebExceptions if upload status cannot be retrieved
     */
    public String getUploadStatus() throws WebExceptions {
        return getElementText(uploadStatus);
    }
    
    /**
     * Gets the upload progress
     * @return The upload progress text
     * @throws WebExceptions if upload progress cannot be retrieved
     */
    public String getUploadProgress() throws WebExceptions {
        return getElementText(uploadProgress);
    }
    
    /**
     * Checks if upload is in progress
     * @return true if upload is in progress, false otherwise
     */
    public boolean isUploadInProgress() {
        return isElementVisible(uploadProgress);
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
     * Checks if error message is displayed
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(errorMessage);
    }
    
    /**
     * Gets the error message
     * @return The error message text
     * @throws WebExceptions if error message cannot be retrieved
     */
    public String getErrorMessage() throws WebExceptions {
        return getElementText(errorMessage);
    }
    
    /**
     * Gets the form submission response
     * @return The form submission response text
     * @throws WebExceptions if response cannot be retrieved
     */
    public String getFormSubmissionResponse() throws WebExceptions {
        return getElementText(formSubmissionResponse);
    }
    
    /**
     * Verifies that the form preview is loaded
     * @return true if form preview is loaded, false otherwise
     */
    public boolean isFormPreviewLoaded() {
        return isElementVisible(formPreviewContainer) && 
               isElementVisible(formPreviewTitle);
    }
    
    /**
     * Verifies that all form input elements are visible
     * @return true if all input elements are visible, false otherwise
     */
    public boolean areAllFormInputElementsVisible() {
        return isElementVisible(textboxInput) && 
               isElementVisible(fileUploadInput) && 
               isElementVisible(fileUploadButton);
    }
    
    /**
     * Verifies that all form submission elements are visible
     * @return true if all submission elements are visible, false otherwise
     */
    public boolean areAllFormSubmissionElementsVisible() {
        return isElementVisible(submitFormButton) && 
               isElementVisible(resetFormButton);
    }
    
    /**
     * Verifies that all form preview elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllFormPreviewElementsVisible() {
        return isFormPreviewLoaded() && 
               areAllFormInputElementsVisible() && 
               areAllFormSubmissionElementsVisible();
    }
    
    /**
     * Checks if file upload was successful
     * @return true if file upload was successful, false otherwise
     */
    public boolean isFileUploadSuccessful() {
        return isSuccessMessageDisplayed() && !isErrorMessageDisplayed();
    }
    
    /**
     * Waits for file upload to complete
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return true if upload completed successfully, false otherwise
     */
    public boolean waitForFileUploadToComplete(int timeoutSeconds) {
        try {
            int attempts = 0;
            int maxAttempts = timeoutSeconds * 2; // Check every 500ms
            
            while (attempts < maxAttempts) {
                if (!isUploadInProgress() && (isSuccessMessageDisplayed() || isErrorMessageDisplayed())) {
                    return isFileUploadSuccessful();
                }
                Thread.sleep(1000);
                attempts++;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
