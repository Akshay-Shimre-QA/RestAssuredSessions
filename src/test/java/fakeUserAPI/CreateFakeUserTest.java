package fakeUserAPI;

import org.testng.annotations.Test;

import fakeUserAPI.FakeUserTestLombok.Address.GEOLocation;
import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateFakeUserTest {

	
	@Test
	public void createUserTest()
	{
		//In this formation also we can define-->GEOLocation geoLocation=new GEOLocation("-234.90","52.99");
		
		FakeUserTestLombok.Address.GEOLocation geoLocation=new FakeUserTestLombok.Address.GEOLocation("-234.90","52.99");
		FakeUserTestLombok.Address address=new FakeUserTestLombok.Address("Pune","Wall",55,"400615",geoLocation);
		FakeUserTestLombok.Name name=new FakeUserTestLombok.Name("Akshay","Singh");
		FakeUserTestLombok user=new FakeUserTestLombok("ak.sh@ggmail.com","Ak_123","Test@123","4525246252",name,address);
		
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
	
	@Test
	public void createUserTest_BuilderPattern()
	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
		FakeUserTestLombok.Address.GEOLocation geoLocation=	new FakeUserTestLombok.Address.GEOLocation.GEOLocationBuilder()
		.lat("25.33").longitude("55.21").build();
		
		FakeUserTestLombok.Address address=new FakeUserTestLombok.Address.AddressBuilder()
				.city("pune").number(2).street("wallstreet").zipcode("400615")
		        .geoLocation(geoLocation).build();
		
		FakeUserTestLombok.Name name=new FakeUserTestLombok.Name.NameBuilder()
				.firstname("Akshay")
				.lastname("Singh")
				.build();
		
		FakeUserTestLombok user=new FakeUserTestLombok.FakeUserTestLombokBuilder()
				.address(address)
				.name(name)
				.email("ak.sh@gmail.com")
		        .password("Test@123")
		        .phone("252-858-3210")
		        .username("ak_123")
		        .build();
		
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
