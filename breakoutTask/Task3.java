package breakoutTask;

import static  io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.response.Response;

public class Task3 extends BaseClass{
	
	String username ="Praful";
	@Test(priority = 1)
	public void createuser()
	{
		User newUser = new User();
		newUser.setId(786);
		newUser.setUsername(username);
		newUser.setFirstName("Mukesh");
		newUser.setLastName("Otwani");
		newUser.setEmail("mukesh@gmail.com");
		newUser.setPassword("mukesh@123");
		newUser.setPhone("9090909090");
		newUser.setUserStatus(1);
		
//		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
//				.header("Content-Type","application/json")
//				.header("Accept","application/json")
				.spec(req)
				.body(newUser)
				.post("/user");

		resp.then().assertThat().spec(resp_spec_200);
		System.out.println("Response: " + resp.jsonPath().prettyPrint());
//		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertEquals(resp.jsonPath().getString("code"), "200");
		
	}	
	@Test(priority = 2)
	public void getuser()
	{
		
//		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
//				.header("Content-Type","application/json")
//				.header("Accept","application/json")
				.spec(req)
				.pathParam("uname", username)
				.get("/user/{uname}");
		System.out.println("Response: " + resp.jsonPath().prettyPrint());

//		Assert.assertEquals(resp.getStatusCode(), 200);
		resp.then().assertThat().spec(resp_spec_200);
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
		User updUser = new User();
		updUser.setId(786);
		updUser.setUsername(username);
		updUser.setFirstName("Mukesh");
		updUser.setLastName("Otwani");
		updUser.setEmail("mukesh@gmail.com");
		updUser.setPassword("mukesh@123");
		updUser.setPhone("9090909090");
		updUser.setUserStatus(1);


//		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
//				.header("Content-Type","application/json")
//				.header("Accept","application/json")
				.spec(req)
				.pathParam("uname", username)
				.body(updUser)
				.put("/user/{uname}");
		System.out.println("Response: " + resp.jsonPath().prettyPrint());

//		Assert.assertEquals(resp.getStatusCode(), 200);
		resp.then().assertThat().spec(resp_spec_200);
		Assert.assertEquals(resp.jsonPath().getString("message"), "786");

		
	}

	@Test(priority = 4)
	public void deleteuser()
	{
//		baseURI="https://petstore.swagger.io/v2";
		
		Response resp = given()
//				.header("Accept","application/json")
				.spec(req)
				.pathParam("uname", username)
				.delete("/user/{uname}");
		System.out.println("Response: " + resp.jsonPath().prettyPrint());

//		Assert.assertEquals(resp.getStatusCode(), 200);
		resp.then().assertThat().spec(resp_spec_200);
		Assert.assertEquals(resp.jsonPath().getString("message"), username);

		
	}

}
