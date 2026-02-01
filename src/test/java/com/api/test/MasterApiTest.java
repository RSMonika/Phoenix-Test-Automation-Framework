package com.api.test;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterApiTest {

	@Test
	
	public void MasterApiTest() {
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))///empty contentype
		.when()
		.post("/master")
		.then()
		.and()
		.spec(SpecUtil.responseSpec_ok())
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("com/response/file/masterAPIResponseFile.json"))
		.body("data",hasKey("mst_oem"))
		.body("$",hasKey("message"))
		.body("data.mst_oem.size()",equalTo(2));
		
		
	}
	@Test
	public void NegativeMasterApiTest() {
		given()
		.spec(SpecUtil.requestSpec())
		///empty contentype
		.when()
		.post("/master")
		.then()
		.and()
		.spec(SpecUtil.responseSpec(401));
	}
	
	
}
