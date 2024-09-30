package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseDataConcept {
	
	
	
	@Test
	public void getContactsAPITest_WithInvalidToken()
	{
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

		given().log().all()
		.header("Authorization", "Bearer eyasasas1JhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjUyOTIxNTh9.3FBGhtqXaTsZWnbFNTTTgwx9c96ZdehVQEhj-AnGV8c")
			.when().log().all()
				.get("/contacts")
					.then().log().all()
						.assertThat()
								.statusCode(401)
									.and()
									     .body("error", equalTo("Please authenticate."));
												
	

	}
	
	@Test
	public void getContactsAPITest_WithInvalidToken_WithExtractOption()
	{
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	String errorMessage=given().log().all()
		.header("Authorization", "Bearer eyasasas1JhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjUyOTIxNTh9.3FBGhtqXaTsZWnbFNTTTgwx9c96ZdehVQEhj-AnGV8c")
			.when().log().all()
				.get("/contacts")
				  .then()
				     .extract()
                        .response()
                          .path("error");
	
	
	System.out.println("JSON error message is --->"+errorMessage);
												
	Assert.assertEquals(errorMessage, "Please authenticate.");

	}
	
	@Test
	public void getSingleUser_WithJsonPath()
	{
		RestAssured.baseURI = "https://gorest.co.in";

	Response response=given().log().all()
			.header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		           .when().log().all()
		              .get("/public/v2/users/6940822");
		                 
				
	response.prettyPrint();
	
	JsonPath js=response.jsonPath();
	
	String name=js.getString("name");
	int id=js.getInt("id");
	
	System.out.println("NAME IS --->"+name);
	System.out.println("ID IS --->"+id);

												

	}
	
	@Test
	public void getListOfUser_WithJsonPath()
	{
		RestAssured.baseURI = "https://gorest.co.in";

	Response response=given().log().all()
			.header("Authorization", "Bearer 1e2837d2377b1f31cbf80e30a103ef84964299fe508e02ef39043ac3bde46749")
		           .when().log().all()
		              .get("/public/v2/users");
		                 
				
	response.prettyPrint();
	
	JsonPath js=response.jsonPath();
	
	List<String> name=js.getList("name");
	List<Integer> id=js.getList("id");
	
	System.out.println("NAMES are --->"+name);
	System.out.println("ID are --->"+id);
	
	for(Integer e: id)
	{
		System.out.println(e);
		
	}

												

	}
	
	//NESTED JSON
	@Test
	public void getProducts_NestedData_WithJsonPath()
	{
		RestAssured.baseURI = "https://fakestoreapi.com";

	Response response=given().log().all()
			                        .get("/products");
			
		                 
				
	response.prettyPrint();
	
	JsonPath js=response.jsonPath();
	
	List<Integer> id=js.getList("id");
	System.out.println("id are --->"+id);
	
	List<Object> prices=js.getList("price");
	System.out.println("prices are --->"+prices);
	
	List<Object> rates=js.getList("rating.rate");
	System.out.println("rates are --->"+rates);
	
	List<Object> count=js.getList("rating.count");
	System.out.println("count are --->"+count);
	
	for(int i=0; i<id.size(); i++)
	{
		int ids=id.get(i);
		Object pricess=prices.get(i);
		Object allRates=rates.get(i);
		Object allCount=count.get(i);
		
		System.out.println("IDs are : "+ ids +" Prices are : "+pricess +" allRates are : "+allRates+ " All counts are : "+allCount);
	}

	}

}
