package com.lukasz.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Duration SHORT_WAIT = Duration.ofSeconds(10);

    @FindBy(xpath = "//li/a[@href='#/']")
    private WebElement homeNavBarButton;

    @FindBy(xpath = "//li/a[@href='#/register']")
    private WebElement signUpNavBarButton;

    @FindBy(xpath = "//li/a[@href='#/login']")
    private WebElement loginNavBarButton;

    @FindBy(xpath = "//div[@class='home-page']/div[@class='banner']")
    private WebElement homePageBanner;

    @FindBy(xpath = "//li/div[./img[@class='user-pic']]")
    private WebElement userAvatar;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, SHORT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public HomePage clickHomeNavBar() {
        homeNavBarButton.click();
        return this;
    }

    public LoginPage goToLoginPage() {
        loginNavBarButton.click();
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        wait.until(ExpectedConditions.visibilityOf(userAvatar));
        return userAvatar.isDisplayed();
    }

    public boolean isUserLoggedOut() {
        wait.until(ExpectedConditions.visibilityOf(homePageBanner));
        return homePageBanner.isDisplayed();
    }

    public String getUserNameFromNavBar() {
        return userAvatar.getText().trim();
    }
}