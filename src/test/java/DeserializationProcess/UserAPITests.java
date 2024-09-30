package DeserializationProcess;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPITests {

	

	//To generate random emailID
		public String randomEmail() {
		return "apiAutomation"+System.currentTimeMillis()+"@OpenKart.com";}
		
	@Test
	public void createUserWithlombok()
	{
		//1. Create User Process
		RestAssured.baseURI="https://gorest.co.in";
		
		UserLomBok	userOBJ=new UserLomBok.UserLomBokBuilder()
				  .name("Akshay Singh")
				  .email(randomEmail())
				  .gender("Male")
				  .status("active")
				  .build();		
		
		
		Response postResponse=	given().log().all()
			             .contentType(ContentType.JSON)
			                 .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			                       .body(userOBJ)  /// Serialization Process
			                          .when().log().all()
			                             .post("/public/v2/users");
		
		postResponse.prettyPrint();
		
		Integer userID=postResponse.jsonPath().get("id");
		
		System.out.println("User ID is :--->"+userID);
		
		//2. Get User using GET call
		
	Response getResponse=	given().log().all()
        .contentType(ContentType.JSON)
            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                     .when().log().all()
                        .get("/public/v2/users/"+userID);
	
	 
	    getResponse.prettyPrint();
	    
	   // Step 3.  De-serialization process started JSON to POJO
	    
	    ObjectMapper mapper=new ObjectMapper();
	    
	    try {
	    	UserLomBok userClassresponse=	mapper.readValue(getResponse.getBody().asString(), UserLomBok.class);
		
	    	System.out.println("Output is --->>"+userClassresponse.getId()+" "+userClassresponse.getName() + " " + userClassresponse.getEmail() + " "+
	    			userClassresponse.getGender()+" "+userClassresponse.getStatus());
	    
	    	// Assert actual VS expected as below
	    	Assert.assertEquals(userClassresponse.getId(), userID);
	    	Assert.assertEquals(userClassresponse.getEmail(), userOBJ.getEmail());

	    } catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	}
	
}
