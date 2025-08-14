package com.automationframework.tests;

import com.automationframework.core.BaseTest;
import com.automationframework.core.WebExceptions;
import com.automationframework.pages.*;
import com.automationframework.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Form with Upload Flow automation
 * Implements the complete test flow with assertions
 */
public class FormWithUploadTest extends BaseTest {
    
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AutomationPage automationPage;
    private FormPage formPage;
    private FormDesignerPage formDesignerPage;
    private FormPreviewPage formPreviewPage;
    
    @Override
    protected void navigateToApplication() {
        driver.get(TestDataProvider.LOGIN_URL);
    }
    
    /**
     * Test Case: Complete Form with Upload Flow
     * Steps:
     * 1. Log in to the application
     * 2. Navigate to Automation from the left-hand menu
     * 3. Click on the Create dropdown and select Form
     * 4. Fill in all mandatory details and click the Create button
     * 5. From the left menu, drag and drop the Textbox and Select File elements onto the canvas
     * 6. Click on each element and verify all UI interactions in the right panel
     * 7. Enter text in the textbox and upload a document from your shared folder
     * 8. Save the form and verify whether the document is uploaded successfully
     */
    @Test(description = "Complete Form with Upload Flow")
    public void testFormWithUploadFlow() {
        try {
            // Step 1: Log in to the application
            performLogin();
            
            // Step 2: Navigate to Automation from the left-hand menu
            navigateToAutomation();
            
            // Step 3: Click on the Create dropdown and select Form
            navigateToForm();
            
            // Step 4: Fill in all mandatory details and click the Create button
            createForm();
            
            // Step 5: From the left menu, drag and drop the Textbox and Select File elements onto the canvas
            addFormElementsToCanvas();
            
            // Step 6: Click on each element and verify all UI interactions in the right panel
            verifyUIInteractions();
            
            // Step 7: Enter text in the textbox and upload a document from your shared folder
            previewAndFillForm();
            
            // Step 8: Save the form and verify whether the document is uploaded successfully
            submitFormAndVerifyUpload();
            
            // Final validation
            validateSuccessfulFormSubmission();
            
        } catch (WebExceptions e) {
            Assert.fail("Test failed due to WebExceptions: " + e.getMessage());
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Form Creation and Design Validation
     * Validates form creation, design elements, and canvas functionality
     */
    @Test(description = "Form Creation and Design Validation")
    public void testFormCreationAndDesign() {
        try {
            performLogin();
            navigateToAutomation();
            navigateToForm();
            createForm();
            addFormElementsToCanvas();
            verifyUIInteractions();
            
            // Validate form design functionality
            validateFormDesignFunctionality();
            
        } catch (WebExceptions e) {
            Assert.fail("Form Creation and Design test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: File Upload and Form Submission Validation
     * Validates file upload functionality and form submission behavior
     */
    @Test(description = "File Upload and Form Submission Validation")
    public void testFileUploadAndSubmission() {
        try {
            performLogin();
            navigateToAutomation();
            navigateToForm();
            createForm();
            addFormElementsToCanvas();
            previewAndFillForm();
            submitFormAndVerifyUpload();
            
            // Validate file upload and submission
            validateFileUploadAndSubmission();
            
        } catch (WebExceptions e) {
            Assert.fail("File Upload and Submission test failed: " + e.getMessage());
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
     * Navigates to Form creation
     * @throws WebExceptions if navigation fails
     */
    private void navigateToForm() throws WebExceptions {
        // Navigate to Form
        formPage = automationPage.navigateToForm();
        
        // Verify Form page elements are visible
        Assert.assertTrue(formPage.areAllFormElementsVisible(), "Form creation elements should be visible");
    }
    
    /**
     * Creates a form with mandatory details
     * @throws WebExceptions if form creation fails
     */
    private void createForm() throws WebExceptions {
        // Create Form with mandatory details
        formDesignerPage = formPage.createForm(
            TestDataProvider.FORM_NAME,
            TestDataProvider.FORM_DESCRIPTION,
            TestDataProvider.FORM_CATEGORY,
            TestDataProvider.FORM_TYPE,
            TestDataProvider.FORM_ACCESS
        );
        
        // Verify Form creation success
        Assert.assertTrue(formPage.isSuccessMessageDisplayed(), "Success message should be displayed after Form creation");
        
        // Verify Form Designer is loaded
        Assert.assertTrue(formDesignerPage.isFormDesignerLoaded(), "Form Designer should be loaded");
        Assert.assertTrue(formDesignerPage.areAllFormDesignerElementsVisible(), "All Form Designer elements should be visible");
    }
    
    /**
     * Adds form elements to the canvas using drag and drop
     * @throws WebExceptions if drag and drop fails
     */
    private void addFormElementsToCanvas() throws WebExceptions {
        // Verify left menu with form elements is visible
        Assert.assertTrue(formDesignerPage.isLeftMenuVisible(), "Left menu with form elements should be visible");
        
        // Verify form canvas is visible
        Assert.assertTrue(formDesignerPage.isFormCanvasVisible(), "Form canvas should be visible");
        
        // Add Textbox element to canvas
        formDesignerPage.addTextboxElement();
        
        // Add Select File element to canvas
        formDesignerPage.addSelectFileElement();
        
        // Verify elements are added to canvas
        Assert.assertTrue(formDesignerPage.areFormElementsAddedToCanvas(), "Form elements should be successfully added to canvas");
    }
    
    /**
     * Verifies UI interactions in the right panel
     * @throws WebExceptions if verification fails
     */
    private void verifyUIInteractions() throws WebExceptions {
        // Verify right panel is visible
        Assert.assertTrue(formDesignerPage.isRightPanelVisible(), "Right panel should be visible");
        
        // Select Textbox element and verify properties
        formDesignerPage.selectTextboxElement();
        Assert.assertTrue(formDesignerPage.isRightPanelVisible(), "Right panel should show Textbox properties");
        
        // Select Select File element and verify properties
        formDesignerPage.selectSelectFileElement();
        Assert.assertTrue(formDesignerPage.isRightPanelVisible(), "Right panel should show Select File properties");
    }
    
    /**
     * Previews the form and fills in the required data
     * @throws WebExceptions if form preview or filling fails
     */
    private void previewAndFillForm() throws WebExceptions {
        // Click Preview Form button
        formDesignerPage.clickPreviewFormButton();
        
        // Verify Form Preview is loaded
        Assert.assertTrue(formPreviewPage.isFormPreviewLoaded(), "Form Preview should be loaded");
        Assert.assertTrue(formPreviewPage.areAllFormPreviewElementsVisible(), "All Form Preview elements should be visible");
        
        // Enter text in textbox
        formPreviewPage.enterTextInTextbox(TestDataProvider.FORM_TEXT_INPUT);
        
        // Verify text is entered
        String enteredText = formPreviewPage.getTextboxText();
        Assert.assertEquals(enteredText, TestDataProvider.FORM_TEXT_INPUT, "Text should be entered in textbox");
        
        // Upload file
        formPreviewPage.uploadFile(TestDataProvider.TEST_FILE_PATH);
        
        // Verify file is uploaded
        String uploadedFileName = formPreviewPage.getUploadedFileName();
        Assert.assertNotNull(uploadedFileName, "File name should be displayed after upload");
        Assert.assertFalse(uploadedFileName.isEmpty(), "File name should not be empty");
    }
    
    /**
     * Submits the form and verifies file upload
     * @throws WebExceptions if form submission fails
     */
    private void submitFormAndVerifyUpload() throws WebExceptions {
        // Submit the form
        formPreviewPage.clickSubmitFormButton();
        
        // Wait for file upload to complete
        boolean uploadCompleted = formPreviewPage.waitForFileUploadToComplete(30);
        Assert.assertTrue(uploadCompleted, "File upload should complete within 30 seconds");
        
        // Verify upload success
        Assert.assertTrue(formPreviewPage.isFileUploadSuccessful(), "File upload should be successful");
        Assert.assertFalse(formPreviewPage.isErrorMessageDisplayed(), "Error message should not be displayed");
    }
    
    /**
     * Validates successful form submission
     * @throws WebExceptions if validation fails
     */
    private void validateSuccessfulFormSubmission() throws WebExceptions {
        // Verify success message
        String successMessage = formPreviewPage.getSuccessMessage();
        Assert.assertNotNull(successMessage, "Success message should not be null");
        Assert.assertFalse(successMessage.isEmpty(), "Success message should not be empty");
        
        // Verify form submission response
        String submissionResponse = formPreviewPage.getFormSubmissionResponse();
        Assert.assertNotNull(submissionResponse, "Form submission response should not be null");
        
        // Verify all form elements remain functional
        Assert.assertTrue(formPreviewPage.areAllFormPreviewElementsVisible(), "Form elements should remain visible after submission");
    }
    
    /**
     * Validates form design functionality
     * @throws WebExceptions if validation fails
     */
    private void validateFormDesignFunctionality() throws WebExceptions {
        // Verify form designer title
        String designerTitle = formDesignerPage.getFormDesignerTitle();
        Assert.assertNotNull(designerTitle, "Form Designer title should not be null");
        Assert.assertFalse(designerTitle.isEmpty(), "Form Designer title should not be empty");
        
        // Verify canvas functionality
        Assert.assertTrue(formDesignerPage.isFormCanvasVisible(), "Form canvas should be visible");
        Assert.assertTrue(formDesignerPage.isLeftMenuVisible(), "Left menu should be visible");
        Assert.assertTrue(formDesignerPage.isRightPanelVisible(), "Right panel should be visible");
    }
    
    /**
     * Validates file upload and submission functionality
     * @throws WebExceptions if validation fails
     */
    private void validateFileUploadAndSubmission() throws WebExceptions {
        // Verify file upload status
        String uploadStatus = formPreviewPage.getUploadStatus();
        Assert.assertNotNull(uploadStatus, "Upload status should not be null");
        
        // Verify form submission response
        String submissionResponse = formPreviewPage.getFormSubmissionResponse();
        Assert.assertNotNull(submissionResponse, "Form submission response should not be null");
        
        // Verify no error messages
        Assert.assertFalse(formPreviewPage.isErrorMessageDisplayed(), "No error messages should be displayed");
        
        // Verify success message
        Assert.assertTrue(formPreviewPage.isSuccessMessageDisplayed(), "Success message should be displayed");
    }
}
