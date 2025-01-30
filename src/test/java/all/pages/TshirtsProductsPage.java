package all.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class TshirtsProductsPage extends BasePage {

    /**
     * Constructor to initialize the T-ShirtsProductsPage with WebDriver.
     *
     * @param driver the WebDriver instance
     */
    public TshirtsProductsPage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * Verifies if the T-shirts products page is displayed by checking the tab title.
     *
     * @return true if the tab title matches, false otherwise
     */
    public boolean verifyMenDressProductsText() {
        return isTabTitleMatch("Automation Exercise - Tshirts Products");
    }
}
