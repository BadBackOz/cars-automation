package com.ohz.pages;

import com.ohz.common.HomepageBaseTestPage;
import com.ohz.elements.CustomWebElement;
import com.ohz.util.CustomElementFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarsResultsPage extends HomepageBaseTestPage {

    @FindBy(xpath = "//h1")
    public CustomWebElement header;

    public CarsResultsPage(){
        PageFactory.initElements(new CustomElementFieldDecorator(this.getDriver()), this);
    }
}
