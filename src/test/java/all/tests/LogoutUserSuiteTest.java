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

public class LogoutUserSuiteTest {

    private static final Logger logger = LogManager.getLogger(LogoutUserSuiteTest.class);
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
     * Verifies that the home page is visible.
     * Logs the result and asserts the visibility of the Home page.
     */
    @Test(priority = 1, description = "Testing the visibility of the Home page", groups = {"regression", "LogOutUser"})
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
     * Verifies that 'Login to your account' message is visible.
     * Logs the result and asserts the visibility of the message.
     */
    @Test(priority = 2, description = "Testing the visibility of 'Login to your account' message", groups = {"regression", "LogOutUser"})
    public void verifyLoginToYourAccount() {
        boolean result = actions.verifySignUpLoginPage();
        if (result) {
            logger.info("'Login to your account' is visible, test passed.");
        } else {
            logger.error("'Login to your account' is not visible, test failed.");
        }
        Assert.assertTrue(result, "'Login to your account' is not visible.");
    }

    /**
     * Verifies that 'Logged in as username' message is visible.
     * Compares the username from the configuration file with the UI.
     * Logs the result and asserts the visibility of the message.
     */
    @Test(priority = 3, description = "Testing the visibility of 'Logged in as username' message", groups = {"regression", "LogOutUser"})
    public void verifyLoggedInAsUsername() {
        String username = JsonUtils.readJsonFromFile("valid_name");
        boolean result = actions.verifyLoggedInAsUserName(username);

        if (result) {
            logger.info("'Logged in as {}' is visible, test passed.", username);
        } else {
            logger.error("'Logged in as {}' is not visible, test failed.", username);
        }
        Assert.assertTrue(result, "'Logged in as " + username + "' is not visible.");
    }

    /**
     * Verifies that the user is navigated to the login page after logging out.
     * Logs the result and asserts the navigation to the login page.
     */
    @Test(priority = 4, description = "Verify that user is navigated to the login page", groups = {"regression", "LogOutUser"})
    public void verifyUserNavigatedToLoginPage() {

        boolean result = actions.verifyNavigatedToLoginPage();

        if (result) {
            logger.info("User successfully navigated to the login page, test passed.");
        } else {
            logger.error("User did not navigate to the login page, test failed.");
        }
        Assert.assertTrue(result, "User is not navigated to the login page.");
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
