package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	@Test
public void loginAPITest()
	
	 {
		UserCredentials user=new UserCredentials("iamfd","password");
		given()//Give request specification!!
		.baseUri("http://64.227.160.186:9000/v1")
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
		.time(lessThan(3000L));
		
		
		
		
		
		
	}

}
