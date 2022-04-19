package RestTesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class E_Commerce {
	
	

	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String accessToken;
	public static String UserId;
	public static String emailId;
	@Test(priority = 0, enabled = false)
	public void signup()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"Abhi991@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/signup")
			
			.then()
			.assertThat().statusCode(201).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	message = js.get("message");
	System.out.println(message);
	
	
}
	
	
	@Test(priority = 1)
	public void Login()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"Abhi991@gmail.com\",\n"
			+ "	\"password\": \"krishna@123\"\n"
			+ "}";
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/login")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	//nw i have to fetch the id
	accessToken = js.get("accessToken");
	System.out.println(accessToken);
	
}
	@Test(priority = 2)
	public void getallusers()
	{
		RestAssured.baseURI =baseurl;

	Response resposne = given()
			.header("Content-Type","application/json")
			.header("Authorization", "Bearer "+accessToken)
			
			.when()
			.get("/user/")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	
	System.out.println(jsonresponse);
	UserId = js.get("users[24]._id");
	System.out.println("user[24]"+UserId);
	emailId = js.get("users[24].email");
	System.out.println("User[24]"+emailId);
}
	@Test(priority = 3)
	public void delete()
	{
		RestAssured.baseURI =baseurl;

	Response resposne = given()
			.header("Content-Type","application/json")
			.header("Authorization", "Bearer "+accessToken)
			
			.when()
			.delete("/user/"+UserId)
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	JsonPath js = new JsonPath(jsonresponse);
	
	message = js.get("message");
	System.out.println(message);
	System.out.println("Folllowing user got deleted: "+emailId);
	System.out.println("Abhishek1");
}
}
