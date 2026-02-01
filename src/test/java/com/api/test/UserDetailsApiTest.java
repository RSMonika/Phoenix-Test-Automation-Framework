package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

import static com.api.utils.AuthTokenProvider.*;

//import static com.api.utils.ConfigManager.*;
import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsApiTest {
	
	@Test
	public void UserDetailsApiTest() throws IOException {
		
	
		//Header authHeader=new Header("Authorization", getToken(FD));
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_ok())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("com/response/file/userDetailsApiResponseFile.json"));
	}

}
