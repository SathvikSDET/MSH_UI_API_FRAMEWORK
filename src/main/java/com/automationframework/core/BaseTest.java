package com.automationframework.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Abstract base class for all test classes
 * Provides common test setup and teardown functionality
 */
public abstract class BaseTest {
    
    protected WebDriver driver;
    
    /**
     * Setup method that runs before each test method
     * Creates WebDriver instance and navigates to the application
     * @param browser The browser type to use for testing
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome")String browser) {
        try {
            // Create WebDriver instance using factory
            driver = WebDriverFactory.createDriver(browser);
            
            // Navigate to the application
            navigateToApplication();
            
            // Wait for page to load
            driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(30));
            
        } catch (Exception e) {
            System.err.println("Failed to setup test: " + e.getMessage());
            throw new RuntimeException("Test setup failed", e);
        }
    }
    
    /**
     * Teardown method that runs after each test method
     * Closes the WebDriver and releases resources
     */
    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                WebDriverFactory.quitDriver(driver);
            }
        } catch (Exception e) {
            System.err.println("Failed to teardown test: " + e.getMessage());
        }
    }
    
    /**
     * Abstract method to navigate to the application
     * Must be implemented by concrete test classes
     */
    protected abstract void navigateToApplication();
    
    /**
     * Gets the current WebDriver instance
     * @return WebDriver instance
     */
    protected WebDriver getDriver() {
        return driver;
    }
    
    /**
     * Waits for a specified amount of time
     * @param seconds Number of seconds to wait
     */
    protected void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
