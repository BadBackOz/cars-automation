package com.ohz.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface CustomSearchContext {

    List<WebElement> findElements(By by);

    WebElement findElement(By by);
}
