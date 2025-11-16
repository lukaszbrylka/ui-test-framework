package com.lukasz.ui.steps;

import com.lukasz.ui.models.UserDTO;
import com.lukasz.ui.pages.HomePage;
import com.lukasz.ui.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private final WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
    }

    public LoginSteps goToLoginPage() {
        this.loginPage = homePage.goToLoginPage();
        return this;
    }

    public LoginSteps submitLogin(UserDTO user) {
        loginPage.enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickLogin();
        homePage = new HomePage(driver);
        return this;
    }

    public LoginSteps verifyUserIsLoggedIn(String expectedUsername) {
        if (!homePage.isUserLoggedIn()) {
            throw new AssertionError("User should be logged in");
        }
        if (!expectedUsername.equals(homePage.getUserNameFromNavBar())) {
            throw new AssertionError("Username in navbar should match expected");
        }
        return this;
    }

    public LoginSteps verifyErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessageText().trim();
        if (!expectedMessage.equals(actualMessage)) {
            throw new AssertionError("Expected error message: " + expectedMessage + ", but got: " + actualMessage);
        }
        return this;
    }
}