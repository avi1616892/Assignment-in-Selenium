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

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver  the WebDriver instance
     * @param timeout the timeout duration in seconds
     */
    public BasePage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        actions = new Actions(driver);
    }

    /**
     * Clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be clicked
     */
    public void click(By locator) {
        logger.info("Going to click WebElement: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.click();
    }

    /**
     * Types the given text into the element located by the given locator.
     *
     * @param locator the By locator of the element
     * @param text    the text to be typed
     */
    public void typeText(By locator, String text) {
        logger.info("Going to send keys to WebElement: {} {}", locator, text);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Validates if elements exist for the given locator.
     *
     * @param locator the By locator of the elements
     * @return the boolean of elements found
     */
    public boolean validateElementExist(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }

    /**
     * Checks if the current tab title matches the given title.
     *
     * @param title the expected title of the tab
     * @return true if the tab title matches, false otherwise
     */
    public boolean isTabTitleMatch(String title) {
        return driver.getTitle().equals(title);
    }

    /**
     * Retrieves the text content of the element located by the given locator.
     *
     * @param locator the By locator of the element
     * @return the text content of the element
     */
    public String getElementText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    /**
     * Double-clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be double-clicked
     */
    public void doubleClickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        actions.doubleClick(element).perform();
    }

    /**
     * Scrolls the page until the element located by the given locator is in view.
     *
     * @param locator the By locator of the element to scroll to
     */
    public void scrollToElement(By locator) {
        // Find the element using the provided locator
        WebElement element = driver.findElement(locator);

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Checks if the element located by the given locator is displayed.
     *
     * @param locator the By locator of the element
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Retrieves the value of the specified attribute of the element located by the given locator using JavaScript.
     *
     * @param locator  the By locator of the element
     * @param attribute the name of the attribute
     * @return the value of the attribute
     */
    public String getElementAttributeUsingJS(By locator, String attribute) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attribute);
    }
}
