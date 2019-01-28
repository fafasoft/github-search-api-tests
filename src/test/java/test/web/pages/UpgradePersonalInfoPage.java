package test.web.pages;

import org.openqa.selenium.By;
import test.web.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import java.lang.Thread;
import java.util.List;
import test.web.common.Driver;
import java.text.ParseException;

/**
 * Created by Fabricio Foruria on 21-Jan-19.
 */
public class UpgradePersonalInfoPage extends Page {
	
    public static String username;	
    public static String password;

    @FindBy (css = "h2.title--secondary") WebElement headerTitle;
  
    public static String getUsername() {
    	return username;
    }  
    
    public static String getPassword() {
    	return password;
    }  
    
    public void waitForPageLoaded() {
    	waitExplicitlyWebElement(headerTitle,30);  
    }
    
    public String getHeaderText() {
    	return headerTitle.getText();
    }      
    
    public void fillRequiredFields() throws ParseException {     

    	// Randomized data
    	String firstNameString = getRandomAlphabeticString(10);
    	String lastNameString = getRandomAlphabeticString(10);
        // Because an actual issue with selenium sendKeys does not send properly all chars for a random date string 
    	String dobString = getRandomDOB().replaceAll("[^\\d.]", "");
    	String randomNumberString = getRandomInt();
        
    	try {
           	waitFluentlyWebElement(By.cssSelector("input[name='borrowerFirstName']"),20);
            WebElement firstname = Driver.driver.findElement(By.cssSelector("input[name='borrowerFirstName']"));
            type(firstname, firstNameString);

           	waitFluentlyWebElement(By.cssSelector("input[name='borrowerLastName']"),20);
            WebElement lastname = Driver.driver.findElement(By.cssSelector("input[name='borrowerLastName']"));
            type(lastname, lastNameString);     
            
            waitFluentlyWebElement(By.cssSelector("input[name='borrowerStreet']"),20);
            WebElement street = Driver.driver.findElement(By.cssSelector("input[name='borrowerStreet']"));
            type(street, "1657 Riverside Drive");
            
            List<WebElement> suggestions = Driver.driver.findElements(By.cssSelector("li.geosuggest__item"));
            waitFluentlyWebElement(By.cssSelector("li.geosuggest__item"),20);
            suggestions.get(0).click();
                        
            waitFluentlyWebElement(By.cssSelector("input[name='borrowerDateOfBirth']"),20);
            WebElement dob = Driver.driver.findElement(By.cssSelector("input[name='borrowerDateOfBirth']"));
            
            // Because an actual issue with selenium sendKeys does not send properly all chars for a random date string 
            //type(dob, dobString);
            
            Thread.sleep(2000); 
            type(dob, "11111970");
            
            waitFluentlyWebElement(By.cssSelector("input[name='borrowerIncome']"),20);
            WebElement annualIncome = Driver.driver.findElement(By.cssSelector("input[name='borrowerIncome']"));
            type(annualIncome, "100000");        

            waitFluentlyWebElement(By.cssSelector("input[name='borrowerAdditionalIncome']"),20);
            WebElement additionalIncome = Driver.driver.findElement(By.cssSelector("input[name='borrowerAdditionalIncome']"));
            type(additionalIncome, "5000");
            
            scrollToElement(additionalIncome);
            
            Thread.sleep(1000);
            
            waitFluentlyWebElement(By.xpath("//input[@name='username']"),20);
            WebElement usernameElement = Driver.driver.findElement(By.cssSelector("input[name='username']"));
            username = "fabricio.foruria"+ randomNumberString +"@upgrade-challenge.com";
            type(usernameElement, username); 
            
            //Reporter.log("Test username: " + username, true);
            //Reporter.log("Test password: " + password, true);
            
            waitFluentlyWebElement(By.xpath("//div[contains(@class,'input-text-container')] //input[@name='password']"),20);
            WebElement passwordElement = Driver.driver.findElement(By.xpath("//div[contains(@class,'input-text-container')] //input[@name='password']"));
            password = "Abcd1234$";
            type(passwordElement, password);
                    
            scrollToElement(passwordElement);
            
            Thread.sleep(1000); 
            
            waitFluentlyWebElement(By.xpath("//div[@class='sc-hEsumM calXzx sc-tilXH jyQxwO']"),20);
            WebElement agreeCheckbox = Driver.driver.findElement(By.xpath("//div[@class='sc-hEsumM calXzx sc-tilXH jyQxwO']"));
            agreeCheckbox.click();

            waitFluentlyWebElement(By.xpath("//button[@type='submit']"),20);
            WebElement submitButton = Driver.driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();            	
    	}
    	catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}  
    }        
}