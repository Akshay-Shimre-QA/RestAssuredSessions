package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GETAPIWithQueryParamsAndPathParams {

	
	@Test
	public void getUserWith_QueryParam(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		      .header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		      .queryParam("name", "trivedi")
		      .queryParam("status", "active")
		           .when().log().all()
		              .get("/public/v2/users")
		                  .then().log().all()
		                     .assertThat().statusCode(200)
		                         .and()
		                           .contentType(ContentType.JSON);
		
	}
	
	
	@Test
    public void getUserWith_QueryParam_withHashmap(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		
		Map<String, String> queryMap=new HashMap<String, String>();
		
		queryMap.put("name", "trivedi");
		queryMap.put("Status", "active");
		queryMap.put("gender", "male");
		
		given().log().all()
		      .header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		      .queryParams(queryMap)
		           .when().log().all()
		              .get("/public/v2/users")
		                  .then().log().all()
		                     .assertThat().statusCode(200)
		                         .and()
		                           .contentType(ContentType.JSON);
		
	}
	
	@Test
    public void getUserWith_QueryParam_withMapOf(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		
//		Map<String, String> queryMap=new HashMap<String, String>();
//		
//		queryMap.put("name", "trivedi");
//		queryMap.put("Status", "active");
//		queryMap.put("gender", "male");
		
		Map<String, String> queryMap=Map.of("Name", "Trivedi",
				"Status", "Active");
		
		given().log().all()
		      .header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		      .queryParams(queryMap)
		           .when().log().all()
		              .get("/public/v2/users")
		                  .then().log().all()
		                     .assertThat().statusCode(200)
		                         .and()
		                           .contentType(ContentType.JSON);
		
	}
	
	@Test
    public void getUserWith_PathParam(){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		
//		Map<String, String> queryMap=new HashMap<String, String>();
//		
//		queryMap.put("name", "trivedi");
//		queryMap.put("Status", "active");
//		queryMap.put("gender", "male");
		
		Map<String, String> queryMap=Map.of("Name", "Trivedi",
				"Status", "Active");
		
		given().log().all()
		      .header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		      .pathParam("userID", "7381532")
		      .queryParams(queryMap)
		           .when().log().all()
		              .get("/public/v2/users/{userID}/posts")
		                  .then().log().all()
		                     .assertThat().statusCode(200)
		                         .and()
		                           .contentType(ContentType.JSON);
		
	}
	
	
	@DataProvider
	public Object[][] getUserID()
	{
		return new Object[][]
				{
			{"7381532"},
			{"7381533"},
			{"7381534"}
				};
	}
	
	@Test(dataProvider ="getUserID")
    public void getUserWith_PathParamWithDataProvider(String userData){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Map<String, String> queryMap=Map.of("Name", "Trivedi",
				"Status", "Active");
		
		given().log().all()
		      .header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		      .pathParam("userID", userData)
		      .queryParams(queryMap)
		           .when().log().all()
		              .get("/public/v2/users/{userID}/posts")
		                  .then().log().all()
		                     .assertThat().statusCode(200)
		                         .and()
		                           .contentType(ContentType.JSON);
		
	}
	
	@DataProvider
	public Object[][] getQueryParamData()
	{
		return new Object[][]
				{
			{"name","trivedi"},
			{"status","active"},
			{"gender","male"}
				};
	}
	
	@Test(dataProvider ="getQueryParamData")
    public void getUserWith_QueryParamWithDataProvider(String queryParamKey, String queryParamValue){
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		
		given().log().all()
		      .header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		      .queryParams(queryParamKey, queryParamValue)
		           .when().log().all()
		              .get("/public/v2/users")
		                  .then().log().all()
		                     .assertThat().statusCode(200)
		                         .and()
		                           .contentType(ContentType.JSON);
		
	}
	
	
	
}
