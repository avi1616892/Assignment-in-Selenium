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

public class RegisterUserWithExistingEmailSuiteTest {

    private static final Logger logger = LogManager.getLogger(RegisterUserWithExistingEmailSuiteTest.class);
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
     * Verifies that the home page is visible successfully.
     * Logs the result and asserts the visibility of the Home page.
     */
    @Test(priority = 1, description = "Testing the visibility of the Home page", groups = {"regression", "RegisterUser"})
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
     * Verifies that 'New User Signup!' message is visible.
     * Logs the result and asserts the visibility of the message.
     */
    @Test(priority = 2, description = "Testing the visibility of 'New User Signup!' message", groups = {"regression", "RegisterUser"})
    public void verifyNewUserSignup() {
        boolean result = actions.verifySignUpLoginPage();
        if (result) {
            logger.info("'New User Signup!' is visible, test passed.");
        } else {
            logger.error("'New User Signup!' is not visible, test failed.");
        }
        Assert.assertTrue(result, "'New User Signup!' is not visible.");
    }

    /**
     * Verifies that the 'Email Address already exists!' error message is visible
     * when attempting to register with an already existing email address.
     * Uses dataProvider to test multiple email cases.
     */
    @Test(priority = 3, description = "Verify 'Email Address already exists!' error", groups = {"regression", "RegisterUser"}, dataProvider = "emailData", dataProviderClass = TestDataProvider.class)
    public void verifyEmailAddressAlreadyExistsError(String email) {
        boolean result = actions.verifyEmailAddressAlreadyExistsError(email);
        if (result) {
            logger.info("'Email Address already exists!' error is visible for email: " + email + ", test passed.");
        } else {
            logger.error("'Email Address already exists!' error is not visible for email: " + email + ", test failed.");
        }
        Assert.assertTrue(result, "'Email Address already exists!' error not visible.");
    }

    /**
     * Cleans up resources after the suite execution is complete.
     * Quits the WebDriver session.
     */
    @AfterSuite
    public void tearDown() {
        GenerateDriver.cleanDriver(driver);
    }
}
