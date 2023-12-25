package com.ohz.common;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.ConcurrentHashMap;

public class Configuration {

    static ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    public static void setWebDriver(WebDriver driver){
        map.put("driver%s".formatted(Thread.currentThread().toString()), driver);
    }

    public static WebDriver getDriver(){
        return (WebDriver) map.get("driver%s".formatted(Thread.currentThread().toString()));
    }

    public static void removeWebDriver(){
        map.remove("driver%s".formatted(Thread.currentThread()));
    }

    public static void setScenario(Scenario scenario){
        map.put("scenario%s".formatted(Thread.currentThread().toString()), scenario);
    }

    public static Scenario getScenario(){
        return (Scenario) map.get("scenario%s".formatted(Thread.currentThread().toString()));
    }

    public static void removeScenario(){
        map.remove("scenario%s".formatted(Thread.currentThread().toString()));
    }
}
