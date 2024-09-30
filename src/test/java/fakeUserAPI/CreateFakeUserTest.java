package fakeUserAPI;

import org.testng.annotations.Test;

import fakeUserAPI.FakeUserTestLombok.Address.GEOLocation;
import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateFakeUserTest {

	//In this formation also we can define-->GEOLocation geoLocation=new GEOLocation("-234.90","52.99");
	
	FakeUserTestLombok.Address.GEOLocation geoLocation=new FakeUserTestLombok.Address.GEOLocation("-234.90","52.99");
	FakeUserTestLombok.Address address=new FakeUserTestLombok.Address("Pune","Wall",55,"400615",geoLocation);
	FakeUserTestLombok.Name name=new FakeUserTestLombok.Name("Akshay","Singh");
	FakeUserTestLombok user=new FakeUserTestLombok("ak.sh@ggmail.com","Ak_123","Test@123","4525246252",name,address);
	
	@Test
	public void createUserTest()
	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
	Response response=given().log().all()
		.body(user)
		  .when()
		    .post("/users")
		     .then()
		       .assertThat()
		         .statusCode(200)
		           .extract().response();
		          
		            
		     
	response.prettyPrint();
		
	}
}
