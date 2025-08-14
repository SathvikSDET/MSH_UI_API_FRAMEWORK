package com.automationframework.utils;

import com.github.javafaker.Faker;

/**
 * Utility class for providing test data
 * Uses JavaFaker to generate realistic test data
 */
public class TestDataProvider {
    
    private static final Faker faker = new Faker();
    
    // Login credentials
    public static final String VALID_USERNAME = "testuser@example.com";
    public static final String VALID_PASSWORD = "TestPassword123!";
    public static final String INVALID_USERNAME = "invalid@example.com";
    public static final String INVALID_PASSWORD = "WrongPassword";
    
    // Task Bot details
    public static final String TASK_NAME = "Message Box Automation Task";
    public static final String TASK_DESCRIPTION = "Automated task for testing Message Box functionality";
    public static final String TASK_CATEGORY = "Automation";
    public static final String TASK_PRIORITY = "High";
    public static final String TASK_ASSIGNEE = "Automation Team";
    
    // Message Box configuration
    public static final String MESSAGE_TEXT = "This is an automated test message";
    public static final String MESSAGE_TYPE = "Information";
    public static final String MESSAGE_DURATION = "5";
    
    // Form configuration
    public static final String FORM_NAME = "File Upload Test Form";
    public static final String FORM_DESCRIPTION = "Automated form for testing file upload functionality";
    public static final String FORM_CATEGORY = "Testing";
    public static final String FORM_TYPE = "Standard";
    public static final String FORM_ACCESS = "Public";
    
    // Form input data
    public static final String FORM_TEXT_INPUT = "This is test text for the form";
    public static final String TEST_FILE_PATH = "./test-files/sample-document.txt";
    
    // Learning Instance configuration
    public static final String LEARNING_INSTANCE_NAME = "Test Learning Instance";
    public static final String LEARNING_INSTANCE_DESCRIPTION = "Automated test learning instance for API validation";
    public static final String LEARNING_INSTANCE_TYPE = "Supervised";
    public static final String LEARNING_INSTANCE_STATUS = "Draft";
    
    // Application URLs
    public static final String BASE_URL = "https://swift.techwithjatin.com";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String DASHBOARD_URL = BASE_URL + "/dashboard";
    
    /**
     * Generates a random task name
     * @return Random task name
     */
    public static String getRandomTaskName() {
        return "Task_" + faker.lorem().word() + "_" + System.currentTimeMillis();
    }
    
    /**
     * Generates a random task description
     * @return Random task description
     */
    public static String getRandomTaskDescription() {
        return faker.lorem().sentence();
    }
    
    /**
     * Generates a random message text
     * @return Random message text
     */
    public static String getRandomMessageText() {
        return faker.lorem().sentence();
    }
    
    /**
     * Gets a random category from predefined options
     * @return Random category
     */
    public static String getRandomCategory() {
        String[] categories = {"Automation", "Testing", "Development", "Support", "Maintenance"};
        return categories[faker.random().nextInt(categories.length)];
    }
    
    /**
     * Gets a random priority from predefined options
     * @return Random priority
     */
    public static String getRandomPriority() {
        String[] priorities = {"Low", "Medium", "High", "Critical"};
        return priorities[faker.random().nextInt(priorities.length)];
    }
    
    /**
     * Gets a random message type from predefined options
     * @return Random message type
     */
    public static String getRandomMessageType() {
        String[] types = {"Information", "Warning", "Error", "Success"};
        return types[faker.random().nextInt(types.length)];
    }
    
    /**
     * Generates a random duration between 1 and 10 seconds
     * @return Random duration as string
     */
    public static String getRandomDuration() {
        return String.valueOf(faker.random().nextInt(1, 11));
    }
    
    /**
     * Generates a random email address
     * @return Random email address
     */
    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }
    
    /**
     * Generates a random name
     * @return Random name
     */
    public static String getRandomName() {
        return faker.name().fullName();
    }
    
    /**
     * Generates a random form name
     * @return Random form name
     */
    public static String getRandomFormName() {
        return "Form_" + faker.lorem().word() + "_" + System.currentTimeMillis();
    }
    
    /**
     * Generates a random form description
     * @return Random form description
     */
    public static String getRandomFormDescription() {
        return faker.lorem().sentence();
    }
    
    /**
     * Gets a random form type from predefined options
     * @return Random form type
     */
    public static String getRandomFormType() {
        String[] types = {"Standard", "Advanced", "Custom", "Template"};
        return types[faker.random().nextInt(types.length)];
    }
    
    /**
     * Gets a random form access level from predefined options
     * @return Random form access level
     */
    public static String getRandomFormAccess() {
        String[] accessLevels = {"Public", "Private", "Restricted", "Team"};
        return accessLevels[faker.random().nextInt(accessLevels.length)];
    }
    
    /**
     * Generates a random learning instance name
     * @return Random learning instance name
     */
    public static String getRandomLearningInstanceName() {
        return "Learning_Instance_" + faker.lorem().word() + "_" + System.currentTimeMillis();
    }
    
    /**
     * Generates a random learning instance description
     * @return Random learning instance description
     */
    public static String getRandomLearningInstanceDescription() {
        return faker.lorem().sentence();
    }
    
    /**
     * Gets a random learning instance type from predefined options
     * @return Random learning instance type
     */
    public static String getRandomLearningInstanceType() {
        String[] types = {"Supervised", "Unsupervised", "Reinforcement", "Deep Learning", "Machine Learning"};
        return types[faker.random().nextInt(types.length)];
    }
    
    /**
     * Gets a random learning instance status from predefined options
     * @return Random learning instance status
     */
    public static String getRandomLearningInstanceStatus() {
        String[] statuses = {"Draft", "Active", "Inactive", "Completed", "Failed"};
        return statuses[faker.random().nextInt(statuses.length)];
    }
}
