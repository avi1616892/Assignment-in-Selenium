package example2.actions;

import example2.pages.ForgotPasswordPage;
import example2.pages.RegistrationFormPage;
import example2.pages.TableTaskPage;
import org.openqa.selenium.WebDriver;

public class Actions {

    TableTaskPage tableTaskPage;
    ForgotPasswordPage forgotPassword;

    RegistrationFormPage registrationForm;

    /**
     * Constructor to initialize the Actions class with a WebDriver instance.
     *
     * @param driver the WebDriver instance to be used for interacting with web elements
     */
    public Actions(WebDriver driver) {
        forgotPassword = new ForgotPasswordPage(driver);
        registrationForm = new RegistrationFormPage(driver);
    }

    /**
     * Performs the forgot password action by typing the email, clicking submit, and validating the result.
     *
     * @return true if the forgot password functionality is validated successfully, false otherwise
     */
    public boolean doForgotPassword() {
        forgotPassword.typeEmail("test@test.com");
        forgotPassword.clickSubmit();
        return forgotPassword.validateForgotPassword();
    }

    /**
     * Performs the registration form action by filling the contact name, date, and clicking submit.
     *
     * @param name the contact name to be filled
     * @param date the date to be filled
     */
    public boolean doRegistrationForm(String name, String date) {
        registrationForm.fillContactName(name);
        registrationForm.fillDate(date);
        registrationForm.clickSubmit();
        return true;
    }
}