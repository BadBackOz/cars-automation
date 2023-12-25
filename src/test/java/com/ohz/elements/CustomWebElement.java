package com.ohz.elements;

import com.ohz.common.Configuration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.NoSuchElementException;

public class CustomWebElement {

    private final WebElement webElement;

    public CustomWebElement(WebElement webElement){
        this.webElement = webElement;
    }

    public void click(){
        webElement.click();
    }

    public void verifyText(String expectedText) {
        WebDriver driver = Configuration.getDriver();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10L))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(ExpectedConditions.textToBePresentInElement(webElement, expectedText));
        } catch (TimeoutException e) {
            String actualText = webElement.getText();
            String message = "Expected text: %s".formatted(expectedText) + " --- "
                    + "Actual Text: %s".formatted(actualText);

            Configuration.getScenario().log(message);
            Assert.fail();
        }
    }
}
