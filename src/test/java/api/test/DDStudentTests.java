package api.test;

import java.io.IOException;


import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.StudentEndPoints;
import api.payload.Student;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDStudentTests {
	Student studentPayload;
	String id;

	
	@DataProvider(name="getData",indices = {1})
	public String[][] setValue() throws IOException{
			//classname.methodname and return the value 
		return DataProviders.readExcel();
				
	
	}
	
	
	
	@Test(dataProvider="getData",priority=1)
	public void testPostStudent(String name, String location, String phone, String course)
	{
		studentPayload = new Student();
		studentPayload.setName(name);
		studentPayload.setLocation(location);
		studentPayload.setPhone(phone);
		
		String coursearray[] = course.split(",");
		 studentPayload.setCourses(coursearray);
		
	
		Response response = StudentEndPoints.createStudent(studentPayload);
		response.then().log().body();
		id = response.jsonPath().getString("id");
		
		//System.out.println(id);
		Assert.assertEquals(response.getStatusCode(), 201);
			
}
	
	@Test(priority=2)
	public void testDeleteStudent()
	{
		Response response = StudentEndPoints.deleteStudent(id);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	

}
