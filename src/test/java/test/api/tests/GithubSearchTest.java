package test.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.common.TestBase;

import test.api.data.GithubData;

/**
 * Created by Fabricio Foruria on 1-Feb-19.
 */
public class GithubSearchTest extends TestBase {
	
	private String query;

	@DataProvider(name = "data")
	public Object[][] createRepositoriesTestData() {   
		return new Object[][]{
			{new GithubData("fafasoft","8","github-search-api-tests","javascript","tetris","100","2","updated","desc")}
		};
	}
	
	@Test(dataProvider = "data") 
    public void verifySearchByAuthor(GithubData data)  {
        Reporter.log("Test Scenario 1: Validates that total count of repositories is equal to: " + data.getCount() ,true);
        query = "user:" + data.getUser();
        given()
        	.spec(spec)
	        .param("q", query.toString())
	        .when()
	        .get()
	        .then()
	        .statusCode(200)
	        .body("total_count", equalTo(Integer.parseInt(data.getCount())));
    }
	
	@Test(dataProvider = "data")
    public void verifySearchByRepositoryName(GithubData data) {
        Reporter.log("Test Scenario 2: Validates that repository name '" + data.getName() + "' for user '" + data.getUser() + "'" ,true);
        query = data.getName() + " in:name user:" + data.getUser();
        given()
        	.spec(spec)
	        .param("q", query)
            .when()
	        .get()
            .then()
            .statusCode(200).body("total_count", equalTo(1));
    }
    
	@Test(dataProvider = "data")
    public void verifySearchByLanguage(GithubData data) {
        Reporter.log("Test Scenario 3: Validates that language '" + data.getLanguage() + "' is present for user '" + data.getUser() + "'",true);
        query = "language:" + data.getLanguage() + " user:" + data.getUser();
        given()
        	.spec(spec)
	        .param("q", query)
            .when()
	        .get()
            .then()
            .statusCode(200).body("total_count", equalTo(Integer.parseInt(data.getLangResultsCount())));
    }
    
    
	@Test(dataProvider = "data")
    public void verifySearchPagination(GithubData data) {
        Reporter.log("Test Scenario 4: Validates that requested number of '" + data.getPages() + "' pages works for the keyword '" + data.getKeyword() + "'",true);
        query = data.getKeyword();
        given()
        	.spec(spec)
            .param("q",query).param("per_page", data.getPages())
            .when()
	        .get()
            .then()
            .statusCode(200).body("items", hasSize(Integer.parseInt(data.getPages())));
    }
    
	@Test(dataProvider = "data")
    public void verifySearchSorting(GithubData data) {
        Reporter.log("Test Scenario 5: Validates that sorting method works properly." ,true);
        query = "user:" + data.getUser();
        given()
    		.spec(spec)
    		.param("q",query).param("sort", data.getSort()).param("order", data.getOrder())
            .when()
	        .get()
            .then()
            .statusCode(200).body("items[0].name", equalTo(data.getName()));
    }      
}
