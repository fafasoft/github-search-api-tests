package test.api.tests;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import test.common.TestBase;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by Fabricio Foruria on 21-Jan-19.
 */
public class StatesApiTest extends TestBase {
	// Automation task 2: API: Write a test that makes a GET request to the API mentioned in the user story 
	// and does the following using the response content (include any other validations that you deem necessary):	

	@Test(groups= "api")
    public void validatesTotalStatesCount() {
        Reporter.log("API Test: Validating Total States Count Is "+ count +"...",true);
        given().
        accept(ContentType.JSON).
        contentType(ContentType.JSON).
        get().
        then().
        statusCode(200).
        body("states.size()", is(count));
    }
	
	@Test(groups= "api")
    public void validatesAllStatesNamesValid() {
        Reporter.log("API Test: Validating All States Names Valid...",true);       	    	    	
    	Response response = when().get();
    	
    	
        Reporter.log("Response names valid...",true);       	    	    	

	    JsonPath jsonPathEvaluator = response.jsonPath();
	    List<String> allStatesNames = jsonPathEvaluator.getList("states.label");
	    for(String state : allStatesNames)
	    {	 
	      assertEquals(states.toLowerCase().contains(state.toLowerCase()), true,"This state from response is not a valid U.S. state: " + state);
	    }        
    }  
    
	@Test(groups= "api")
    public void validatesOnlyOneStateHasMinAgeOf19() {
        Reporter.log("API Test: Validating that Only One State Has Min Age Of 19...",true);
        Response response = when().get();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Integer> allStatesMinAges = new ArrayList<>();
        allStatesMinAges = jsonPathEvaluator.getList("states.minAge");
	    int i = 0;
	    // This counter is for counting how many states meet the condition and then comparing in the assertion
	    int counter = 0;
	    for(Integer minAge : allStatesMinAges)
	    {	 
	      if(Integer.toString(minAge).contains("19"))	
	      {
	    	  counter++;
	    	  String stateName = response.jsonPath().getString("states["+ Integer.toString(i) +"].label");
	    	  Reporter.log("This state was found as having Min Age of 19: " + stateName,true);
	      }	
	      i++;	
	    }  
	    assertEquals(counter, 1,"There should be only one U.S. state from response that has Min Age of 19 but it was: " + counter);
    }        
    
	@Test(groups= "api")
    public void validatesGeorgiaOnlyStateWithMinLoanAmountRequirementOf() {
        Reporter.log("API Test: Validating that Georgia is the Only State With Min Loan Amount Requirement Of $3005...",true);
        Response response = when().get();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<Float> allStatesMinLoanAmounts = new ArrayList<>();
        allStatesMinLoanAmounts = jsonPathEvaluator.getList("states.minLoanAmount");
	    int i = 0;
	    // This counter is for counting how many states meet the condition and then comparing in the assertion
	    int counter = 0;
	    for(Float minLoanAmount : allStatesMinLoanAmounts)
	    {	 
	      int amountInt = Math.round(minLoanAmount);	
	      String extractedText = Integer.toString(amountInt);		    	
	      if(extractedText.contains("3005"))	
	      {
	    	  String abbreviation = response.jsonPath().getString("states["+ Integer.toString(i) +"].abbreviation");
	    	  assertEquals(abbreviation, "GA", "Georgia (GA) should be the Only State With Min Loan Amount Requirement Of $3005 but it was state code: " + abbreviation);
	      }	
	      i++;
	    }  
    }      
}
