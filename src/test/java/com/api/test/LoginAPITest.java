package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import static com.api.utils.ConfigManager.*;
import static com.api.utils.ConfigManager.getProperty;
import static com.api.utils.ConfigManagerOLD.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
public void loginAPITest() throws IOException

	
	 {

		UserCredentials user=new UserCredentials("iamfd","password");
		given()//Give request specification!!
		.baseUri(getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and()
		.body(user)
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.post("login")
		.then()
		.statusCode(200)
		.log().all()
		.body("message", equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("com/response/file/loginApiResponseFile.json"))
		.time(lessThan(4000l));
		
		
		
		
		
		
	}

}
