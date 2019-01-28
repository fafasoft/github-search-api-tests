package test.common;

import test.web.common.Driver;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import io.restassured.RestAssured;

/**
 * Created by Fabricio Foruria on 21-Jan-19.
 */
public class TestBase {	
	
    public String baseURI;
    public String basePath;
    public String count;
    public String states;
    
	@BeforeGroups(groups = "api")
    public void setupApiTest(ITestContext context) {
		RestAssured.baseURI = context.getCurrentXmlTest().getParameter("baseURI");
        basePath = context.getCurrentXmlTest().getParameter("basePath");
        count = context.getCurrentXmlTest().getParameter("count");
        Reporter.log("baseURI="+baseURI+" basePath="+basePath+" count="+count, true);   
        RestAssured.basePath = basePath;
        states = context.getCurrentXmlTest().getParameter("states");  	 
    }	

	@BeforeGroups(groups = "web")
    public void initialization(){
        Driver.init();
    }

	@AfterGroups(groups = "web")
    public void cleanup(){
        Driver.tearDown();
    }   	
}
