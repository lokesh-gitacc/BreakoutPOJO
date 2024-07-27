package breakoutTask;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class BaseClass {
	RequestSpecification req;
	ResponseSpecification resp_spec_200;
	
	@BeforeClass
	public void setup()
	{
		System.out.println("****** Generating RequestSpecification ***********");
		
		 req=new RequestSpecBuilder()
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.ALL)
		.setBaseUri("https://petstore.swagger.io/v2")
		.build();	
		 
		 System.out.println("****** Generated RequestSpecification ***********"); 
		 
		 System.out.println("****** Generating ResponseSpecification ***********");
		 
		 resp_spec_200=new ResponseSpecBuilder()
				 .expectContentType(ContentType.JSON)
				 .log(LogDetail.ALL)
				 .expectStatusCode(200)
				 .build();
		 
		 System.out.println("****** Generated ResponseSpecification ***********");
		 
	}
}
