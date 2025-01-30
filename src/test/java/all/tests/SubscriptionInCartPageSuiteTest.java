package all.tests;

import all.actions.Actions;
import all.utils.JsonUtils;
import all.utils.GenerateDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubscriptionInCartPageSuiteTest {

    private static final Logger logger = LogManager.getLogger(SubscriptionInCartPageSuiteTest.class);
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
    @Test(priority = 1, description = "Testing the visibility of the Home page", groups = {"regression", "SubscriptionInCart"})
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
     * Verifies that the subscription text is visible in the cart page.
     * This test ensures the subscription text is displayed on the cart page.
     */
    @Test(priority = 2, description = "Testing the visibility of the subscription text in the cart", groups = {"regression", "SubscriptionInCart"})
    public void verifySubscriptionText() {
        boolean result = actions.verifyCartSubscriptionText();
        if (result) {
            logger.info("Subscription text is visible, test passed.");
        } else {
            logger.error("Subscription text is not visible, test failed.");
        }
        Assert.assertTrue(result, "The subscription text is not visible in the cart.");
    }

    /**
     * Verifies that the success message 'You have been successfully subscribed!' is visible.
     * This test checks if the subscription success message appears after completing the subscription.
     */
    @Test(priority = 3, description = "Verify success message 'You have been successfully subscribed!' is visible", groups = {"regression", "SubscriptionInCart"})
    public void verifySubscriptionSuccessMessage() {
        boolean result = actions.verifyCartSubscriptionSuccessMessage();
        if (result) {
            logger.info("Subscription success message is visible, test passed.");
        } else {
            logger.error("Subscription success message is not visible, test failed.");
        }
        Assert.assertTrue(result, "The subscription success message is not visible.");
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