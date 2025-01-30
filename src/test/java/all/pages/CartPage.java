package all.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    /**
     * Constructor to initialize the CartPage with WebDriver.
     *
     * @param driver the WebDriver instance
     */
    public CartPage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * Verifies if the Cart page is loaded by checking the page title.
     *
     * @return true if the title matches "Automation Exercise - Checkout", false otherwise
     */
    public boolean verifyCartPage() {
        return isTabTitleMatch("Automation Exercise - Checkout");
    }

    /**
     * Verifies if the 'Subscription' text is visible on the Cart page.
     * Scrolls to the 'Subscription' section before checking its visibility.
     *
     * @return true if the 'Subscription' section is visible, false otherwise
     */
    public boolean validateSubscriptionTextVisible() {
        scrollToElement(By.xpath("//h2[text()='Subscription']"));
        return validateElementExist(By.xpath("//h2[text()='Subscription']"));
    }

    /**
     * Enters the provided email address and subscribes to the newsletter.
     *
     * @param email the email address to subscribe
     */
    public void enterEmailAndSubscribe(String email) {
        typeText(By.xpath("//input[@type='email']"), email);
        click(By.xpath("//button[@type='submit']"));
    }

    /**
     * Verifies if the success message for subscription is visible after subscribing.
     *
     * @return true if the success message is visible, false otherwise
     */
    public boolean validateSuccessSubscriptionMessage() {
        return validateElementExist(By.xpath("//*[contains(text(),'You have been successfully subscribed!')]"));
    }
}
