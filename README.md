# MSH UI API Framework

A comprehensive automation framework for UI and API testing built with Selenium WebDriver, TestNG, and REST Assured. This framework follows the Page Object Model (POM) design pattern and provides robust automation capabilities for web applications and REST APIs.

### Disclaimer

Since the assignment did not include an actual website or UI under test, the following were assumed for demonstration purposes:
- **Locators/selectors**: IDs, classes, and XPath patterns are inferred based on common UI conventions.
- **Page structure and flows**: Typical navigation and component layouts are assumed.
- **Test cases and associated methods**: Implemented to reflect realistic scenarios using the assumed UI.

Replace the assumed locators and flows with your application's real identifiers and behaviors when integrating with a live system.



##  Project Overview

The MSH UI API Framework is designed to automate complex workflows including:
- **UI Automation**: Message Box Task creation, Form building with drag & drop, File uploads
- **API Automation**: Learning Instance management with comprehensive validation
- **Cross-browser Testing**: Support for Chrome, Firefox, Edge, and Safari
- **Parallel Execution**: Configurable parallel test execution for faster results

##  Architecture

### Design Patterns
- **Page Object Model (POM)**: Organizes page elements and actions for maintainable tests
- **Factory Pattern**: Manages WebDriver creation and configuration
- **Base Class Pattern**: Common functionality through abstract base classes
- **DRY Principle**: Eliminates code duplication through reusable components

### Framework Structure
```
src/
├── main/java/com/automationframework/
│   ├── core/                          # Core framework classes
│   │   ├── BasePage.java             # Abstract base page with common methods
│   │   ├── BaseTest.java             # Abstract base test class
│   │   ├── WebDriverFactory.java     # WebDriver factory for multiple browsers
│   │   └── WebExceptions.java        # Custom exception handling
│   ├── pages/                         # Page Object classes
│   │   ├── LoginPage.java            # User authentication
│   │   ├── DashboardPage.java        # Dashboard navigation
│   │   ├── AutomationPage.java       # Automation section management
│   │   ├── TaskBotPage.java          # Task Bot creation
│   │   ├── TaskBotActionsPage.java   # Task Bot actions configuration
│   │   ├── FormPage.java             # Form creation and management
│   │   ├── FormDesignerPage.java     # Form design canvas
│   │   └── FormPreviewPage.java      # Form preview and submission
│   ├── api/                           # API automation classes
│   │   ├── ApiBaseClass.java         # Base API functionality
│   │   └── LearningInstanceApi.java  # Learning Instance API operations
│   └── utils/                         # Utility classes
│       ├── ConfigManager.java         # Configuration management
│       └── TestDataProvider.java      # Test data generation
├── test/java/com/automationframework/
│   └── tests/                         # Test classes
│       ├── MessageBoxTaskTest.java    # Message Box Task automation
│       ├── FormWithUploadTest.java    # Form creation and upload flow
│       └── LearningInstanceApiTest.java # Learning Instance API tests
└── resources/
    ├── config.properties              # Framework configuration
    └── testng.xml                    # TestNG test suite configuration
```

##  Key Features

### Core Framework
- **Multi-browser Support**: Chrome, Firefox, Edge, Safari
- **WebDriver Management**: Automatic driver setup and configuration
- **Exception Handling**: Custom WebExceptions with specific error types
- **Configuration Management**: Centralized configuration via properties file
- **Logging**: Comprehensive logging with Logback and Log4j

### UI Automation
- **Page Object Model**: Maintainable and reusable page elements
- **Element Interactions**: Comprehensive UI element handling
- **Wait Strategies**: Implicit, explicit, and page load waits
- **Cross-browser Compatibility**: Consistent behavior across browsers
- **Screenshot Capture**: Automatic screenshots on test failures

### API Automation
- **REST Assured Integration**: Powerful REST API testing
- **JSON Schema Validation**: Response structure validation
- **Response Time Monitoring**: Performance validation
- **Data Serialization**: JSON to Java object conversion
- **Authentication Support**: Login and session management

### Test Framework
- **TestNG Integration**: Advanced test execution and reporting
- **Parallel Execution**: Configurable parallel test execution
- **Data Providers**: Dynamic test data generation
- **Test Suites**: Organized test execution
- **Comprehensive Reporting**: Detailed test execution reports

##  Prerequisites

- **Java**: 8 or higher
- **Maven**: 3.6 or higher
- **Browsers**: Chrome, Firefox, Edge, or Safari
- **Internet Connection**: For WebDriver downloads and dependencies

##  Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd MSH_UI_API_FRAMEWORK
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Configure the framework**
   - Edit `src/main/resources/config.properties`
   - Update application URLs and test credentials
   - Configure browser preferences and timeouts

##  Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=MessageBoxTaskTest
mvn test -Dtest=FormWithUploadTest
mvn test -Dtest=LearningInstanceApiTest
```

### Run with specific browser
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=chrome
mvn test -Dbrowser=edge
```

### Run TestNG suite
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run with parallel execution
```bash
mvn test -Dparallel=methods -DthreadCount=2
```

##  Test Reports

TestNG generates comprehensive HTML reports in the `target/surefire-reports` directory:
- Test execution summary with pass/fail statistics
- Detailed test method results
- Execution time and performance metrics
- Screenshots on failure (if configured)
- Console output and error details

## Configuration

### Browser Configuration
```properties
browser.default=chrome
browser.headless=false
browser.maximize=true
browser.implicitWait=10
```

### Timeout Configuration
```properties
webdriver.timeout.implicit=10
webdriver.timeout.explicit=10
webdriver.timeout.pageLoad=30
webdriver.timeout.script=30
```

### Application Configuration
```properties
app.baseUrl=https://your-application-url.com
app.username=testuser@example.com
app.password=TestPassword123!
```

### Test Data Configuration
```properties
test.task.name=Message Box Automation Task
test.form.name=Upload Form Test
test.learning.instance.name=Test Learning Instance
```

##  Framework Customization

### Adding New Pages
1. Create a new class extending `BasePage`
2. Define page elements using `@FindBy` annotations
3. Implement page-specific actions and validations
4. Add proper exception handling and logging

### Adding New Tests
1. Create a new class extending `BaseTest`
2. Implement the `navigateToApplication()` method
3. Add test methods with appropriate assertions
4. Use data providers for test data management

### Adding New API Endpoints
1. Extend `ApiBaseClass` for common functionality
2. Implement specific API operations
3. Add response validation and error handling
4. Include proper logging and reporting

### Adding New Browsers
1. Update `WebDriverFactory.createDriver()` method
2. Add browser-specific options and configurations
3. Update TestNG configuration if needed
4. Test compatibility across different versions

##  Test Scenarios

### 1. Message Box Task Automation
- **Objective**: Automate complete Message Box Task creation workflow
- **Flow**: Login → Navigation → Task Creation → Action Configuration → Save
- **Validation**: UI element visibility, data entry, successful creation, confirmation messages

### 2. Form with Upload Flow
- **Objective**: Automate form creation with drag & drop and file upload
- **Flow**: Form Creation → Design Canvas → Element Addition → File Upload → Submission
- **Validation**: Drag & drop functionality, text input, file upload, form submission

### 3. Learning Instance API Flow
- **Objective**: Automate Learning Instance management through APIs
- **Flow**: Authentication → Navigation → Instance Creation → Validation
- **Validation**: HTTP status codes, response time, schema validation, functional accuracy

##  Troubleshooting

### Common Issues
1. **WebDriver not found**: Ensure WebDriverManager dependency is included
2. **Element not found**: Check element locators and page loading
3. **Timeout issues**: Adjust wait timeouts in configuration
4. **Browser compatibility**: Verify browser version compatibility
5. **API connection issues**: Check network connectivity and authentication

### Debug Mode
Enable debug logging by setting:
```properties
logging.level=DEBUG
logging.level.com.automationframework=DEBUG
```

### Performance Issues
- Use headless mode for faster execution
- Implement parallel execution
- Optimize wait strategies
- Use efficient element locators

## Best Practices

### Code Organization
- Follow POM design pattern strictly
- Use meaningful names for page objects and methods
- Implement proper exception handling
- Add comprehensive JavaDoc comments
- Maintain consistent coding standards

### Test Design
- Keep tests independent and isolated
- Use descriptive test method names
- Implement proper setup and teardown
- Add meaningful assertions and validations
- Use data providers for test data

### Maintenance
- Regular updates of WebDriver versions
- Periodic review of element locators
- Update test data as application changes
- Monitor test execution reports
- Maintain test environment consistency

##  Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Contribution Guidelines
- Follow existing code style and patterns
- Add tests for new functionality
- Update documentation as needed
- Ensure all tests pass before submitting
- Provide clear commit messages





