package test.web.pages;

import org.openqa.selenium.By;
import test.web.common.Page;
import org.openqa.selenium.WebElement;
import java.lang.Thread;
import test.web.common.Driver;
import org.testng.Assert;

/**
 * Created by Fabricio Foruria on 21-Jan-19.
 */
public class UpgradeOffersPage extends Page {

    public static String loanAmount;
    public static String apr;
    public static String loanTerm;	
    public static String monthlyPayment;
       
    public void waitForPageLoaded() {
        waitFluentlyWebElement(By.xpath("//header/div[@class='header-nav']/label"),20);
        WebElement menuElement = Driver.driver.findElement(By.xpath("//header/div[@class='header-nav']/label"));
    	waitExplicitlyWebElement(menuElement,10);  
    }    

    public void logOut() {
        try {
        	Thread.sleep(2000);
            WebElement signOutElement = Driver.driver.findElement(By.xpath("//a[@class='header-nav-menu__link']"));
        	javaScriptClick(signOutElement);      	
        }
    	catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	} 
    }    
    
    public void storeOfferInfo() {
    	
        waitFluentlyWebElement(By.xpath("//span[@data-auto='userLoanAmount']"),20);
        WebElement loanAmountElem = Driver.driver.findElement(By.xpath("//span[@data-auto='userLoanAmount']"));
        loanAmount = loanAmountElem.getText();
    	
        waitFluentlyWebElement(By.xpath("//div[@data-auto='defaultMoreInfoAPR']"),20);
        WebElement aprElem = Driver.driver.findElement(By.xpath("//div[@data-auto='defaultMoreInfoAPR']"));
        apr = aprElem.getText();

        waitFluentlyWebElement(By.xpath("//div[@data-auto='defaultLoanTerm']"),20);
        WebElement loanTermElem = Driver.driver.findElement(By.xpath("//div[@data-auto='defaultLoanTerm']"));
        loanTerm = loanTermElem.getText();
        
        waitFluentlyWebElement(By.xpath("//span[@data-auto='defaultMonthlyPayment']"),20);
        WebElement monthlyPaymentElem = Driver.driver.findElement(By.xpath("//span[@data-auto='defaultMonthlyPayment']"));
        monthlyPayment = monthlyPaymentElem.getText();
    }
    
    public void verifyPreviouslyStoredLoanInfo() {
    	
        waitFluentlyWebElement(By.xpath("//span[@data-auto='userLoanAmount']"),30);
        WebElement loanAmountElem = Driver.driver.findElement(By.xpath("//span[@data-auto='userLoanAmount']"));
        String loanAmountNew = loanAmountElem.getText();
    	
        waitFluentlyWebElement(By.xpath("//div[@data-auto='defaultMoreInfoAPR']"),30);
        WebElement aprElem = Driver.driver.findElement(By.xpath("//div[@data-auto='defaultMoreInfoAPR']"));
        String aprNew = aprElem.getText();

        waitFluentlyWebElement(By.xpath("//div[@data-auto='defaultLoanTerm']"),30);
        WebElement loanTermElem = Driver.driver.findElement(By.xpath("//div[@data-auto='defaultLoanTerm']"));
        String loanTermNew = loanTermElem.getText();
        
        waitFluentlyWebElement(By.xpath("//span[@data-auto='defaultMonthlyPayment']"),30);
        WebElement monthlyPaymentElem = Driver.driver.findElement(By.xpath("//span[@data-auto='defaultMonthlyPayment']"));
        String monthlyPaymentNew = monthlyPaymentElem.getText();
        
        System.out.print("loanAmount ::::: " + loanAmount);
        System.out.print("loanAmountNew ::::: " + loanAmountNew);
        System.out.print("apr ::::: " + apr);
        System.out.print("aprNew ::::: " + aprNew);
        System.out.print("loanTerm ::::: " + loanTerm);
        System.out.print("loanTermNew ::::: " + loanTermNew);
        System.out.print("monthlyPayment ::::: " + monthlyPayment);
        System.out.print("monthlyPaymentNew ::::: " + monthlyPaymentNew);
        
    	Assert.assertEquals (loanAmountNew,loanAmount); 
    	Assert.assertEquals (aprNew,apr); 
    	Assert.assertEquals (loanTermNew,loanTerm); 
    	Assert.assertEquals (monthlyPaymentNew,monthlyPayment);    	
    }    
}
