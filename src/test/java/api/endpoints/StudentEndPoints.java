package api.endpoints;
import	static io.restassured.RestAssured.*;

import api.payload.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentEndPoints {
	
	public static Response createStudent(Student payload)
	{
		Response  response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url);
			
		return response;	
				
		
	}
	
	
	public static Response readStudent(String studentId)
	{
		Response response = given()
								.pathParam("studentid", studentId)
							.when()
								.get(Routes.get_url);
		return response;
								
				
	}
	
	
	public static Response updateStudent(String studentId,Student payload)
	{
		Response  response =
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("studentid", studentId)
			.body(payload)
					
		.when()
			.put(Routes.put_url);
			
		return response;	
				
		
	}
	
	
	
	public static Response deleteStudent(String studentId)
	{
		Response response = 
				given()
					.pathParam("studentid", studentId)
				.when()
					.delete(Routes.delete_url);
		return response;
								
				
	}
	

}
