package com.ohz.elements;

import org.openqa.selenium.WebElement;

public class CustomInputWebElement {

    private final WebElement webElement;

    public CustomInputWebElement(WebElement webElement){
        this.webElement = webElement;
    }

    public void type(String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

}
