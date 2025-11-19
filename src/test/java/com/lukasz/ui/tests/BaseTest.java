package com.lukasz.ui.tests;

import com.lukasz.ui.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    protected static final String BASE_URL = "https://conduit-realworld-example-app.fly.dev/#/";
    protected static final String BROWSER = "chrome";
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = DriverManager.getInstance().getDriver(BROWSER);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        DriverManager.getInstance().quitDriver();
    }
}