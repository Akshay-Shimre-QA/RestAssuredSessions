package DeserializationProcess;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPItestWithJSONArrayResponse {
	


	

	//To generate random emailID
		public String randomEmail() {
		return "apiAutomation"+System.currentTimeMillis()+"@OpenKart.com";}
		
	@Test
	public void createUserWithlombok()
	{
		
	
		//1. Get User using GET call
		
		RestAssured.baseURI="https://gorest.co.in";
	
	Response getResponse=	given().log().all()
                            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                     .when().log().all()
                        .get("/public/v2/users");
	
	 
	    getResponse.prettyPrint();
	    
	   // Step 2.  De-serialization process started JSON to POJO
	    
	    ObjectMapper mapper=new ObjectMapper();
	    
	    try {
	    	UserLomBok[] userClassresponse=	mapper.readValue(getResponse.getBody().asString(), UserLomBok[].class);  	
	    	 List<UserLomBok> userList = new ArrayList<>(Arrays.asList(userClassresponse));
	         List<Integer> idList = new ArrayList<>();
	         
	    	for (UserLomBok userData : userClassresponse) {
	            System.out.println("Name :-->" + userData.getName()); // Corrected this line
	            System.out.println("ID :-->" + userData.getId()); // Corrected this line

	            System.out.println("==========xxxxxxxxxxxx===========");
	            
	            Integer id = userData.getId(); 
		    	if(id!=null)
		    	{
		    		idList.add(id);
		    	}
		    	else
		    	{
                    System.out.println("Found a null ID for user: " + userData.getName()); // Or handle as needed

		    	}
		    	
		    	 
	        }
	    	
	    	// Verify all IDs are not null
            if (idList.size() == userList.size()) {
                System.out.println("All IDs are valid.");
            } else {
                System.out.println("Some IDs are null.");
            }
	    	
	    	
	    } catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	}
	


}
