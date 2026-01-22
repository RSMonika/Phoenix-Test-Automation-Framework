package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import com.api.pojo.UserCredentials;
import static com.api.utils.AuthTokenProvider.*;

//import static com.api.utils.ConfigManager.*;
import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsApiTest {
	
	@Test
	public void UserDetailsApiTest() throws IOException {
		
	
		Header authHeader=new Header("Authorization", getToken(FD));
		given()
		.baseUri(getProperty("BASE_URI"))
		.accept(ContentType.ANY)
		.header(authHeader)
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(2000l))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("com/response/file/userDetailsApiResponseFile.json"));
	}

}
