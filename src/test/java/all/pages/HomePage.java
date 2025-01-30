package all.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    /**
     * Constructor to initialize the HomePage with WebDriver.
     *
     * @param driver the WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * Verifies if the home page is loaded by checking the page title.
     *
     * @return true if the title matches "Automation Exercise", false otherwise
     */
    public boolean verifyHomePage() {
        return isTabTitleMatch("Automation Exercise");
    }

    /**
     * Clicks on the Sign Up / Login link.
     */
    public void clickSignUpLogin() {
        click(By.xpath("//a[@href=\"/login\"]"));
    }

    /**
     * Clicks on the Logout link.
     */
    public void clickLogout() {
        click(By.xpath("//a[@href='/logout']"));
    }

    /**
     * Clicks on the Cart link.
     */
    public void clickCart() {
        click(By.xpath("//*[text()=' Cart']"));
    }

    /**
     * Verifies if the 'Category' section is visible in the left sidebar.
     */
    public void verifyCategoriesInLeftSidebar() {
        validateElementExist(By.xpath("//div[@class='left-sidebar']//h2[text()='Category']"));
    }

    /**
     * Clicks on the 'Women' category in the sidebar and scrolls to it if necessary.
     */
    public void clickWomanCategory() {
        scrollToElement(By.xpath("//a[@data-toggle='collapse' and @href='#Women']"));
        click(By.xpath("//a[@data-toggle='collapse' and @href='#Women']"));
    }

    /**
     * Clicks on the 'Dress' subcategory under the 'Women' category.
     */
    public void clickDressSubCategory() {
        click(By.xpath("//a[@href='/category_products/1']"));
    }

    /**
     * Clicks on the 'Men' category in the sidebar and scrolls to it if necessary.
     */
    public void clickMenCategory() {
        scrollToElement(By.xpath("//a[@data-toggle='collapse' and @href='#Men']"));
        click(By.xpath("//a[@data-toggle='collapse' and @href='#Men']"));
    }

    /**
     * Clicks on the 'T-Shirts' subcategory under the 'Men' category.
     */
    public void clickTShirtsSubCategory() {
        click(By.xpath("//a[@href='/category_products/3']"));
    }

    /**
     * Scrolls the page to the bottom.
     */
    public void scrollDownToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Verifies if the 'Subscription' section is visible.
     *
     * @return true if the 'Subscription' section is visible, false otherwise
     */
    public boolean validateSubscriptionTextVisible() {
        return validateElementExist(By.xpath("//h2[text()='Subscription']"));
    }

    /**
     * Clicks on the scroll-up arrow.
     */
    public void clickScrollUpArrow() {
        click(By.xpath("//a[@id='scrollUp']"));
    }

    /**
     * Verifies if the text "Full-Fledged practice website for Automation Engineers" is visible
     * after scrolling up the page.
     *
     * @return true if the text is visible, false otherwise
     */
    public boolean verifyTextIsVisibleAfterScrollUp() {
        return validateElementExist(By.xpath("//h2[text()='Full-Fledged practice website for Automation Engineers']"));
    }
}
