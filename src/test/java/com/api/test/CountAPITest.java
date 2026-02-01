package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
@Test
	public void CountAPITest() {
		//Header authheader=new Header("Authorization",getToken(Role.FD));
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_ok())
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
	.spec(SpecUtil.requestSpec())
	.when()
	.get("/dashboard/count")
	.then()
	.spec(SpecUtil.responseSpec(401));
	
	
	
}
}