package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

import  io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {//This is a helper class.!!!!Its only job is to prepare common API request settings.
	//common API request template.

	
	public static RequestSpecification requestSpec() {
		//to take care of common request sections   Get---
		
		
		      RequestSpecification  requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		      return requestSpecification;
		
	}
	
	public static RequestSpecification requestSpec(Object user) {
		//to take care of common request sections PUT/PATCH/POST!!
		
		
		      RequestSpecification  requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(user)
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		      return requestSpecification;
		
	}
	public static RequestSpecification requestSpecWithAuth(Role role) {
		
		
		      RequestSpecification  requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization",AuthTokenProvider.getToken(role))
				.log(LogDetail.METHOD)
				.log(LogDetail.HEADERS)
				.log(LogDetail.BODY)
				.build();
		      return requestSpecification ;
		
	}
	public static ResponseSpecification responseSpec_ok() {
		ResponseSpecification	responseSpecification=new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
	public static ResponseSpecification responseSpec(int statusCode) {
		ResponseSpecification	responseSpecification=new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectContentType(ContentType.HTML)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification	responseSpecification=new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectContentType(ContentType.TEXT)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		return responseSpecification;
	}
}
