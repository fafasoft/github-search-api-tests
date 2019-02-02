package api.common;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

/**
 * Created by Fabricio Foruria on 1-Feb-19.
 */
public class TestBase {	
	
    public static String baseURI;
    public static String basePath;
    public String count;
    public String states;
    
	protected static RequestSpecification spec;

	@BeforeClass
	public static void initSpec(ITestContext context){
		baseURI = context.getCurrentXmlTest().getParameter("baseURI");
		basePath = context.getCurrentXmlTest().getParameter("basePath");
	
		Reporter.log("baseURI="+baseURI+" - basePath="+basePath, true);  
	    spec = new RequestSpecBuilder()
	            .setContentType(ContentType.JSON)
	            .setBaseUri(baseURI)
	            .setBasePath(basePath)
	            //.addFilter(new ResponseLoggingFilter()) //logs request and response for better debugging. You can also only log if a requests fails.
	            //.addFilter(new RequestLoggingFilter())
	            .build();
	} 	
}
