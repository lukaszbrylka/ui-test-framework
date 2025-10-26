package com.lukasz.ui.tests;

import com.lukasz.ui.data.UserFactory;
import com.lukasz.ui.driver.DriverManager;
import com.lukasz.ui.models.UserDTO;
import com.lukasz.ui.pages.HomePage;
import com.lukasz.ui.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    private UserDTO existingUser;

    @BeforeEach
    void generateUser() {
        existingUser = UserFactory.getExistingUser();
    }

    @Test
    void testExistingUserSuccessfulLogin() {
        HomePage loggedInHomePage = new HomePage(DriverManager.driver())
                .goToLoginPage()
                .submitLogin(existingUser);

        assertTrue(loggedInHomePage.isUserLoggedIn(), "User should be logged in");
        assertEquals(existingUser.getUsername(), loggedInHomePage.getUserNameFromNavBar(),
                "Username in navbar should match expected");
    }

    @Test
    void testExistingUserInvalidPasswordUnsuccessfulLogin() {
        existingUser.setPassword("invalid");

        LoginPage loginPage = new HomePage(DriverManager.driver())
                .goToLoginPage();

        loginPage.submitLogin(existingUser);

        String actualErrorMessageText = loginPage.getErrorMessageText().trim();
        String expectedErrorMessageText = "Wrong email/password combination";
        assertEquals(expectedErrorMessageText, actualErrorMessageText,
                "Error message should indicate wrong credentials");
    }
}