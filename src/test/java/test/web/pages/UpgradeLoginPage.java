package test.web.pages;

import test.web.common.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import test.web.pages.UpgradePersonalInfoPage;

/**
 * Created by Fabricio Foruria on 21-Jan-19.
 */
public class UpgradeLoginPage extends Page {

    @FindBy (name = "username") WebElement usernameElement;
    @FindBy (name = "password") WebElement passwordElement;
    @FindBy (css = "button[type='submit']") WebElement loginButtonElement;
    
    private UpgradePersonalInfoPage upgradePersonalInfoPage;

    public void login() {
        try {      	
        	String user = upgradePersonalInfoPage.getUsername();
        	String pass = upgradePersonalInfoPage.getPassword();
            type(usernameElement, user);
            type(passwordElement, pass);
            loginButtonElement.click(); 
            Thread.sleep(5000);
        }
    	catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}
    }

    public void waitForPageLoaded() {
    	waitExplicitlyWebElement(usernameElement,10);  
    }  
}

