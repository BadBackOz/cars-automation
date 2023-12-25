package com.ohz.elements;

import com.ohz.common.Configuration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.ArrayList;

public class CustomDropdownWebElement {

    private final WebElement webElement;

    public CustomDropdownWebElement(WebElement webElement){
        this.webElement = webElement;
    }

    public void selectOptionByVisibleText(String text){
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }

    public void verifyOptions(ArrayList<String> expectedOptions) {
        Select select = new Select(webElement);

        ArrayList<String> actualOptions = new ArrayList<>();
        boolean isMatch = false;
        int i=0;
        while(i<3 && !isMatch){
            actualOptions = new ArrayList<>();
            for(WebElement option : select.getOptions()){
                actualOptions.add(option.getText());
            }

            if(actualOptions.equals(expectedOptions)){
                isMatch = true;
            }
            i = i+1;

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String message = "Expected Options: %s".formatted(expectedOptions) +" --- "
                + "Actual Options: %s".formatted(actualOptions);
        Configuration.getScenario().log(message);
        if(!isMatch){
            Assert.fail();
        }
    }

}
