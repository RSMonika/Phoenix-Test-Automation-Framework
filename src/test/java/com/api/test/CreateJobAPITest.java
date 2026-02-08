package com.api.test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;
public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		
		Problems problems=new Problems(1, "battery issue");//Problems object created here!!
		//Problems[] problemsarray=new Problems[1];//array object created here!!
		List<Problems>  problemlist=new ArrayList<Problems>();
		//problemsarray[0]=problems;//variable assign
		problemlist.add(problems);
		
		CustomerProduct customer_product=new CustomerProduct("2025-09-30T18:30:00.000Z", "22000736434051", "22000736434051", "22000736434051", "2025-09-30T18:30:00.000Z", 1, 1);
		CustomerAddress customer_address=new CustomerAddress("d 404", "abc", "streetname", "landmark", "area", "10101", "Goa", "India");
		Customer customer=new Customer("Monika", "Chaudhary", "0123456789", "", "abcd@ymail.com", "");
		//System.out.println(customer.email_id());
		CreateJobPayload CreateJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customer_address, customer_product, problemlist);
		
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, CreateJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"))
		.body(matchesJsonSchemaInClasspath("com/response/file/createJobAPIResponseFile.json"));
		//.time(lessThan(1000l));




	}

}
