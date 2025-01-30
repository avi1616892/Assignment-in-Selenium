package all.tests;

import all.actions.Actions;
import all.utils.GenerateDriver;
import all.utils.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScrollArrowButtonSuiteTest {

    private static final Logger logger = LogManager.getLogger(ScrollArrowButtonSuiteTest.class);
    WebDriver driver;
    Actions actions;

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(description = "setting up the test environment", alwaysRun = true)
    public void setUp() {
        String URL_FOR_SUBSCRIPTION = JsonUtils.readJsonFromFile("url_for_subscription");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        logger.debug("WebDriver setup complete: {}, {}", BROWSER, URL_FOR_SUBSCRIPTION);
        driver = GenerateDriver.initDriver(BROWSER, URL_FOR_SUBSCRIPTION);
        actions = new Actions(driver);
    }

    /**
     * Verify that the home page is visible successfully.
     */
    @Test(priority = 1, description = "Testing the visibility of the Home page", groups = {"regression", "ScrollArrowButton"})
    public void verifyHomePage() {
        boolean result = actions.verifyHomePage();
        if (result) {
            logger.info("Home page is visible, test passed.");
        } else {
            logger.error("Home page is not visible, test failed.");
        }
        Assert.assertTrue(result, "The Home page is not visible.");
    }

    /**
     * Verifies that the subscription text is visible on the home page.
     * This test ensures the subscription text is displayed after scrolling to the bottom of the page.
     */
    @Test(priority = 2, description = "Testing the visibility of the subscription text on the home page", groups = {"regression", "ScrollArrowButton"})
    public void verifyHomeSubscriptionText() {
        boolean result = actions.verifyHomeSubscriptionText();
        if (result) {
            logger.info("Subscription text is visible, test passed.");
        } else {
            logger.error("Subscription text is not visible, test failed.");
        }
        Assert.assertTrue(result, "The subscription text is not visible on the home page.");
    }

    /**
     * Verifies that the page scrolls up and the 'Full-Fledged practice website for Automation Engineers' text is visible.
     */
    @Test(priority = 3, description = "Testing the scroll up functionality using the arrow button", groups = {"regression", "ScrollArrowButton"})
    public void verifyScrollUpText() {
        boolean result = actions.verifyScrollUpText();
        if (result) {
            logger.info("Text 'Full-Fledged practice website for Automation Engineers' is visible after scroll up, test passed.");
        } else {
            logger.error("Text 'Full-Fledged practice website for Automation Engineers' is not visible after scroll up, test failed.");
        }
        Assert.assertTrue(result, "The text is not visible after scrolling up.");
    }

    /**
     * Cleans up resources after the suite execution is complete.
     * Quits WebDriver.
     */
    @AfterSuite
    public void tearDown() {
        GenerateDriver.cleanDriver(driver);
    }
}

