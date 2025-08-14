package com.automationframework.api;

import io.restassured.response.Response;
import com.automationframework.utils.TestDataProvider;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

/**
 * API class for Learning Instance operations
 * Extends ApiBaseClass to provide Learning Instance specific functionality
 */
public class LearningInstanceApi extends ApiBaseClass {
    
    private static final Faker faker = new Faker();
    
    // API endpoints
    private static final String LEARNING_INSTANCES_ENDPOINT = "/api/learning-instances";
    private static final String AI_TAB_ENDPOINT = "/api/ai";
    private static final String LEARNING_INSTANCE_BY_ID_ENDPOINT = "/api/learning-instances/{id}";
    
    /**
     * Navigates to the AI tab and retrieves learning instance information
     * @return Response object
     */
    public Response navigateToAITab() {
        return get(AI_TAB_ENDPOINT);
    }
    
    /**
     * Creates a new Learning Instance with default test data
     * @return Response object
     */
    public Response createLearningInstance() {
        Map<String, Object> payload = createDefaultLearningInstancePayload();
        return post(LEARNING_INSTANCES_ENDPOINT, payload);
    }
    
    /**
     * Creates a Learning Instance with custom data
     * @param name The name of the learning instance
     * @param description The description of the learning instance
     * @param type The type of learning instance
     * @param status The initial status
     * @return Response object
     */
    public Response createLearningInstance(String name, String description, String type, String status) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("description", description);
        payload.put("type", type);
        payload.put("status", status);
        payload.put("createdBy", TestDataProvider.VALID_USERNAME);
        payload.put("createdAt", System.currentTimeMillis());
        
        return post(LEARNING_INSTANCES_ENDPOINT, payload);
    }
    
    /**
     * Creates a Learning Instance with random data for testing
     * @return Response object
     */
    public Response createLearningInstanceWithRandomData() {
        Map<String, Object> payload = createRandomLearningInstancePayload();
        return post(LEARNING_INSTANCES_ENDPOINT, payload);
    }
    
    /**
     * Retrieves a Learning Instance by ID
     * @param instanceId The ID of the learning instance
     * @return Response object
     */
    public Response getLearningInstanceById(String instanceId) {
        String endpoint = LEARNING_INSTANCE_BY_ID_ENDPOINT.replace("{id}", instanceId);
        return get(endpoint);
    }
    
    /**
     * Retrieves all Learning Instances
     * @return Response object
     */
    public Response getAllLearningInstances() {
        return get(LEARNING_INSTANCES_ENDPOINT);
    }
    
    /**
     * Updates a Learning Instance
     * @param instanceId The ID of the learning instance to update
     * @param updatePayload The update payload
     * @return Response object
     */
    public Response updateLearningInstance(String instanceId, Map<String, Object> updatePayload) {
        String endpoint = LEARNING_INSTANCE_BY_ID_ENDPOINT.replace("{id}", instanceId);
        return put(endpoint, updatePayload);
    }
    
    /**
     * Deletes a Learning Instance
     * @param instanceId The ID of the learning instance to delete
     * @return Response object
     */
    public Response deleteLearningInstance(String instanceId) {
        String endpoint = LEARNING_INSTANCE_BY_ID_ENDPOINT.replace("{id}", instanceId);
        return delete(endpoint);
    }
    
    /**
     * Creates default test data for Learning Instance
     * @return Map containing default test data
     */
    private Map<String, Object> createDefaultLearningInstancePayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", TestDataProvider.LEARNING_INSTANCE_NAME);
        payload.put("description", TestDataProvider.LEARNING_INSTANCE_DESCRIPTION);
        payload.put("type", TestDataProvider.LEARNING_INSTANCE_TYPE);
        payload.put("status", TestDataProvider.LEARNING_INSTANCE_STATUS);
        payload.put("createdBy", TestDataProvider.VALID_USERNAME);
        payload.put("createdAt", System.currentTimeMillis());
        payload.put("configuration", createDefaultConfiguration());
        
        return payload;
    }
    
    /**
     * Creates random test data for Learning Instance
     * @return Map containing random test data
     */
    private Map<String, Object> createRandomLearningInstancePayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Test_Instance_" + faker.lorem().word() + "_" + System.currentTimeMillis());
        payload.put("description", faker.lorem().sentence());
        payload.put("type", getRandomLearningType());
        payload.put("status", getRandomLearningStatus());
        payload.put("createdBy", TestDataProvider.VALID_USERNAME);
        payload.put("createdAt", System.currentTimeMillis());
        payload.put("configuration", createRandomConfiguration());
        
        return payload;
    }
    
    /**
     * Creates default configuration for Learning Instance
     * @return Map containing default configuration
     */
    private Map<String, Object> createDefaultConfiguration() {
        Map<String, Object> config = new HashMap<>();
        config.put("algorithm", "default-algorithm");
        config.put("parameters", new HashMap<String, Object>() {{
            put("learningRate", 0.01);
            put("epochs", 100);
            put("batchSize", 32);
        }});
        config.put("dataSource", "default-datasource");
        
        return config;
    }
    
    /**
     * Creates random configuration for Learning Instance
     * @return Map containing random configuration
     */
    private Map<String, Object> createRandomConfiguration() {
        Map<String, Object> config = new HashMap<>();
        config.put("algorithm", faker.lorem().word());
        config.put("parameters", new HashMap<String, Object>() {{
            put("learningRate", faker.random().nextDouble());
            put("epochs", faker.random().nextInt(50, 200));
            put("batchSize", faker.random().nextInt(16, 64));
        }});
        config.put("dataSource", "datasource-" + faker.lorem().word());
        
        return config;
    }
    
    /**
     * Gets a random learning type from predefined options
     * @return Random learning type
     */
    private String getRandomLearningType() {
        String[] types = {"Supervised", "Unsupervised", "Reinforcement", "Deep Learning", "Machine Learning"};
        return types[faker.random().nextInt(types.length)];
    }
    
    /**
     * Gets a random learning status from predefined options
     * @return Random learning status
     */
    private String getRandomLearningStatus() {
        String[] statuses = {"Draft", "Active", "Inactive", "Completed", "Failed"};
        return statuses[faker.random().nextInt(statuses.length)];
    }
    
    /**
     * Validates Learning Instance creation response
     * @param response The API response
     * @return true if response is valid, false otherwise
     */
    public boolean validateLearningInstanceCreation(Response response) {
        // Validate status code
        if (!validateStatusCode(response, 201)) {
            System.err.println("Expected status code 201, got: " + response.getStatusCode());
            return false;
        }
        
        // Validate response time (should be under 5 seconds)
        if (!validateResponseTime(response, 5000)) {
            System.err.println("Response time too slow: " + response.getTime() + "ms");
            return false;
        }
        
        // Validate required fields
        if (!validateRequiredFields(response, "id", "name", "status", "createdAt")) {
            System.err.println("Required fields missing in response");
            return false;
        }
        
        // Validate field values
        String status = response.jsonPath().getString("status");
        if (!"Active".equals(status) && !"Draft".equals(status)) {
            System.err.println("Invalid status: " + status);
            return false;
        }
        
        return true;
    }
    
    /**
     * Validates Learning Instance retrieval response
     * @param response The API response
     * @return true if response is valid, false otherwise
     */
    public boolean validateLearningInstanceRetrieval(Response response) {
        // Validate status code
        if (!validateStatusCode(response, 200)) {
            System.err.println("Expected status code 200, got: " + response.getStatusCode());
            return false;
        }
        
        // Validate response time (should be under 3 seconds)
        if (!validateResponseTime(response, 3000)) {
            System.err.println("Response time too slow: " + response.getTime() + "ms");
            return false;
        }
        
        // Validate required fields
        if (!validateRequiredFields(response, "id", "name", "description", "type", "status")) {
            System.err.println("Required fields missing in response");
            return false;
        }
        
        return true;
    }
    
    /**
     * Extracts Learning Instance ID from creation response
     * @param response The API response
     * @return The Learning Instance ID, or null if not found
     */
    public String extractLearningInstanceId(Response response) {
        try {
            return response.jsonPath().getString("id");
        } catch (Exception e) {
            System.err.println("Failed to extract Learning Instance ID: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Extracts Learning Instance name from response
     * @param response The API response
     * @return The Learning Instance name, or null if not found
     */
    public String extractLearningInstanceName(Response response) {
        try {
            return response.jsonPath().getString("name");
        } catch (Exception e) {
            System.err.println("Failed to extract Learning Instance name: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Extracts Learning Instance status from response
     * @param response The API response
     * @return The Learning Instance status, or null if not found
     */
    public String extractLearningInstanceStatus(Response response) {
        try {
            return response.jsonPath().getString("status");
        } catch (Exception e) {
            System.err.println("Failed to extract Learning Instance status: " + e.getMessage());
            return null;
        }
    }
}
