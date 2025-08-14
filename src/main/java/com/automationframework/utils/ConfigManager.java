package com.automationframework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for managing configuration properties
 * Reads configuration from config.properties file
 */
public class ConfigManager {
    
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";
    
    static {
        loadProperties();
    }
    
    /**
     * Loads configuration properties from the config file
     */
    private static void loadProperties() {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Unable to find " + CONFIG_FILE);
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading configuration properties: " + e.getMessage());
        }
    }
    
    /**
     * Gets a string property value
     * @param key The property key
     * @return The property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Gets a string property value with default
     * @param key The property key
     * @param defaultValue The default value if key is not found
     * @return The property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Gets an integer property value
     * @param key The property key
     * @return The property value as integer
     */
    public static int getIntProperty(String key) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer property " + key + ": " + value);
            return 0;
        }
    }
    
    /**
     * Gets an integer property value with default
     * @param key The property key
     * @param defaultValue The default value if key is not found
     * @return The property value or default value as integer
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing integer property " + key + ": " + value);
            return defaultValue;
        }
    }
    
    /**
     * Gets a boolean property value
     * @param key The property key
     * @return The property value as boolean
     */
    public static boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Gets a boolean property value with default
     * @param key The property key
     * @param defaultValue The default value if key is not found
     * @return The property value or default value as boolean
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Gets the base URL from configuration
     * @return The base URL
     */
    public static String getBaseUrl() {
        return getProperty("app.base.url");
    }
    
    /**
     * Gets the login URL from configuration
     * @return The login URL
     */
    public static String getLoginUrl() {
        return getProperty("app.login.url");
    }
    
    /**
     * Gets the dashboard URL from configuration
     * @return The dashboard URL
     */
    public static String getDashboardUrl() {
        return getProperty("app.dashboard.url");
    }
    
    /**
     * Gets the automation URL from configuration
     * @return The automation URL
     */
    public static String getAutomationUrl() {
        return getProperty("app.automation.url");
    }
    
    /**
     * Gets the default browser from configuration
     * @return The default browser
     */
    public static String getDefaultBrowser() {
        return getProperty("browser.default", "chrome");
    }
    
    /**
     * Gets the implicit wait timeout from configuration
     * @return The implicit wait timeout in seconds
     */
    public static int getImplicitWaitTimeout() {
        return getIntProperty("webdriver.timeout.implicit", 10);
    }
    
    /**
     * Gets the explicit wait timeout from configuration
     * @return The explicit wait timeout in seconds
     */
    public static int getExplicitWaitTimeout() {
        return getIntProperty("webdriver.timeout.explicit", 10);
    }
    
    /**
     * Gets the page load timeout from configuration
     * @return The page load timeout in seconds
     */
    public static int getPageLoadTimeout() {
        return getIntProperty("webdriver.timeout.pageLoad", 30);
    }
    
    /**
     * Gets the script timeout from configuration
     * @return The script timeout in seconds
     */
    public static int getScriptTimeout() {
        return getIntProperty("webdriver.timeout.script", 30);
    }
    
    /**
     * Checks if headless mode is enabled
     * @return true if headless mode is enabled, false otherwise
     */
    public static boolean isHeadlessMode() {
        return getBooleanProperty("browser.headless", false);
    }
    
    /**
     * Checks if browser should be maximized
     * @return true if browser should be maximized, false otherwise
     */
    public static boolean shouldMaximizeBrowser() {
        return getBooleanProperty("browser.maximize", true);
    }
    
    /**
     * Gets the retry count from configuration
     * @return The retry count
     */
    public static int getRetryCount() {
        return getIntProperty("retry.count", 2);
    }
    
    /**
     * Gets the retry interval from configuration
     * @return The retry interval in milliseconds
     */
    public static int getRetryInterval() {
        return getIntProperty("retry.interval", 1000);
    }
    
    /**
     * Gets the maximum API response time from configuration
     * @return The maximum API response time in milliseconds
     */
    public static int getMaxApiResponseTime() {
        return getIntProperty("test.api.response.time.max", 5000);
    }
    
    /**
     * Gets the API retry count from configuration
     * @return The API retry count
     */
    public static int getApiRetryCount() {
        return getIntProperty("test.api.retry.count", 3);
    }
    
    /**
     * Gets the Learning Instance name from configuration
     * @return The Learning Instance name
     */
    public static String getLearningInstanceName() {
        return getProperty("test.learning.instance.name");
    }
    
    /**
     * Gets the Learning Instance description from configuration
     * @return The Learning Instance description
     */
    public static String getLearningInstanceDescription() {
        return getProperty("test.learning.instance.description");
    }
    
    /**
     * Gets the Learning Instance type from configuration
     * @return The Learning Instance type
     */
    public static String getLearningInstanceType() {
        return getProperty("test.learning.instance.type");
    }
    
    /**
     * Gets the Learning Instance status from configuration
     * @return The Learning Instance status
     */
    public static String getLearningInstanceStatus() {
        return getProperty("test.learning.instance.status");
    }
}
