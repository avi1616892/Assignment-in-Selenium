package all.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class DressProductsPage extends BasePage {

    /**
     * Constructor to initialize the DressProductsPage with WebDriver.
     *
     * @param driver the WebDriver instance
     */
    public DressProductsPage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * Verifies if the Dress Products page is loaded by checking the page title.
     *
     * @return true if the title matches "Automation Exercise - Dress Products", false otherwise
     */
    public boolean verifyDressProductsPage() {
        return isTabTitleMatch("Automation Exercise - Dress Products");
    }

    /**
     * Verifies if the 'Women - Dress Products' text is visible on the page.
     */
    public void verifyWomenDressProductsText() {
        validateElementExist(By.xpath("//h2[@class='title text-center' and contains(text(),'Women - Dress Products')]"));
    }

    /**
     * Clicks on the Home icon to navigate to the home page.
     */
    public void clickHome() {
        click(By.xpath("//i[@class='fa fa-home']"));
    }
}
