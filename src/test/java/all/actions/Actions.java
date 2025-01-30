package all.actions;

import all.pages.*;
import all.utils.JsonUtils;
import org.openqa.selenium.WebDriver;
import all.utils.RandomUtils;

public class Actions {

    HomePage homePage;
    SignUpLoginPage signUpLoginPage;
    CartPage cartPage;
    DressProductsPage dressProductsPage;
    TshirtsProductsPage tshirtsProductsPage;

    /**
     * Constructor to initialize the Actions class with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used for interacting with web elements
     */
    public Actions(WebDriver driver) {
        homePage = new HomePage(driver);
        signUpLoginPage = new SignUpLoginPage(driver);
        cartPage = new CartPage(driver);
        dressProductsPage = new DressProductsPage(driver);
        tshirtsProductsPage = new TshirtsProductsPage(driver);
    }

    public boolean verifyHomePage() {
        return homePage.verifyHomePage();
    }

    public boolean verifySignUpLoginPage() {
        boolean results = homePage.verifyHomePage();
        if (results) {
            homePage.clickSignUpLogin();
            results = signUpLoginPage.verifySignUpLoginPage();
        }
        return results;
    }

    public boolean verifyLoggedInAsUserName(String username) {
        boolean result = signUpLoginPage.verifySignUpLoginPage();
        if (result) {
            signUpLoginPage.typeLoginEmailAndPassword(JsonUtils.readJsonFromFile("valid_email"), JsonUtils.readJsonFromFile("valid_password"));
            signUpLoginPage.clickLogin();
            result = signUpLoginPage.verifyLoggedInAsUsername(username);
        }
        return result;
    }

    public boolean verifyEmailAddressAlreadyExistsError(String email) {
        boolean result = signUpLoginPage.verifySignUpLoginPage();
        if (result) {
            signUpLoginPage.typeSignUpEmailAndPassword(email);
            signUpLoginPage.clickSignUp();
            result = signUpLoginPage.isEmailAddressAlreadyExistsErrorVisible();
        }
        return result;
    }

    public boolean verifyNavigatedToLoginPage() {
        homePage.clickLogout();
        return signUpLoginPage.verifySignUpLoginPage();
    }

    public boolean verifyCartSubscriptionText() {
        boolean result = homePage.verifyHomePage();
        if (result) {
            homePage.clickCart();
            result = cartPage.verifyCartPage();
            if (result) {
                result = cartPage.validateSubscriptionTextVisible();
            }
        }
        return result;
    }

    public boolean verifyCartSubscriptionSuccessMessage() {
        boolean result = cartPage.verifyCartPage();
        if (result) {
            String email = "user" + RandomUtils.getRandomInt(4) + "@domain.com";
            cartPage.enterEmailAndSubscribe(email);
            result = cartPage.validateSuccessSubscriptionMessage();
        }
        return result;
    }

    public boolean verifyCategoriesVisibleInLeftSidebar() {
        boolean result = homePage.verifyHomePage();
        if (result) {
            homePage.verifyCategoriesInLeftSidebar();
        }
        return result;
    }

    public boolean verifyWomenCategoryPageText() {
        boolean result = homePage.verifyHomePage();
        if (result) {
            homePage.clickWomanCategory();
            homePage.clickDressSubCategory();
            result = dressProductsPage.verifyDressProductsPage();
            if (result) {
                dressProductsPage.verifyWomenDressProductsText();
            }
        }
        return result;
    }

    public boolean verifyMenCategoryPage() {
        dressProductsPage.clickHome();
        boolean result = homePage.verifyHomePage();
        if (result) {
            homePage.clickMenCategory();
            homePage.clickTShirtsSubCategory();
            result = tshirtsProductsPage.verifyMenDressProductsText();
        }
        return result;
    }

    public boolean verifyHomeSubscriptionText() {
        boolean result = homePage.verifyHomePage();
        if (result) {
            homePage.scrollDownToBottom();
            result = homePage.validateSubscriptionTextVisible();
        }
        return result;
    }

    public boolean verifyScrollUpText() {
        boolean result = homePage.verifyHomePage();
        if (result) {
            homePage.clickScrollUpArrow();
            result = homePage.verifyTextIsVisibleAfterScrollUp();
        }
        return result;
    }
}
