package all.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * BasePage provides common helper methods for interacting with web pages.
 * This class includes methods for clicking elements, entering text,
 * verifying element visibility, scrolling, and other utilities.
 */
public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    /**
     * Constructor to initialize BasePage with WebDriver and timeout.
     *
     * @param driver WebDriver instance to interact with the web page
     * @param timeout Maximum wait time in seconds for explicit waits
     */
    public BasePage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        actions = new Actions(driver);
        logger.info("BasePage initialized with timeout: {} seconds", timeout);
    }

    /**
     * Clicks on a web element identified by the given locator.
     *
     * @param locator The By locator of the element to be clicked
     */
    public void click(By locator) {
        logger.info("Attempting to click WebElement: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.click();
        logger.info("Clicked WebElement: {}", locator);
    }

    /**
     * Types the given text into a web element.
     *
     * @param locator The By locator of the element
     * @param text The text to be entered
     */
    public void typeText(By locator, String text) {
        logger.info("Entering text '{}' into WebElement: {}", text, locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
        logger.info("Text entered into WebElement: {}", locator);
    }

    /**
     * Checks if an element exists on the page.
     *
     * @param locator The By locator of the element
     * @return true if the element exists, false otherwise
     */
    public boolean validateElementExist(By locator) {
        logger.info("Validating existence of WebElement: {}", locator);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        List<WebElement> elements = driver.findElements(locator);
        boolean exists = !elements.isEmpty();
        logger.info("Validation result for WebElement {}: {}", locator, exists);
        return exists;
    }

    /**
     * Verifies if the current tab title matches the expected title.
     *
     * @param title The expected tab title
     * @return true if the title matches, false otherwise
     */
    public boolean isTabTitleMatch(String title) {
        logger.info("Checking if tab title matches: {}", title);
        boolean matches = driver.getTitle().equals(title);
        logger.info("Tab title match result: {}", matches);
        return matches;
    }

    /**
     * Retrieves the text content of a web element.
     *
     * @param locator The By locator of the element
     * @return The text content of the element
     */
    public String getElementText(By locator) {
        logger.info("Retrieving text from WebElement: {}", locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String text = driver.findElement(locator).getText();
        logger.info("Text retrieved from WebElement {}: {}", locator, text);
        return text;
    }

    /**
     * Performs a double-click action on a web element.
     *
     * @param locator The By locator of the element to be double-clicked
     */
    public void doubleClickElement(By locator) {
        logger.info("Double-clicking WebElement: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        actions.doubleClick(element).perform();
        logger.info("Double-clicked WebElement: {}", locator);
    }

    /**
     * Scrolls to a web element using JavaScript.
     *
     * @param locator The By locator of the element to scroll to
     */
    public void scrollToElement(By locator) {
        logger.info("Scrolling to WebElement: {}", locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to WebElement: {}", locator);
    }

    /**
     * Checks if a web element is displayed on the page.
     *
     * @param locator The By locator of the element
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        logger.info("Checking if WebElement is displayed: {}", locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("WebElement is displayed: {}", locator);
            return true;
        } catch (Exception e) {
            logger.info("WebElement is NOT displayed: {}", locator);
            return false;
        }
    }

    /**
     * Retrieves an attribute value from a web element using JavaScript.
     *
     * @param locator The By locator of the element
     * @param attribute The attribute name to retrieve
     * @return The value of the specified attribute
     */
    public String getElementAttributeUsingJS(By locator, String attribute) {
        logger.info("Getting attribute '{}' from WebElement: {}", attribute, locator);
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String value = (String) js.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
        logger.info("Retrieved attribute '{}' from WebElement {}: {}", attribute, locator, value);
        return value;
    }
}
