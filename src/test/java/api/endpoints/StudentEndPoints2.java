package api.endpoints;
import	static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentEndPoints2 {
	
	static ResourceBundle getEndpoints()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createStudent(Student payload)
	{
		
		String post_url = getEndpoints().getString("post_url");

		Response  response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url);
			
		return response;	
				
		
	}
	
	
	public static Response readStudent(String studentId)
	{
		String get_url = getEndpoints().getString("get_url");
		Response response = given()
								.pathParam("studentid", studentId)
							.when()
								.get(get_url);
		return response;
								
				
	}
	
	
	public static Response updateStudent(String studentId,Student payload)
	{
		String put_url= getEndpoints().getString("put_url");
		
		Response  response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("studentid", studentId)
			.body(payload)
					
		.when()
			.put(put_url);
			
		return response;	
				
		
	}
	
	
	
	public static Response deleteStudent(String studentId)
	{
		String delete_url= getEndpoints().getString("delete_url");
		Response response = 
				given()
					.pathParam("studentid", studentId)
				.when()
					.delete(delete_url);
		return response;
								
				
	}
	

}
