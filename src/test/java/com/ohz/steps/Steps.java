package com.ohz.steps;

import com.ohz.common.Configuration;
import com.ohz.common.HomepageBaseTestPage;
import com.ohz.pages.CarsResultsPage;
import com.ohz.pages.CarsShoppingPage;
import com.ohz.util.DropdownOptionMapper;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
    @BeforeStep
    public void beforeStep(Scenario scenario){
        Configuration.setScenario(scenario);
    }

    @Given("user is on the Cars Shopping page")
    public void launchCarsShoppingPage(){
        HomepageBaseTestPage baseTestPage = new HomepageBaseTestPage();
        baseTestPage.launchCarsWebApp();
    }

    @And("user selects {string} in Stock Type dropdown")
    public void selectStockType(String stockType){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownStockType.selectOptionByVisibleText(stockType);
    }

    @And("user selects {string} in Make dropdown")
    public void selectMake(String make){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownMake.selectOptionByVisibleText(make);
    }

    @And("user selects {string} in Model dropdown")
    public void selectModel(String model){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownModel.selectOptionByVisibleText(model);
    }

    @And("user selects {string} in Price dropdown")
    public void selectPrice(String price){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownPrice.selectOptionByVisibleText(price);
    }

    @And("user selects {string} in Distance dropdown")
    public void selectDistance(String distance){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownDistance.selectOptionByVisibleText(distance);
    }

    @And("user enters {string} into ZIP input")
    public void enterZip(String zipCode){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.inputZip.type(zipCode);
    }

    @When("user clicks Search button")
    public void performSearch(){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.buttonSearch.click();
    }

    @Then("Results page should be displayed with header {string}")
    public void resultsPageShouldBeDisplayedWithHeader(String expectedText) {
        CarsResultsPage crp = new CarsResultsPage();
        crp.header.verifyText(expectedText);
    }

    @Then("model dropdown should contain expected new model options for Make {string}")
    public void verifyModelDropdownOptions(String make) {
        CarsShoppingPage csp = new CarsShoppingPage();

        csp.dropdownModel.verifyOptions(DropdownOptionMapper.getNewModelsForMake(make));
    }
}
