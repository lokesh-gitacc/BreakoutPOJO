package breakoutTask;

import static  io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.response.Response;

public class Task2 {
	
	String username ="Mukesh";
	@Test(priority = 1)
	public void createuser()
	{
		
		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body(new File("./src/test/resources/jsonFiles/createuser.json"))
				.post("/user");
		
		System.out.println("Response: " + resp.jsonPath().prettyPrint());
		Assert.assertEquals(resp.getStatusCode(), 200);
		//Assert.assertTrue(resp.jsonPath().getString("code").equals("200"));		
		Assert.assertEquals(resp.jsonPath().getString("code"), "200");
		
	}	
	@Test(priority = 2)
	public void getuser()
	{
		
		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.pathParam("uname", username)
				.get("/user/{uname}");
		System.out.println("Response: " + resp.jsonPath().prettyPrint());

		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertEquals(resp.jsonPath().getString("id"), "786");
		Assert.assertEquals(resp.jsonPath().getString("username"), username);
		Assert.assertEquals(resp.jsonPath().getString("firstName"), "Mukesh");
		Assert.assertEquals(resp.jsonPath().getString("lastName"), "Otwani");
		Assert.assertEquals(resp.jsonPath().getString("email"), "mukesh@gmail.com");
		Assert.assertEquals(resp.jsonPath().getString("password"), "mukesh@123");
		Assert.assertEquals(resp.jsonPath().getString("phone"), "9090909090");
		Assert.assertEquals(resp.jsonPath().getString("userStatus"), "1");

		
	}
	
	@Test(priority = 3)
	public void updateuser()
	{
		username = "Mahesh";
		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.pathParam("uname", username)
				.body(new File("./src/test/resources/jsonFiles/updateuser.json"))
				.put("/user/{uname}");
		System.out.println("Response: " + resp.jsonPath().prettyPrint());

		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertEquals(resp.jsonPath().getString("message"), "786");

		
	}

	@Test(priority = 4)
	public void deleteuser()
	{
		baseURI="https://petstore.swagger.io/v2";
		System.out.println("Username: " + username);
		Response resp = given()
				.header("Accept","application/json")
				.pathParam("uname", username)
				.log().all()
				.delete("/user/{uname}");
		System.out.println("Response: " + resp.jsonPath().prettyPrint());

		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertEquals(resp.jsonPath().getString("message"), username);

		
	}

}
