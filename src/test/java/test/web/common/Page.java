package test.web.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebDriver;
import com.google.common.base.Function;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.lang.StringBuilder;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.lang.CharSequence;

/**
 * Created by Fabricio Foruria on 10-Jan-19.
 */
public class Page {

	private static final String ALPHABETIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";	
	
    public Page() {
        PageFactory.initElements(Driver.driver, this);
    } 
    
    public void open(String url) {
        Driver.driver.get(url);
        //Driver.driver.manage().window().maximize();
    }
    
    public void goToUrl(String url) {
        Driver.driver.navigate().to(url);
    }    
     
    public static String getRandomAlphabeticString(int count) {
    	StringBuilder builder = new StringBuilder();
    	while (count-- != 0) {
    	int character = (int)(Math.random()*ALPHABETIC_STRING.length());
    	builder.append(ALPHABETIC_STRING.charAt(character));
    	}
    	return builder.toString();
    }    
    
    protected static String getRandomInt() {
    	int random = (int)(Math.random() * 50 + 1);
    	String numberString = Integer.toString(random);
    	return numberString;
    }       
    
    protected String getRandomDOB() throws ParseException {
    	try {
    			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	            Calendar cal = Calendar.getInstance();
	            String str_date1="01/01/1930";
	            String str_date2="01/01/2000";
	
	            cal.setTime(formatter.parse(str_date1));
	            long value1 = cal.getTimeInMillis();
	            cal.setTime(formatter.parse(str_date2));
	            long value2 = cal.getTimeInMillis();
	            long value3 = (long)(value1 + Math.random()*(value2 - value1));
	            cal.setTimeInMillis(value3);
	            return formatter.format(cal.getTime());
    		}
			catch (ParseException e) {
				throw e;
			}           
     }        
    
    protected boolean isElementPresent(By locator) {
        Driver.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> elements = Driver.driver.findElements(locator);
        Driver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    protected void type(WebElement webElement, String text){
    	waitElementToBeClickable(webElement,30);
    	webElement.click();
        webElement.sendKeys(text);
    }
    
//    protected void typeCarefully(WebElement input, String text){
//    	waitElementToBeClickable(input,30);  
//    	input.click();
//        for(int i = 0; i < text.length(); i++){
//        	input.sendKeys(text.charAt(i));
//        }
//    }    
    
    public String getText(WebElement webElement) {
    	return webElement.getText();
    }        

    protected void acceptConfirmationMessage() {
        Alert alert = Driver.driver.switchTo().alert();
        alert.accept();
    }

    protected void selectElementByTheText(String locator, String text){
        Select select = new Select(Driver.driver.findElement(By.xpath(locator)));
        select.selectByVisibleText(text);
        select.getFirstSelectedOption();
    }

    protected void deselectAllAndSelectElementByTheText(String locator, String text){
        Select select = new Select(Driver.driver.findElement(By.xpath(locator)));
        select.deselectAll();
        select.selectByVisibleText(text);
        select.getFirstSelectedOption();
    }

    protected void executeJavascript(String javaScriptCode){
        ((JavascriptExecutor) Driver.driver).executeScript(javaScriptCode);
    }

    protected void javaScriptClick(WebElement webElement){
        JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    protected void scrollToElement(WebElement element){
    	try {
            JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);     		
    	}
    	catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}  
    }    
    
    protected void waitExplicitlyWebElement(WebElement locator, int seconds){
        WebDriverWait waiter = new WebDriverWait(Driver.driver, seconds);
        waiter.until(ExpectedConditions.visibilityOf(locator));
    }

    protected void waitFluentlyWebElement(final By locator, int seconds){
    	WebDriver driv = Driver.driver;
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driv)
        	    .withTimeout(30, TimeUnit.SECONDS)
        	    .pollingEvery(5, TimeUnit.SECONDS)
        	    .ignoring(NoSuchElementException.class);

        	WebElement foo = wait.until(new Function<WebDriver, WebElement>() 
        	{
        	  public WebElement apply(WebDriver driv) {
        	  return driv.findElement(locator);
        	}
        	});
    }     	
    	
    protected void waitElementToBeClickable(WebElement locator, int seconds){
        WebDriverWait waiter = new WebDriverWait(Driver.driver, seconds);
        waiter.until(ExpectedConditions.elementToBeClickable(locator));
    }    
       
    protected Boolean isAjaxAttributeValuePresent(final By locator, final String attribute, final String value) {	
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.driver);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.withTimeout(5, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class); 
        wait.ignoring(TimeoutException.class);
 
        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
        {
        	Boolean response = false;
            public Boolean apply(WebDriver arg0)
            {
                WebElement element = arg0.findElement(locator);
                String attrValue = element.getAttribute(attribute);
 
                if(attrValue.equals(value))
                {
                	response = true;
                }
                return response;
            } 
        };
        
        Boolean resp;
        
        try {
        	resp = wait.until(function);
        } 
        catch (Exception e) {
        	resp = false;
        }
        
        return resp;
    }
}
