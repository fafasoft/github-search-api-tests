package test.web.pages;

import org.openqa.selenium.By;
import test.web.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Random;
import test.web.common.Driver;

/**
 * Created by Fabricio Foruria on 21-Jan-19.
 */
public class UpgradeLandingPage extends Page {
    final String URL = "https://www.credify.tech";

    public void waitForPageLoaded() {
    	waitFluentlyWebElement(By.xpath("//div[contains(@class,'input-text-container')]/div/input"),30);
        WebElement desiredAmountInput = Driver.driver.findElement(By.xpath("//div[contains(@class,'input-text-container')]/div/input"));
    	waitExplicitlyWebElement(desiredAmountInput,60);  
    }  

    public void navigatesTo() {
    	open(URL);
    }      
   
    public void enterInfoAndSubmit(String amount) {        	
    	Random rand = new Random();
    	waitFluentlyWebElement(By.xpath("//div[contains(@class,'input-text-container')]/div/input"),30);
        WebElement desiredAmountInput = Driver.driver.findElement(By.xpath("//div[contains(@class,'input-text-container')]/div/input"));
        type(desiredAmountInput, amount);
        waitFluentlyWebElement(By.xpath("//select[contains(@data-auto,'dropLoanPurpose')]"),30);
        WebElement loanPurposeSelect = Driver.driver.findElement(By.xpath("//select[contains(@data-auto,'dropLoanPurpose')]"));
        Select dropdown = new Select(loanPurposeSelect);
        List<WebElement> options = dropdown.getOptions();
        int index = rand.nextInt(options.size());    
        // It was selecting a random loan purpose but for some of them the offer page is very different
        // I am selecting Business option instead of a random option now
        //options.get(index).click();  
        options.get(3).click();  
    	waitFluentlyWebElement(By.id("loan-form-submit"),30);
        WebElement submitButton = Driver.driver.findElement(By.id("loan-form-submit"));
        submitButton.click();        	
    }    
}
