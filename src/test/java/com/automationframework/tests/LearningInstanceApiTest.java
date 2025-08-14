package com.automationframework.tests;

import com.automationframework.api.LearningInstanceApi;
import com.automationframework.utils.TestDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for Learning Instance API Flow automation
 * Implements the complete API test flow with comprehensive validations
 */
public class LearningInstanceApiTest {
    
    private LearningInstanceApi learningInstanceApi;
    private String createdInstanceId;
    
    @BeforeClass
    public void setUp() {
        learningInstanceApi = new LearningInstanceApi();
    }
    
    /**
     * Test Case: Complete Learning Instance API Flow
     * Steps:
     * 1. Perform login using the provided credentials
     * 2. After login, navigate to learning instance under AI tab
     * 3. Create a Learning Instance
     * 4. Validate the created instance with appropriate checks
     */
    @Test(description = "Complete Learning Instance API Flow", priority = 1)
    public void testCompleteLearningInstanceApiFlow() {
        try {
            // Step 1: Perform login using the provided credentials
            boolean loginSuccess = performLogin();
            Assert.assertTrue(loginSuccess, "Login should be successful");
            
            // Step 2: After login, navigate to learning instance under AI tab
            Response aiTabResponse = navigateToAITab();
            Assert.assertNotNull(aiTabResponse, "AI tab navigation response should not be null");
            
            // Step 3: Create a Learning Instance
            Response creationResponse = createLearningInstance();
            Assert.assertNotNull(creationResponse, "Learning Instance creation response should not be null");
            
            // Step 4: Validate the created instance with appropriate checks
            validateCreatedInstance(creationResponse);
            
            // Extract instance ID for further validation
            createdInstanceId = learningInstanceApi.extractLearningInstanceId(creationResponse);
            Assert.assertNotNull(createdInstanceId, "Learning Instance ID should not be null");
            
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Learning Instance Creation with Default Data
     * Validates creation with predefined test data
     */
    @Test(description = "Learning Instance Creation with Default Data", priority = 2)
    public void testLearningInstanceCreationWithDefaultData() {
        try {
            // Ensure login
            if (!learningInstanceApi.isAuthenticated()) {
                performLogin();
            }
            
            // Create Learning Instance with default data
            Response response = learningInstanceApi.createLearningInstance();
            
            // Validate response
            Assert.assertNotNull(response, "Response should not be null");
            Assert.assertTrue(learningInstanceApi.validateLearningInstanceCreation(response), 
                           "Learning Instance creation validation should pass");
            
            // Validate specific field values
            String instanceName = learningInstanceApi.extractLearningInstanceName(response);
            String instanceStatus = learningInstanceApi.extractLearningInstanceStatus(response);
            
            Assert.assertEquals(instanceName, TestDataProvider.LEARNING_INSTANCE_NAME, 
                              "Instance name should match expected value");
            Assert.assertEquals(instanceStatus, TestDataProvider.LEARNING_INSTANCE_STATUS, 
                              "Instance status should match expected value");
            
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Learning Instance Creation with Custom Data
     * Validates creation with custom test data
     */
    @Test(description = "Learning Instance Creation with Custom Data", priority = 3)
    public void testLearningInstanceCreationWithCustomData() {
        try {
            // Ensure login
            if (!learningInstanceApi.isAuthenticated()) {
                performLogin();
            }
            
            // Custom test data
            String customName = "Custom_Test_Instance_" + System.currentTimeMillis();
            String customDescription = "Custom test description for API validation";
            String customType = "Unsupervised";
            String customStatus = "Active";
            
            // Create Learning Instance with custom data
            Response response = learningInstanceApi.createLearningInstance(
                customName, customDescription, customType, customStatus);
            
            // Validate response
            Assert.assertNotNull(response, "Response should not be null");
            Assert.assertTrue(learningInstanceApi.validateLearningInstanceCreation(response), 
                           "Learning Instance creation validation should pass");
            
            // Validate custom field values
            String instanceName = learningInstanceApi.extractLearningInstanceName(response);
            String instanceStatus = learningInstanceApi.extractLearningInstanceStatus(response);
            
            Assert.assertEquals(instanceName, customName, "Instance name should match custom value");
            Assert.assertEquals(instanceStatus, customStatus, "Instance status should match custom value");
            
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Learning Instance Creation with Random Data
     * Validates creation with randomly generated test data
     */
    @Test(description = "Learning Instance Creation with Random Data", priority = 4)
    public void testLearningInstanceCreationWithRandomData() {
        try {
            // Ensure login
            if (!learningInstanceApi.isAuthenticated()) {
                performLogin();
            }
            
            // Create Learning Instance with random data
            Response response = learningInstanceApi.createLearningInstanceWithRandomData();
            
            // Validate response
            Assert.assertNotNull(response, "Response should not be null");
            Assert.assertTrue(learningInstanceApi.validateLearningInstanceCreation(response), 
                           "Learning Instance creation validation should pass");
            
            // Validate that required fields are present
            String instanceId = learningInstanceApi.extractLearningInstanceId(response);
            String instanceName = learningInstanceApi.extractLearningInstanceName(response);
            String instanceStatus = learningInstanceApi.extractLearningInstanceStatus(response);
            
            Assert.assertNotNull(instanceId, "Instance ID should not be null");
            Assert.assertNotNull(instanceName, "Instance name should not be null");
            Assert.assertNotNull(instanceStatus, "Instance status should not be null");
            
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Learning Instance Retrieval and Validation
     * Validates retrieval of created Learning Instance
     */
    @Test(description = "Learning Instance Retrieval and Validation", priority = 5)
    public void testLearningInstanceRetrievalAndValidation() {
        try {
            // Ensure we have a created instance ID
            if (createdInstanceId == null) {
                // Create a new instance if none exists
                Response creationResponse = learningInstanceApi.createLearningInstance();
                createdInstanceId = learningInstanceApi.extractLearningInstanceId(creationResponse);
                Assert.assertNotNull(createdInstanceId, "Learning Instance ID should not be null");
            }
            
            // Retrieve the Learning Instance by ID
            Response response = learningInstanceApi.getLearningInstanceById(createdInstanceId);
            
            // Validate response
            Assert.assertNotNull(response, "Response should not be null");
            Assert.assertTrue(learningInstanceApi.validateLearningInstanceRetrieval(response), 
                           "Learning Instance retrieval validation should pass");
            
            // Validate field values match creation data
            String instanceName = learningInstanceApi.extractLearningInstanceName(response);
            String instanceStatus = learningInstanceApi.extractLearningInstanceStatus(response);
            
            Assert.assertEquals(instanceName, TestDataProvider.LEARNING_INSTANCE_NAME, 
                              "Retrieved instance name should match created value");
            Assert.assertEquals(instanceStatus, TestDataProvider.LEARNING_INSTANCE_STATUS, 
                              "Retrieved instance status should match created value");
            
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Learning Instance Update and Validation
     * Validates update functionality of Learning Instance
     */
    @Test(description = "Learning Instance Update and Validation", priority = 6)
    public void testLearningInstanceUpdateAndValidation() {
        try {
            // Ensure we have a created instance ID
            if (createdInstanceId == null) {
                // Create a new instance if none exists
                Response creationResponse = learningInstanceApi.createLearningInstance();
                createdInstanceId = learningInstanceApi.extractLearningInstanceId(creationResponse);
                Assert.assertNotNull(createdInstanceId, "Learning Instance ID should not be null");
            }
            
            // Prepare update payload
            Map<String, Object> updatePayload = new HashMap<>();
            updatePayload.put("name", "Updated_Test_Instance_" + System.currentTimeMillis());
            updatePayload.put("description", "Updated description for API validation");
            updatePayload.put("status", "Active");
            
            // Update the Learning Instance
            Response updateResponse = learningInstanceApi.updateLearningInstance(createdInstanceId, updatePayload);
            
            // Validate update response
            Assert.assertNotNull(updateResponse, "Update response should not be null");
            Assert.assertTrue(learningInstanceApi.validateStatusCode(updateResponse, 200), 
                           "Update should return status code 200");
            
            // Retrieve and validate updated instance
            Response retrievalResponse = learningInstanceApi.getLearningInstanceById(createdInstanceId);
            Assert.assertNotNull(retrievalResponse, "Retrieval response should not be null");
            
            // Validate updated field values
            String updatedName = learningInstanceApi.extractLearningInstanceName(retrievalResponse);
            String updatedStatus = learningInstanceApi.extractLearningInstanceStatus(retrievalResponse);
            
            Assert.assertEquals(updatedName, updatePayload.get("name"), 
                              "Updated instance name should match update payload");
            Assert.assertEquals(updatedStatus, updatePayload.get("status"), 
                              "Updated instance status should match update payload");
            
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Test Case: Learning Instance Deletion and Validation
     * Validates deletion functionality of Learning Instance
     */
    @Test(description = "Learning Instance Deletion and Validation", priority = 7)
    public void testLearningInstanceDeletionAndValidation() {
        try {
            // Ensure we have a created instance ID
            if (createdInstanceId == null) {
                // Create a new instance if none exists
                Response creationResponse = learningInstanceApi.createLearningInstance();
                createdInstanceId = learningInstanceApi.extractLearningInstanceId(creationResponse);
                Assert.assertNotNull(createdInstanceId, "Learning Instance ID should not be null");
            }
            
            // Delete the Learning Instance
            Response deleteResponse = learningInstanceApi.deleteLearningInstance(createdInstanceId);
            
            // Validate delete response
            Assert.assertNotNull(deleteResponse, "Delete response should not be null");
            Assert.assertTrue(learningInstanceApi.validateStatusCode(deleteResponse, 200) || 
                           learningInstanceApi.validateStatusCode(deleteResponse, 204), 
                           "Delete should return status code 200 or 204");
            
            // Verify instance is deleted by attempting to retrieve it
            Response retrievalResponse = learningInstanceApi.getLearningInstanceById(createdInstanceId);
            Assert.assertTrue(learningInstanceApi.validateStatusCode(retrievalResponse, 404), 
                           "Retrieval of deleted instance should return status code 404");
            
            // Reset instance ID
            createdInstanceId = null;
            
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }
    
    /**
     * Performs login using test credentials
     * @return true if login successful, false otherwise
     */
    private boolean performLogin() {
        return learningInstanceApi.performLogin();
    }
    
    /**
     * Navigates to AI tab
     * @return Response object
     */
    private Response navigateToAITab() {
        return learningInstanceApi.navigateToAITab();
    }
    
    /**
     * Creates a Learning Instance
     * @return Response object
     */
    private Response createLearningInstance() {
        return learningInstanceApi.createLearningInstance();
    }
    
    /**
     * Validates the created Learning Instance
     * @param response The creation response
     */
    private void validateCreatedInstance(Response response) {
        // Validate HTTP status code (201 Created)
        Assert.assertTrue(learningInstanceApi.validateStatusCode(response, 201), 
                        "Expected status code 201 Created, got: " + response.getStatusCode());
        
        // Validate response time (should be under 5 seconds)
        Assert.assertTrue(learningInstanceApi.validateResponseTime(response, 5000), 
                        "Response time should be under 5 seconds, got: " + response.getTime() + "ms");
        
        // Validate response body schema and field-level checks
        Assert.assertTrue(learningInstanceApi.validateRequiredFields(response, "id", "name", "status", "createdAt"), 
                        "Required fields should be present in response");
        
        // Validate functional accuracy
        String instanceName = learningInstanceApi.extractLearningInstanceName(response);
        String instanceStatus = learningInstanceApi.extractLearningInstanceStatus(response);
        
        Assert.assertNotNull(instanceName, "Instance name should not be null");
        Assert.assertFalse(instanceName.isEmpty(), "Instance name should not be empty");
        Assert.assertNotNull(instanceStatus, "Instance status should not be null");
        Assert.assertTrue("Active".equals(instanceStatus) || "Draft".equals(instanceStatus), 
                        "Instance status should be valid");
        
        // Log response details for debugging
        System.out.println("Created Learning Instance:");
        System.out.println("ID: " + learningInstanceApi.extractLearningInstanceId(response));
        System.out.println("Name: " + instanceName);
        System.out.println("Status: " + instanceStatus);
        System.out.println("Response Time: " + response.getTime() + "ms");
    }
}
