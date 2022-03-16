package RestTesting;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Trello {

	public static String baseurl = "https://api.trello.com";
	public static String id;
	
	@Test(priority = 0)
	public void createBoard()
	{
		
		//i want to fetch the base url so that i can add other thing to that base url
		//This is the first step to provide base url to the request, Storing url\
		//restassured is class for given,when,then
		RestAssured.baseURI = baseurl;
		
		//in rest assured we have three keyword
		//given : pre-condition includes like parameter,header, authorization,
		//when : condition i am testing like resource //iwe have to provide the name //that is called resource /1/board/ and the method name
		//then : post condition : is something like response check the response
		Response resposne = given().queryParam("name", "Abhishek11 Moolya board")
		.queryParam("key", "3ed93e9575b33ccdd13ca0bbd89cd36e")
		.queryParam("token", "3684993ef508faf97baa595da4f586f05c0e47884fb29ce0c2894dfb87111924")
		.header("Content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
		
		//This is to fetch the response as a string
		String jsonresponse = resposne.asString();
		
		//use JsonPath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		
		//to fetch the id
		id = js.get("id");
		
	}
	
	//if we make any @Test method enabled =false/ that method will not execute
	
	@Test(priority = 1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
		
		Response response =	given()
		.queryParam("key", "3ed93e9575b33ccdd13ca0bbd89cd36e")
		.queryParam("token", "3684993ef508faf97baa595da4f586f05c0e47884fb29ce0c2894dfb87111924")
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
	
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
	}
	
	@Test(priority = 2)
	public void DeleteBoard()
	{
		RestAssured.baseURI = baseurl;
		
		Response response =	given()
		.queryParam("key", "3ed93e9575b33ccdd13ca0bbd89cd36e")
		.queryParam("token", "3684993ef508faf97baa595da4f586f05c0e47884fb29ce0c2894dfb87111924")
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
	
		String jsonresponse = response.asString();
		System.out.println(jsonresponse);
	}
}