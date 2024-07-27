package breakoutTask;

import static  io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.response.Response;

public class Task1 {
	
	String username ="Praful";
	@Test(priority = 1)
	public void createuser()
	{
		
		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.body("{\r\n"
						+ "\"id\": 786,\r\n"
						+ "\"username\": \"" + username + "\",\r\n"
						+ "\"firstName\": \"Mukesh\",\r\n"
						+ "\"lastName\": \"Otwani\",\r\n"
						+ "\"email\": \"mukesh@gmail.com\",\r\n"
						+ "\"password\": \"mukesh@123\",\r\n"
						+ "\"phone\": \"9090909090\",\r\n"
						+ "\"userStatus\": 1\r\n"
						+ "}")
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
		username = "Lokesh";
		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
				.header("Content-Type","application/json")
				.header("Accept","application/json")
				.pathParam("uname", username)
				.body("{\r\n"
						+ "\"id\": 786,\r\n"
						+ "\"username\": \""+ username +"\",\r\n"
						+ "\"firstName\": \"Mukesh\",\r\n"
						+ "\"lastName\": \"KOtwani\",\r\n"
						+ "\"email\": \"mukesh@gmail.com\",\r\n"
						+ "\"password\": \"mukesh@123\",\r\n"
						+ "\"phone\": \"9090909090\",\r\n"
						+ "\"userStatus\": 1\r\n"
						+ "}")
				.put("/user/{uname}");
		System.out.println("Response: " + resp.jsonPath().prettyPrint());

		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertEquals(resp.jsonPath().getString("message"), "786");

		
	}

	@Test(priority = 4)
	public void deleteuser()
	{
		baseURI="https://petstore.swagger.io/v2";
		
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
