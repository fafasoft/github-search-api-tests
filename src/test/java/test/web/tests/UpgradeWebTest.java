package test.web.tests;

import org.testng.annotations.Test;
import org.testng.Reporter;
import test.common.TestBase;
import java.text.ParseException;
import test.web.workflows.Workflows;

// Automation task 1: Web: Automate via the UI, the web scenario mentioned in User Story, 
// and include any validations you deem necessary.
public class UpgradeWebTest extends TestBase {
	    
	@Test(groups= "web")
    public void validatesThatNewUserCanCheckHisRateandGetAnLoanOffer() throws ParseException {
    	Reporter.log("WEB Test Step: Given the user goes to the Upgrade Landing page ...",true);  
    	Workflows.givenGoToUpgradeLandingPage();
    	Reporter.log("WEB Test Step: when enters Loan Amount and Purpose and submits it ...",true);  
    	Workflows.whenEntersLoanAmountPurposeAndClicksCheckYourRateButton();
    	Reporter.log("WEB Test Step: when enters required Personal Info and submits it ...",true);  
    	Workflows.whenEntersPersonalInfoAndSubmitIt();
    	Reporter.log("WEB Test Step: when gets the Loan Offer info shown and logs-out ...",true);  
    	Workflows.whenGetsTheLoanOfferInfoShownAndLogsOut(); 
    	Reporter.log("WEB Test Step: when logs-in and verifies that the same previous Loan Offer info is shown again ...",true);  
    	Workflows.thenLogsInAndVerifiesTheSameLoanOfferInfoShownPreviously();        		     
    }        
}
