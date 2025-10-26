package com.lukasz.ui.pages;

import com.lukasz.ui.models.UserDTO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Duration SHORT_WAIT = Duration.ofSeconds(10);

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;

    @FindBy(xpath = "//h1[text()='Sign in']")
    private WebElement loginHeadline;

    @FindBy(xpath = "//ul[@class='error-messages']/li")
    private WebElement errorMessageContainer;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, SHORT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public HomePage clickLogin() {
        loginButton.click();
        return new HomePage(driver);
    }

    public HomePage submitLogin(UserDTO user) {
        return enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickLogin();
    }

    public boolean isLoginPageVisible() {
        wait.until(ExpectedConditions.visibilityOf(loginHeadline));
        return loginHeadline.isDisplayed();
    }

    public String getErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageContainer));
        return errorMessageContainer.getText();
    }
}