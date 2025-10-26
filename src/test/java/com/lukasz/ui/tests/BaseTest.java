package com.lukasz.ui.tests;

import com.lukasz.ui.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    protected static final String BASE_URL = "https://conduit-realworld-example-app.fly.dev/#/";

    @BeforeEach
    void setUp() {
        DriverManager.driver().manage().window().maximize();
        DriverManager.driver().get(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        DriverManager.getInstance().quitDriver();
    }
}