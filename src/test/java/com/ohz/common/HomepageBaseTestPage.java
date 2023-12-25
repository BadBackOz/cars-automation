package com.ohz.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomepageBaseTestPage {
    private static final String BASE_URL = System.getProperty("env.url");

    public WebDriver getDriver() {
        WebDriver driver = Configuration.getDriver();

        if (driver == null) {
            driver = new ChromeDriver();
            Configuration.setWebDriver(driver);
        }

        return driver;
    }

    public void launchCarsWebApp() {
        getDriver().get(BASE_URL);
        getDriver().manage().window().maximize();
        Configuration.getScenario().log("Launched: %s".formatted(BASE_URL));
    }

}
