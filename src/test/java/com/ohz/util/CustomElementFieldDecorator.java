package com.ohz.util;

import com.ohz.elements.CustomDropdownWebElement;
import com.ohz.elements.CustomWebElement;
import com.ohz.elements.CustomInputWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;

public class CustomElementFieldDecorator implements FieldDecorator {
    private final SearchContext searchContext;

    public CustomElementFieldDecorator(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {

        FindBy findBy = field.getAnnotation(FindBy.class);

        if (findBy != null) {
            WebElement webElement = searchContext.findElement(By.xpath(findBy.xpath()));

            if (CustomInputWebElement.class.isAssignableFrom(field.getType())) {
                return new CustomInputWebElement(webElement);
            } else if (CustomWebElement.class.isAssignableFrom(field.getType())) {
                return new CustomWebElement(webElement);
            } else if (CustomDropdownWebElement.class.isAssignableFrom(field.getType())) {
                return new CustomDropdownWebElement(webElement);
            }
        }

        return null;
    }
}
