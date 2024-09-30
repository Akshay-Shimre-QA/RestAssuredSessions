package UpdateUser;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserWithLombok_POJO {

	//To generate random emailID
		public String randomEmail() {
		return "apiAutomation"+System.currentTimeMillis()+"@OpenKart.com";}
		
		@Test
		public void updateUserWith_POJO()
		{
			RestAssured.baseURI="https://gorest.co.in";
			
			//User userOBJ=new User("Akshay",randomEmail(),"MALE","active");
			
			UserLombok userOBJ=new UserLombok("Akshay",randomEmail(),"MALE","active");
			
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
		
		}
	
		
		@Test
		public void updateUserWith_BuilderPattern()
		{
			//creating user class obj using Lombok
			
			RestAssured.baseURI="https://gorest.co.in";
			
			UserLombok	userOBJ=	new UserLombok.UserLombokBuilder()
			  .name("Akshay Singh")
			  .email(randomEmail())
			  .gender("Male")
			  .status("active")
			  .build();			
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
		
		}
	
}
