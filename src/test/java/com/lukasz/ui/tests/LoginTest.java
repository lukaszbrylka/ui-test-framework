package com.lukasz.ui.tests;

import com.lukasz.ui.data.UserFactory;
import com.lukasz.ui.models.UserDTO;
import com.lukasz.ui.steps.LoginSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    private UserDTO existingUser;

    @BeforeEach
    void generateUser() {
        existingUser = UserFactory.getExistingUser();
    }

    @Test
    void testExistingUserSuccessfulLogin() {
        new LoginSteps(driver)
                .goToLoginPage()
                .submitLogin(existingUser)
                .verifyUserIsLoggedIn(existingUser.getUsername());
    }

    @Test
    void testExistingUserInvalidPasswordUnsuccessfulLogin() {
        existingUser.setPassword("invalid");

        new LoginSteps(driver)
                .goToLoginPage()
                .submitLogin(existingUser)
                .verifyErrorMessage("Wrong email/password combination");
    }
}