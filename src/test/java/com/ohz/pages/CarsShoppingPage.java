package com.ohz.pages;

import com.ohz.common.HomepageBaseTestPage;
import com.ohz.elements.CustomDropdownWebElement;
import com.ohz.elements.CustomInputWebElement;
import com.ohz.elements.CustomWebElement;
import com.ohz.util.CustomElementFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarsShoppingPage extends HomepageBaseTestPage {

    @FindBy(xpath ="//select[@id='make-model-search-stocktype']")
    public CustomDropdownWebElement dropdownStockType;

    @FindBy(xpath = "//select[@id='makes']")
    public CustomDropdownWebElement dropdownMake;

    @FindBy(xpath = "//select[@id='models']")
    public CustomDropdownWebElement dropdownModel;

    @FindBy(xpath = "//select[@id='make-model-max-price']")
    public CustomDropdownWebElement dropdownPrice;

    @FindBy(xpath = "//select[@id='make-model-maximum-distance']")
    public CustomDropdownWebElement dropdownDistance;

    @FindBy(xpath = "//input[@id='make-model-zip']")
    public CustomInputWebElement inputZip;

    @FindBy(xpath = "//button[text()='Search']")
    public CustomWebElement buttonSearch;

    public CarsShoppingPage(){
        PageFactory.initElements(new CustomElementFieldDecorator(this.getDriver()), this);
    }

}
