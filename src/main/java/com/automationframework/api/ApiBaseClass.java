package com.automationframework.api;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.automationframework.utils.ConfigManager;
import com.automationframework.utils.TestDataProvider;

import java.util.Map;
import java.util.HashMap;

/**
 * Base class for API automation
 * Provides common API functionality and utilities
 */
public class ApiBaseClass {
    
    
    protected String baseUrl;
    protected String authToken;
    protected RequestSpecification requestSpec;
    
    public ApiBaseClass() {
        this.baseUrl = ConfigManager.getBaseUrl();
        initializeRequestSpecification();
    }
    
    /**
     * Initializes the request specification with common headers and base URI
     */
    protected void initializeRequestSpecification() {
        requestSpec = RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
    
    /**
     * Performs login and retrieves authentication token
     * @param username The username for login
     * @param password The password for login
     * @return true if login successful, false otherwise
     */
    public boolean performLogin(String username, String password) {
        try {
            Map<String, String> loginPayload = new HashMap<>();
            loginPayload.put("username", username);
            loginPayload.put("password", password);
            
            Response response = requestSpec
                    .body(loginPayload)
                    .when()
                    .post("/api/auth/login")
                    .then()
                    .extract().response();
            
            if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
                // Extract auth token from response
                this.authToken = response.jsonPath().getString("token");
                if (authToken != null && !authToken.isEmpty()) {
                    // Add auth token to request specification
                    requestSpec.header("Authorization", "Bearer " + authToken);
                    return true;
                }
            }
            
            return false;
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Performs login using default test credentials
     * @return true if login successful, false otherwise
     */
    public boolean performLogin() {
        return performLogin(TestDataProvider.VALID_USERNAME, TestDataProvider.VALID_PASSWORD);
    }
    
    /**
     * Makes a GET request to the specified endpoint
     * @param endpoint The API endpoint
     * @return Response object
     */
    public Response get(String endpoint) {
        return requestSpec
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }
    
    /**
     * Makes a POST request to the specified endpoint
     * @param endpoint The API endpoint
     * @param payload The request payload
     * @return Response object
     */
    public Response post(String endpoint, Object payload) {
        return requestSpec
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
    }
    
    /**
     * Makes a PUT request to the specified endpoint
     * @param endpoint The API endpoint
     * @param payload The request payload
     * @return Response object
     */
    public Response put(String endpoint, Object payload) {
        return requestSpec
                .body(payload)
                .when()
                .put(endpoint)
                .then()
                .extract().response();
    }
    
    /**
     * Makes a DELETE request to the specified endpoint
     * @param endpoint The API endpoint
     * @return Response object
     */
    public Response delete(String endpoint) {
        return requestSpec
                .when()
                .delete(endpoint)
                .then()
                .extract().response();
    }
    
    /**
     * Validates HTTP status code
     * @param response The API response
     * @param expectedStatusCode The expected status code
     * @return true if status code matches, false otherwise
     */
    public boolean validateStatusCode(Response response, int expectedStatusCode) {
        return response.getStatusCode() == expectedStatusCode;
    }
    
    /**
     * Validates response time is within acceptable limits
     * @param response The API response
     * @param maxResponseTimeMs Maximum acceptable response time in milliseconds
     * @return true if response time is acceptable, false otherwise
     */
    public boolean validateResponseTime(Response response, long maxResponseTimeMs) {
        long responseTime = response.getTime();
        return responseTime <= maxResponseTimeMs;
    }
    
    /**
     * Validates that response contains required fields
     * @param response The API response
     * @param requiredFields Array of required field names
     * @return true if all required fields are present, false otherwise
     */
    public boolean validateRequiredFields(Response response, String... requiredFields) {
        try {
            for (String field : requiredFields) {
                Object value = response.jsonPath().get(field);
                if (value == null) {
                    System.err.println("Required field missing: " + field);
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error validating required fields: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Validates field value matches expected value
     * @param response The API response
     * @param fieldName The field name to validate
     * @param expectedValue The expected field value
     * @return true if field value matches, false otherwise
     */
    public boolean validateFieldValue(Response response, String fieldName, Object expectedValue) {
        try {
            Object actualValue = response.jsonPath().get(fieldName);
            return expectedValue.equals(actualValue);
        } catch (Exception e) {
            System.err.println("Error validating field value: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Gets the current authentication token
     * @return The authentication token
     */
    public String getAuthToken() {
        return authToken;
    }
    
    /**
     * Checks if user is authenticated
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return authToken != null && !authToken.isEmpty();
    }
    
    /**
     * Logs out the current user
     * @return true if logout successful, false otherwise
     */
    public boolean logout() {
        try {
            Response response = requestSpec
                    .when()
                    .post("/api/auth/logout")
                    .then()
                    .extract().response();
            
            if (response.getStatusCode() == 200) {
                this.authToken = null;
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Logout failed: " + e.getMessage());
            return false;
        }
    }
}
