package all.pages;

import all.utils.JsonUtils;
import all.utils.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpLoginPage extends BasePage {

    /**
     * Constructor to initialize the SignUpLoginPage with WebDriver.
     *
     * @param driver the WebDriver instance
     */
    public SignUpLoginPage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * Types the login email and password into the respective fields.
     *
     * @param email    the email address to be typed
     * @param password the password to be typed
     */
    public void typeLoginEmailAndPassword(String email, String password) {
        typeText(By.xpath("//input[@data-qa=\"login-email\"]"), email);
        typeText(By.xpath("//input[@data-qa=\"login-password\"]"), password);
    }

    /**
     * Types the signup email and a generated username into the respective fields.
     *
     * @param email the email address to be typed
     */
    public void typeSignUpEmailAndPassword(String email) {
        String name = "user" + RandomUtils.getRandomInt(4);
        typeText(By.xpath("//input[@data-qa=\"signup-name\"]"), name);
        typeText(By.xpath("//input[@data-qa=\"signup-email\"]"), email);
    }

    /**
     * Clicks the signup button to initiate the signup process.
     */
    public void clickSignUp() {
        click(By.xpath("//button[@data-qa=\"signup-button\"]"));
    }

    /**
     * Clicks the login button to initiate the login process.
     */
    public void clickLogin() {
        click(By.xpath("//button[@data-qa=\"login-button\"]"));
    }

    /**
     * Verifies if the user is logged in by checking if the username matches the expected username.
     *
     * @param expectedUsername the expected username
     * @return true if logged in as the expected username, false otherwise
     */
    public boolean verifyLoggedInAsUsername(String expectedUsername) {
        String expectedUserName = JsonUtils.readJsonFromFile("valid_name");

        boolean isLoggedInTextPresent = validateElementExist(By.xpath("//a[text()=' Logged in as ']"));
        boolean isUsernamePresent = validateElementExist(By.xpath("//b[text()='" + expectedUserName + "']"));

        return isLoggedInTextPresent && isUsernamePresent;
    }

    /**
     * Verifies if the SignUp/Login page is displayed by checking the presence of 'Login' and 'SignUp' headings.
     *
     * @return true if both 'Login' and 'SignUp' sections are present, false otherwise
     */
    public boolean verifySignUpLoginPage() {
        boolean login = validateElementExist(By.xpath("//h2[text()='Login to your account']"));
        boolean signUp = validateElementExist(By.xpath("//h2[text()='New User Signup!']"));
        return login && signUp;
    }

    /**
     * Verifies if the error message is visible when the email address already exists.
     *
     * @return true if the error message is visible, false otherwise
     */
    public boolean isEmailAddressAlreadyExistsErrorVisible() {
        return validateElementExist(By.xpath("//p[text()='Email Address already exist!']"));
    }
}
