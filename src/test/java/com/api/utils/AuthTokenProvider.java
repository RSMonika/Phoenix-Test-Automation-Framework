package com.api.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import static com.api.constants.Role.*;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


public class AuthTokenProvider {//Utility class--static method---

	private  AuthTokenProvider() {//no one can create object of AuthTokenProvider class outside this class!!
		
	}
	
	
	public static String getToken(Role role) {
//I want to make request for the login api and we want to extrat the token and print it on console!!
		//UserCredentials usercred=new UserCredentials("iamfd","password");
		UserCredentials usercred = null;
		if(role==FD) {
			 usercred=	new UserCredentials("iamfd","password");
		}
		else if (role==SUP) {
			 usercred=	new UserCredentials("iamsup","password");
		}
		else if (role==QC) {
			 usercred=	new UserCredentials("iamqc","password");
		}
		else if (role==ENG) {
			 usercred=	new UserCredentials("iameng","password");
		}
		String token=given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(usercred)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.extract()
		.jsonPath()
		.getString("data.token");
		return token;
		
	}

}
