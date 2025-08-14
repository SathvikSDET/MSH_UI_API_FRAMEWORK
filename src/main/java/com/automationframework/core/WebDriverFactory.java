package com.automationframework.core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Factory class for creating and managing WebDriver instances
 * Follows Factory Pattern for driver management
 */
public class WebDriverFactory {
    
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final String SAFARI = "safari";
    
    /**
     * Creates a WebDriver instance based on the specified browser type
     * @param browserType The type of browser to create
     * @return WebDriver instance
     */
    public static WebDriver createDriver(String browserType) {
        WebDriver driver = null;
        
        switch (browserType.toLowerCase()) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
                
            case SAFARI:
                SafariOptions safariOptions = new SafariOptions();
                driver = new SafariDriver(safariOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        
        // Common driver configurations
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        return driver;
    }
    
    /**
     * Creates a Chrome WebDriver with default configuration
     * @return Chrome WebDriver instance
     */
    public static WebDriver createChromeDriver() {
        return createDriver(CHROME);
    }
    
    /**
     * Quits the WebDriver and releases resources
     * @param driver The WebDriver instance to quit
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            }
        }
    }
}
