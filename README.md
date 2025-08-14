# Message Box Task Automation Framework

A comprehensive UI automation framework for automating Message Box Task creation using Selenium WebDriver, TestNG, and following the Page Object Model (POM) design pattern.

##  Use Cases

### Use Case 1: Message Box Task (UI Automation)

#### Steps:
1. Log in to the application
2. Navigate to Automation from the left-hand menu
3. Click on the Create dropdown and select Task Bot
4. Fill in all mandatory details and click the Create button
5. In the Actions panel, search for Message Box and double-click to add it
6. On the right panel, verify every UI element interaction
7. Save the configuration

#### Expectations:
- Automate the flow for creating a message box task
- Use POM for organizing elements and actions
- Assert the following:
  - UI element visibility
  - Proper data entry
  - Successful creation and confirmation messages
  - Full functional flow validation

### Use Case 2: Form with Upload Flow (UI Automation)

#### Steps:
1. Log in to the application
2. Navigate to Automation from the left-hand menu
3. Click on the Create dropdown and select Form
4. Fill in all mandatory details and click the Create button
5. From the left menu, drag and drop the Textbox and Select File elements onto the canvas
6. Click on each element and verify all UI interactions in the right panel
7. Enter text in the textbox and upload a document from your shared folder
8. Save the form and verify whether the document is uploaded successfully

#### Expectations:
- Build and automate a form with:
  - A Text Box input field
  - A File Upload control
- Automate the following:
  - Text input interaction
  - File selection from a shared folder
  - File upload process and completion
- Assert the following:
  - UI element visibility and functionality
  - File upload status and confirmation
  - Form submission behavior and backend response

### Use Case 3: Learning Instance API Flow (API Automation)

#### Steps:
1. Perform login using the provided credentials
2. After login, navigate to learning instance under AI tab
3. Create a Learning Instance
4. Validate the created instance with appropriate checks

#### Expectations:
- Identify required API endpoints using the browser Network tab
- Perform the following validations:
  - HTTP status code (e.g., 200 OK, 201 Created)
  - Response time (optional but preferred)
  - Response body schema and field-level checks (e.g., ID, name, status)
  - Functional accuracy (e.g., instance created with correct data and status)

##  Architecture

### Design Patterns
- **Page Object Model (POM)**: Organizes page elements and actions
- **Factory Pattern**: Manages WebDriver creation and configuration
- **DRY Principle**: Eliminates code duplication through base classes

### Framework Structure
```
src/
├── main/java/com/automationframework/
│   ├── core/                          # Core framework classes
│   │   ├── BasePage.java             # Abstract base page class
│   │   ├── BaseTest.java             # Abstract base test class
│   │   ├── WebDriverFactory.java     # WebDriver factory
│   │   └── WebExceptions.java        # Custom exception handling
│   ├── pages/                         # Page Object classes
│   │   ├── LoginPage.java            # Login page
│   │   ├── DashboardPage.java        # Dashboard page
│   │   ├── AutomationPage.java       # Automation page
│   │   ├── TaskBotPage.java          # Task Bot creation page
│   │   └── TaskBotActionsPage.java   # Task Bot actions page
│   └── utils/                         # Utility classes
│       ├── ConfigManager.java         # Configuration management
│       └── TestDataProvider.java      # Test data provider
├── test/java/com/automationframework/
│   └── tests/
│       └── MessageBoxTaskTest.java    # Main test class
└── resources/
    ├── config.properties              # Configuration properties
    └── testng.xml                    # TestNG configuration
```

## Features

### Core Framework
- **WebDriver Factory**: Supports Chrome, Firefox, Edge, and Safari
- **Base Classes**: Common functionality for pages and tests
- **Exception Handling**: Custom WebExceptions with specific types
- **Configuration Management**: Centralized configuration via properties file

### Page Objects
- **LoginPage**: User authentication functionality
- **DashboardPage**: Navigation and dashboard actions
- **AutomationPage**: Automation section management
- **TaskBotPage**: Task Bot creation and configuration
- **TaskBotActionsPage**: Message Box action management
- **FormPage**: Form creation and configuration
- **FormDesignerPage**: Form design canvas and drag & drop functionality
- **FormPreviewPage**: Form preview, text input, and file upload

### API Classes
- **ApiBaseClass**: Base API functionality and utilities
- **LearningInstanceApi**: Learning Instance specific API operations

### Test Framework
- **TestNG Integration**: Test execution and reporting
- **Cross-browser Testing**: Support for multiple browsers
- **Parallel Execution**: Configurable parallel test execution
- **Comprehensive Assertions**: UI element visibility and functional validation

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Chrome, Firefox, Edge, or Safari browser
- Internet connection for WebDriver downloads

## Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd MSH_UI_API_FRAMEWORK
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Update configuration**
   - Edit `src/main/resources/config.properties`
   - Update application URLs and test credentials
   - Configure browser preferences

##  Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=MessageBoxTaskTest
```

### Run with specific browser
```bash
mvn test -Dbrowser=firefox
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
- Test execution summary
- Passed/failed test details
- Execution time and statistics
- Screenshots on failure (if configured)

## Configuration

### Browser Configuration
```properties
browser.default=chrome
browser.headless=false
browser.maximize=true
```

### Timeout Configuration
```properties
webdriver.timeout.implicit=10
webdriver.timeout.explicit=10
webdriver.timeout.pageLoad=30
webdriver.timeout.script=30
```

### Test Data Configuration
```properties
test.username=testuser@example.com
test.password=TestPassword123!
test.task.name=Message Box Automation Task
```

## Customization

### Adding New Pages
1. Create a new class extending `BasePage`
2. Define page elements using `@FindBy` annotations
3. Implement page-specific actions and validations

### Adding New Tests
1. Create a new class extending `BaseTest`
2. Implement the `navigateToApplication()` method
3. Add test methods with appropriate assertions

### Adding New Browsers
1. Update `WebDriverFactory.createDriver()` method
2. Add browser-specific options and configurations
3. Update TestNG configuration if needed

##  Test Cases

#### Message Box Task Tests
1. **Complete Message Box Task Creation Flow**
   - End-to-end automation of the complete workflow
   - Validates all steps from login to configuration save

2. **UI Element Visibility Validation**
   - Ensures all required UI elements are visible
   - Validates element presence at each step

3. **Functional Flow Validation**
   - Verifies complete functional flow and expected behaviors
   - Validates successful completion of all operations

#### Form with Upload Tests
4. **Complete Form with Upload Flow**
   - End-to-end automation of form creation, design, and submission
   - Validates drag & drop, text input, and file upload

5. **Form Creation and Design Validation**
   - Validates form creation, design elements, and canvas functionality
   - Ensures proper drag & drop behavior

6. **File Upload and Form Submission Validation**
   - Validates file upload functionality and form submission behavior
   - Verifies backend response and upload confirmation

#### Learning Instance API Tests
7. **Complete Learning Instance API Flow**
   - End-to-end API automation for Learning Instance creation and validation
   - Validates login, navigation, creation, and response validation

8. **Learning Instance Creation with Different Data Types**
   - Validates creation with default, custom, and random test data
   - Ensures robust API testing with various input scenarios

9. **Learning Instance CRUD Operations**
   - Validates Create, Read, Update, and Delete operations
   - Ensures complete API functionality coverage

##  Troubleshooting

### Common Issues
1. **WebDriver not found**: Ensure WebDriverManager dependency is included
2. **Element not found**: Check element locators and page loading
3. **Timeout issues**: Adjust wait timeouts in configuration
4. **Browser compatibility**: Verify browser version compatibility

### Debug Mode
Enable debug logging by setting:
```properties
logging.level=DEBUG
```

## Best Practices

### Code Organization
- Follow POM design pattern strictly
- Use meaningful names for page objects and methods
- Implement proper exception handling
- Add comprehensive JavaDoc comments

### Test Design
- Keep tests independent and isolated
- Use descriptive test method names
- Implement proper setup and teardown
- Add meaningful assertions and validations

### Maintenance
- Regular updates of WebDriver versions
- Periodic review of element locators
- Update test data as application changes
- Monitor test execution reports

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

##  License

This project is licensed under the MIT License - see the LICENSE file for details.


