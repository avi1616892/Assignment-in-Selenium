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

public class ViewCategoryProductsSuiteTest {

    private static final Logger logger = LogManager.getLogger(ViewCategoryProductsSuiteTest.class);
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
     * Verifies that the categories are visible on the left sidebar and performs category navigation.
     * This test ensures the presence of categories, clicks on them, and verifies navigation to the appropriate category pages.
     */
    @Test(priority = 1, description = "Verify categories visibility and navigation", groups = {"regression", "ViewCategoryProducts"})
    public void verifyCategoriesVisibleInLeftSidebar() {
        boolean result = actions.verifyCategoriesVisibleInLeftSidebar();
        if (result) {
            logger.info("Categories are visible on the left sidebar, test passed.");
        } else {
            logger.error("Categories are not visible on the left sidebar, test failed.");
        }
        Assert.assertTrue(result, "Categories are not visible on the left sidebar.");
    }

    /**
     * Verify that the Women category page is displayed and confirm the text 'WOMEN - TOPS PRODUCTS'
     */
    @Test(priority = 2, description = "Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'", groups = {"regression", "ViewCategoryProducts"})
    public void verifyWomenCategoryPageText() {
        boolean result = actions.verifyWomenCategoryPageText();
        if (result) {
            logger.info("'WOMEN - TOPS PRODUCTS' text is displayed, test passed.");
        } else {
            logger.error("'WOMEN - TOPS PRODUCTS' text is not displayed, test failed.");
        }
        Assert.assertTrue(result, "The 'WOMEN - TOPS PRODUCTS' text is not displayed.");
    }

    /**
     /**
     * Verify that the Men category page is displayed and confirm the text 'MEN - T-SHIRTS PRODUCTS'
     */
    @Test(priority = 3, description = "Verify that Men category page is displayed and confirm text 'MEN - T-SHIRTS PRODUCTS'", groups = {"regression", "ViewCategoryProducts"})
    public void verifyManCategoryPage() {
        boolean result = actions.verifyMenCategoryPage();
        if (result) {
            logger.info("'MEN - T-SHIRTS PRODUCTS' text is displayed, test passed.");
        } else {
            logger.error("'MEN - T-SHIRTS PRODUCTS' text is not displayed, test failed.");
        }
        Assert.assertTrue(result, "The 'MEN - T-SHIRTS PRODUCTS' text is not displayed.");
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