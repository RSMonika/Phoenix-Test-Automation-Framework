package com.api.test;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
@Test
	public void CountAPITest() {
		Header authheader=new Header("Authorization",getToken(Role.FD));
		
		given()
		.baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.header(authheader)
		.and()
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(2000l))
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",not(blankOrNullString()))
		.body("data.key",containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
		//.body(null, null)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("com/response/file/countAPIResponseFile.json"));

	
	}
@Test
public void NegativeCountAPITest() {
	
	given()
	.baseUri(getProperty("BASE_URI"))
	.and()
	.log().uri()
	.log().method()
	.log().headers()
	.when()
	.get("/dashboard/count")
	.then()
	.log().all()
	.statusCode(401);
	
	
	
}
}