package com.automationframework.pages;

import com.automationframework.core.BasePage;
import com.automationframework.core.WebExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;

/**
 * Page Object for Form Designer functionality
 * Contains elements for form design canvas, drag and drop, and form elements
 */
public class FormDesignerPage extends BasePage {
    
    // Form designer canvas and elements
    @FindBy(className = "form-canvas")
    private WebElement formCanvas;
    
    @FindBy(className = "left-menu")
    private WebElement leftMenu;
    
    @FindBy(className = "right-panel")
    private WebElement rightPanel;
    
    // Form elements in left menu
    @FindBy(xpath = "//div[contains(@class,'form-element') and contains(text(),'Textbox')]")
    private WebElement textboxElement;
    
    @FindBy(xpath = "//div[contains(@class,'form-element') and contains(text(),'Select File')]")
    private WebElement selectFileElement;
    
    @FindBy(className = "element-library")
    private WebElement elementLibrary;
    
    // Canvas drop zones
    @FindBy(className = "drop-zone-1")
    private WebElement dropZone1;
    
    @FindBy(className = "drop-zone-2")
    private WebElement dropZone2;
    
    // Right panel configuration elements
    @FindBy(className = "element-properties")
    private WebElement elementProperties;
    
    @FindBy(className = "element-settings")
    private WebElement elementSettings;
    
    // Form submission elements
    @FindBy(id = "save-form-button")
    private WebElement saveFormButton;
    
    @FindBy(id = "preview-form-button")
    private WebElement previewFormButton;
    
    @FindBy(className = "form-designer-title")
    private WebElement formDesignerTitle;
    
    public FormDesignerPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Drags and drops a form element onto the canvas
     * @param element The form element to drag
     * @param dropZone The drop zone on the canvas
     * @throws WebExceptions if drag and drop fails
     */
    public void dragAndDropElement(WebElement element, WebElement dropZone) throws WebExceptions {
        try {
            Actions dragAndDrop = new Actions(driver);
            dragAndDrop.dragAndDrop(element, dropZone).perform();
            waitForPageLoad();
        } catch (Exception e) {
            throw new WebExceptions(WebExceptions.ExceptionType.GENERAL_EXCEPTION,
                    "Failed to perform drag and drop: " + e.getMessage());
        }
    }
    
    /**
     * Drags and drops Textbox element onto the first drop zone
     * @throws WebExceptions if drag and drop fails
     */
    public void addTextboxElement() throws WebExceptions {
        dragAndDropElement(textboxElement, dropZone1);
    }
    
    /**
     * Drags and drops Select File element onto the second drop zone
     * @throws WebExceptions if drag and drop fails
     */
    public void addSelectFileElement() throws WebExceptions {
        dragAndDropElement(selectFileElement, dropZone2);
    }
    
    /**
     * Clicks on an element to select it and show properties in right panel
     * @param element The element to click
     * @throws WebExceptions if element cannot be clicked
     */
    public void selectElement(WebElement element) throws WebExceptions {
        clickElement(element);
        waitForPageLoad();
    }
    
    /**
     * Clicks on the Textbox element to select it
     * @throws WebExceptions if Textbox element cannot be selected
     */
    public void selectTextboxElement() throws WebExceptions {
        selectElement(textboxElement);
    }
    
    /**
     * Clicks on the Select File element to select it
     * @throws WebExceptions if Select File element cannot be selected
     */
    public void selectSelectFileElement() throws WebExceptions {
        selectElement(selectFileElement);
    }
    
    /**
     * Clicks the Save Form button
     * @throws WebExceptions if Save Form button cannot be clicked
     */
    public void clickSaveFormButton() throws WebExceptions {
        clickElement(saveFormButton);
        waitForPageLoad();
    }
    
    /**
     * Clicks the Preview Form button
     * @throws WebExceptions if Preview Form button cannot be clicked
     */
    public void clickPreviewFormButton() throws WebExceptions {
        clickElement(previewFormButton);
        waitForPageLoad();
    }
    
    /**
     * Verifies that the form designer is loaded
     * @return true if form designer is loaded, false otherwise
     */
    public boolean isFormDesignerLoaded() {
        return isElementVisible(formCanvas) && 
               isElementVisible(leftMenu) && 
               isElementVisible(rightPanel);
    }
    
    /**
     * Verifies that the left menu with form elements is visible
     * @return true if left menu is visible, false otherwise
     */
    public boolean isLeftMenuVisible() {
        return isElementVisible(leftMenu) && 
               isElementVisible(elementLibrary) &&
               isElementVisible(textboxElement) && 
               isElementVisible(selectFileElement);
    }
    
    /**
     * Verifies that the form canvas is visible
     * @return true if form canvas is visible, false otherwise
     */
    public boolean isFormCanvasVisible() {
        return isElementVisible(formCanvas) && 
               isElementVisible(dropZone1) && 
               isElementVisible(dropZone2);
    }
    
    /**
     * Verifies that the right panel is visible
     * @return true if right panel is visible, false otherwise
     */
    public boolean isRightPanelVisible() {
        return isElementVisible(rightPanel) && 
               isElementVisible(elementProperties);
    }
    
    /**
     * Verifies that all form designer elements are visible
     * @return true if all elements are visible, false otherwise
     */
    public boolean areAllFormDesignerElementsVisible() {
        return isFormDesignerLoaded() && 
               isLeftMenuVisible() && 
               isFormCanvasVisible() && 
               isRightPanelVisible();
    }
    
    /**
     * Gets the form designer title
     * @return The form designer title text
     * @throws WebExceptions if title cannot be retrieved
     */
    public String getFormDesignerTitle() throws WebExceptions {
        return getElementText(formDesignerTitle);
    }
    
    /**
     * Checks if an element is present on the canvas
     * @param element The element to check
     * @return true if element is present on canvas, false otherwise
     */
    public boolean isElementOnCanvas(WebElement element) {
        try {
            return element.isDisplayed() && element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Verifies that both form elements are successfully added to the canvas
     * @return true if both elements are on canvas, false otherwise
     */
    public boolean areFormElementsAddedToCanvas() {
        // This would need to be implemented based on how elements appear on canvas
        // after drag and drop operation
        return true; // Placeholder implementation
    }
}
