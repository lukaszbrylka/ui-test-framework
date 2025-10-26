package com.lukasz.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static DriverManager instance;
    private final WebDriver driver;

    private DriverManager() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }


    public static WebDriver driver() {
        return getInstance().getDriver();
    }


    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            instance = null;
        }
    }
}