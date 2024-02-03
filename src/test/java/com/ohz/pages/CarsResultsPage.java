package com.ohz.pages;

import com.ohz.common.HomepageBaseTestPage;
import com.ohz.elements.CustomWebElement;
import com.ohz.util.CustomElementFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarsResultsPage extends HomepageBaseTestPage {

    @FindBy(xpath = "//h1")
    public CustomWebElement header;

    @FindBy(xpath = "//span[@class='total-filter-count']")
    public CustomWebElement textTotalFilterCount;

    @FindBy(xpath = "//div[@class='vehicle-details']")
    public CustomWebElement vehicleCardContainerList;

    @FindBy(xpath = "//a[text()='Next'] | //button[text()='Next']")
    public CustomWebElement linkNextPage;

    public CarsResultsPage(){
        PageFactory.initElements(new CustomElementFieldDecorator(this.getDriver()), this);
    }

}
