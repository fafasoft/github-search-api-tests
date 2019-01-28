package test.web.workflows;

import test.web.pages.UpgradeLandingPage;
import test.web.pages.UpgradePersonalInfoPage;
import test.web.pages.UpgradeOffersPage;
import test.web.pages.UpgradeLoginPage;
import java.text.ParseException;

public class Workflows {
    private static UpgradePersonalInfoPage upgradePersonalInfoPage;
    private static UpgradeOffersPage upgradeOffersPage;
    private static UpgradeLandingPage upgradeLandingPage;
    private static UpgradeLoginPage upgradeLoginPage;
    private static String desiredAmountInput = "2000";
 
    public static void givenGoToUpgradeLandingPage() {
    	upgradeLandingPage = new UpgradeLandingPage();
    	upgradeLandingPage.navigatesTo();
    	upgradeLandingPage.waitForPageLoaded();
    }        
    
    public static void whenEntersLoanAmountPurposeAndClicksCheckYourRateButton() {
    	upgradeLandingPage.enterInfoAndSubmit(desiredAmountInput);
    }    
    
    public static void whenEntersPersonalInfoAndSubmitIt() throws ParseException {
    	upgradePersonalInfoPage = new UpgradePersonalInfoPage();
    	upgradePersonalInfoPage.waitForPageLoaded();
    	upgradePersonalInfoPage.fillRequiredFields();       	
    }     
 
    public static void whenGetsTheLoanOfferInfoShownAndLogsOut(){
    	upgradeOffersPage = new UpgradeOffersPage();
    	upgradeOffersPage.waitForPageLoaded();
    	upgradeOffersPage.storeOfferInfo();
    	upgradeOffersPage.logOut();
    	upgradeOffersPage.goToUrl("https://www.credify.tech/portal/login");	
    }   
    
    public static void thenLogsInAndVerifiesTheSameLoanOfferInfoShownPreviously(){
       	upgradeLoginPage = new UpgradeLoginPage();
    	upgradeLoginPage.waitForPageLoaded();
    	upgradeLoginPage.login();
    	upgradeOffersPage = new UpgradeOffersPage();
    	upgradeOffersPage.waitForPageLoaded();	
    	upgradeOffersPage.verifyPreviouslyStoredLoanInfo();
    }           
}