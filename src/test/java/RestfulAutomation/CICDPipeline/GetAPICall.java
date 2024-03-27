package RestfulAutomation.CICDPipeline;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAPICall {
	
	
	@Test
	public void getMethodCall()
	{
RestAssured.baseURI="http://216.10.245.166";
		
		Response basebook = given()
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"name\": \"Mindcraft\",\r\n"
				+ "    \"isbn\": \"gfh\",\r\n"
				+ "    \"aisle\": \"300\",\r\n"
				+ "    \"author\": \"Pavan Ingale\"\r\n"
				+ "}")
		.when()
		.post("/Library/Addbook.php");
		
		
		JsonPath js = new JsonPath(basebook.asString());
		
		String id = js.get("ID");
		
		System.out.println("ID is : "+id);
		
		
		given()
		.queryParam("ID",id)
		.when().get("Library/GetBook.php")
		.then().log().all().assertThat().statusCode(200);
		
		
		
		
		
		
	}
	
	
	
	

}
