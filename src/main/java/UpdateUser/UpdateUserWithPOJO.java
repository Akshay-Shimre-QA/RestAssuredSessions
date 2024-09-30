package UpdateUser;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UpdateUserWithPOJO {

	// 1. create a fresh user
	// 2. GET
	// 3. PUT or Patch
	
	//To generate random emailID
	public String randomEmail() {
	return "apiAutomation"+System.currentTimeMillis()+"@OpenKart.com";}
	
	@Test
	public void updateUserWith_POJO()
	{
		RestAssured.baseURI="https://gorest.co.in";
		
		User userOBJ=new User("Akshay",randomEmail(),"MALE","active");
		
		//1. POST --> Fetch UserID
		
	Response postResponse=	given().log().all()
		             .contentType(ContentType.JSON)
		                 .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		                       .body(userOBJ)
		                          .when().log().all()
		                             .post("/public/v2/users");
		
	postResponse.print();
	Integer ID=postResponse.jsonPath().get("id");
	String name= postResponse.jsonPath().get("name");
	
	System.out.println("UserID is : --->"+ID);
	System.out.println("Name is : --->"+name);

	System.out.println("================XXXXXX==================");
		                                
  //2. Update : PUT : update user
	
	userOBJ.setName("Akshay Singh");
	userOBJ.setStatus("inactive");
	
       given().log().all()
	   .contentType(ContentType.JSON)
	   .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
	   .body(userOBJ)
	      .when().log().all()
	           .patch("/public/v2/users/"+ID)
	               .then()
	                  .assertThat()
	                     .statusCode(200)
	                         .and()
	                               .body("id", equalTo(ID))
	                               .and()
	                               .body("name",equalTo( userOBJ.getName()))
	                               .and()
	                               .body("status", equalTo( userOBJ.getStatus())); 
       
       //3. Get 
	
	}
	
}
