package com.ohz.elements;

import com.ohz.common.Configuration;
import com.ohz.util.CustomUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomWebElement {

    private final WebElement webElement;

    public CustomWebElement(WebElement webElement){
        this.webElement = webElement;
    }

    public void click(){
        waitForPresent(10);
        this.webElement.click();
        Configuration.getScenario().log("Clicked on element: %s".formatted(this.getXpath()));
    }

    public void javascriptClick(){
        JavascriptExecutor executor = (JavascriptExecutor) Configuration.getDriver();
        executor.executeScript("arguments[0].click();", this.webElement);
    }

    public boolean isEnabled(){
        waitForPresent(10);
        try{
            return this.webElement.isEnabled();
        }catch (StaleElementReferenceException e){
            System.out.println("StaleElementException: " + this.getXpath() + " --- " + e.getMessage());
            return false;
        }
    }

    public String getText(){
        waitForPresent(10);
        return this.webElement.getText();
    }

    public String getAttributeValue(String attribute){
        waitForPresent(10);
        return this.webElement.getAttribute(attribute);
    }

    public CustomWebElement findElement(By by){
        return new CustomWebElement(this.webElement.findElement(by));
    }

    public List<CustomWebElement> getListOfElement(){
        List<WebElement> webElementList = Configuration.getDriver().findElements(By.xpath(this.getXpath()));

        List<CustomWebElement> customWebElementList = new ArrayList<>();
        for(WebElement element : webElementList){
            customWebElementList.add(new CustomWebElement(element));
        }

        return customWebElementList;
    }

    public void waitForAttributeNotValue(String attribute, String value, int timeoutInSeconds){
        boolean isMatch = true;
        long endTime = System.currentTimeMillis() + Duration.ofSeconds(timeoutInSeconds).getSeconds();

        String actualAttributeValue = null;
        while(isMatch && System.currentTimeMillis() < endTime){
            try{
               CustomUtil.wait(200);
                actualAttributeValue = String.valueOf(this.webElement.getAttribute(attribute));
            }catch (Exception e){
                System.out.printf("Exception: %s%n", e);
            }

            isMatch = value.equals(actualAttributeValue);
        }
    }

    public void waitForPresent(int timeoutInSeconds){
        long endTime = System.currentTimeMillis() + timeoutInSeconds * 1000L;

        boolean isPresent = false;
        while(!isPresent && System.currentTimeMillis() < endTime){
            try{
                if(!Configuration.getDriver().findElements(By.xpath(getXpath())).isEmpty()){
                 isPresent = true;
                }
            }catch (Exception e){
                System.out.printf("Waiting for presence of element: %s%n", getXpath());
            }
            CustomUtil.wait(200);
        }

        if(!isPresent){
            Configuration.getScenario().log("Element not present: %s".formatted(this.getXpath()));
            Assert.fail();
        }
    }

    public void verifyText(String expectedText) {
        WebDriver driver = Configuration.getDriver();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10L))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(ExpectedConditions.textToBePresentInElement(this.webElement, expectedText));
        } catch (TimeoutException e) {
            String actualText = this.webElement.getText();
            String message = "Expected text: %s".formatted(expectedText) + " --- "
                    + "Actual Text: %s".formatted(actualText);

            Configuration.getScenario().log(message);
            Assert.fail();
        }
    }

    private String getXpath(){
        String[] xpathArray = this.webElement.toString().split("xpath: ");
        StringBuilder sb = new StringBuilder();
        for(String xpath : xpathArray){
            xpath = xpath.trim();
            sb.append(xpath.replaceAll("]$","").replaceAll("^\\[\\[.* ->","").replaceAll("\\.//","//").replaceAll("->","").replaceAll("]]]","]").trim());
        }
        return sb.toString();
    }
}