package com.ohz.steps;

import com.google.gson.Gson;
import com.ohz.common.Configuration;
import com.ohz.common.HomepageBaseTestPage;
import com.ohz.data.VehicleData;
import com.ohz.elements.CustomWebElement;
import com.ohz.pages.CarsResultsPage;
import com.ohz.pages.CarsShoppingPage;
import com.ohz.util.CustomUtil;
import com.ohz.util.DropdownOptionMapper;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Steps {
    @BeforeStep
    public void beforeStep(Scenario scenario){
        Configuration.setScenario(scenario);
    }

    @Given("user is on the Cars Shopping page")
    public void launchCarsShoppingPage(){
        HomepageBaseTestPage baseTestPage = new HomepageBaseTestPage();
        baseTestPage.launchCarsWebApp();
        CustomWebElement popUp = new CustomWebElement("//div[@role='document']//button[@part='close-button']");
        if(popUp.isDisplayed(5)){
            popUp.click();
        }
    }

    @And("user selects {string} in Stock Type dropdown")
    public void selectStockType(String stockType){
        CarsShoppingPage csp = new CarsShoppingPage();

        HashMap<String, String> data = Configuration.getExcelData();
        if(!data.isEmpty()){
            stockType = data.get("stockType");
        }
        csp.dropdownStockType.selectOptionByVisibleText(stockType, 5);
    }

    @And("user selects {string} in Make dropdown")
    public void selectMake(String make){
        CarsShoppingPage csp = new CarsShoppingPage();

        HashMap<String, String> data = Configuration.getExcelData();
        if(!data.isEmpty()){
            make = data.get("make");
        }
        csp.dropdownMake.selectOptionByVisibleText(make, 5);
    }

    @And("user selects {string} in Model dropdown")
    public void selectModel(String model){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownModel.selectOptionByVisibleText(model, 5);
    }

    @And("user selects {string} in Price dropdown")
    public void selectPrice(String price){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownPrice.selectOptionByVisibleText(price, 5);
    }

    @And("user selects {string} in Distance dropdown")
    public void selectDistance(String distance){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.dropdownDistance.selectOptionByVisibleText(distance, 5);
    }

    @And("user enters {string} into ZIP input")
    public void enterZip(String zipCode){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.inputZip.type(zipCode, 5);
    }

    @When("user clicks Search button")
    public void performSearch(){
        CarsShoppingPage csp = new CarsShoppingPage();
        csp.buttonSearch.javascriptClick();
        CarsResultsPage resultsPage = new CarsResultsPage();
        resultsPage.vehicleCardContainerList.waitForPresent(10, false);
    }

    @Then("Results page should be displayed with header {string}")
    public void resultsPageShouldBeDisplayedWithHeader(String expectedText) {
        CarsResultsPage crp = new CarsResultsPage();
        crp.header.verifyText(expectedText);
        Configuration.getScenario().log("Count of cars found: %s".formatted(crp.textTotalFilterCount.getText()));
    }

    @Then("model dropdown should contain expected new model options for Make {string}")
    public void verifyModelDropdownOptions(String make) {
        CarsShoppingPage csp = new CarsShoppingPage();

        HashMap<String, String> data = Configuration.getExcelData();
        List<String> expectedModels;
        if(!data.isEmpty()){
            expectedModels = Arrays.stream(data.get("expectedModels").split(",")).toList();
        }else {
            expectedModels = DropdownOptionMapper.getNewModelsForMake(make);
        }

        csp.dropdownModel.verifyOptions(expectedModels, 5);
    }

    @And("log details of each used vehicle found")
    public void logToReportDetailsOfEachVehicleFound() {
        boolean isNextEnabled;
        do {
            CarsResultsPage page = new CarsResultsPage();

            List<CustomWebElement> listOfVehicleData = page.vehicleCardContainerList.getListOfElement();
            String firstVehicle = listOfVehicleData.getFirst().findElement(By.xpath(".//a/h2")).getText();
            for(CustomWebElement customWebElement : listOfVehicleData){
                VehicleData vehicleData = new VehicleData();
                vehicleData.setYearAndTrim(customWebElement.findElement(By.xpath(".//a/h2")).getText());
                vehicleData.setMileage(customWebElement.findElement(By.xpath(".//div[@class='mileage']")).getText());
                vehicleData.setPrice(customWebElement.findElement(By.xpath(".//span[@class='primary-price']")).getText());
                vehicleData.setSeller(customWebElement.findElement(By.xpath(".//div[@class='dealer-name']/strong | .//div[@class='seller-name']/strong")).getText());

                Configuration.getScenario().log(new Gson().toJson(vehicleData));
            }
            isNextEnabled = page.linkNextPage.isEnabled();
            if(isNextEnabled){
                String currentPage = page.linkNextPage.getAttributeValue("phx-value-page");
                page.linkNextPage.javascriptClick();
                page = new CarsResultsPage();
                page.linkNextPage.waitForAttributeNotValue("phx-value-page", currentPage, 10);
                CustomWebElement firstYearAndTrimElement = page.vehicleCardContainerList.getListOfElement().getFirst().findElement(By.xpath(".//a/h2"));
                firstYearAndTrimElement.waitForAttributeNotValue("innerText", firstVehicle, 10);
                CustomUtil.wait(1000);
            }
        }while (isNextEnabled);
    }
}
