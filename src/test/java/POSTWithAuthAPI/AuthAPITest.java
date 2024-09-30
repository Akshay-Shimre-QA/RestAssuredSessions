package POSTWithAuthAPI;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

import java.io.File;  
public class AuthAPITest {
	
	@Test
	public void getToken_JSONSTRING()
	{
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String Token=given().log().all()
		   .contentType(ContentType.JSON)
		      .body("{\r\n"
		      		+ "    \"username\" : \"admin\",\r\n"
		      		+ "    \"password\" : \"password123\"\r\n"
		      		+ "}")
		          .when()
		            .post("/auth")
		              .then()
		                .log() .all()
		                .assertThat()
						.statusCode(200).and()
		                    .extract()
		                      .path("token");
		
		System.out.println("==TOKEN==>"+Token);
		Assert.assertNotNull(Token);
		           
		
		
	}
	
	@Test
	public void getToken_JSONFile()
	{
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String Token=given().log().all()
		   .contentType(ContentType.JSON)
		      .body(new File("./src/test/resources/jsons/auth.json"))
		          .when().log().all()
		            .post("/auth")
		              .then()
		                .log() .all()
		                .assertThat()
						.statusCode(200).and()
		                    .extract()
		                      .path("token");
		
		System.out.println("==TOKEN==>"+Token);
		Assert.assertNotNull(Token);
		           
		
		
	}
	
	@Test
	public void getToken_POJO()
	{
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		Credentials creds=new Credentials("admin","password123");
		
		// POJO to JSON --> Serialization using ObjectMapper Class(Jackson lib)
		
		
		String Token=given().log().all()
		   .contentType(ContentType.JSON)
		      .body(creds)
		          .when().log().all()
		            .post("/auth")
		              .then()
		                .log() .all()
		                .assertThat()
						.statusCode(200).and()
		                    .extract()
		                      .path("token");
		
		System.out.println("==TOKEN==>"+Token);
		Assert.assertNotNull(Token);
		           
		
		
	}

}
