package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.StudentEndPoints2;
import api.payload.Student;
import io.restassured.response.Response;

public class StudentTests2 {
	
	Faker faker ;
	Student studentPayload;
	String id;
	public Logger logger;
	
	
	@BeforeClass()
	public void setupData()
	{
		faker = new Faker();
		studentPayload = new Student();
		studentPayload.setName(faker.name().name());
		studentPayload.setLocation(faker.country().name());
		studentPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		// Define array size
        int size = 2;
        String[] course = new String[size];

        // Populate array using Faker
        for (int i = 0; i < size; i++) {
        	course[i] = faker.educator().course();
        }
		 studentPayload.setCourses(course);
		
		logger = (Logger) LogManager.getLogger(this.getClass());
	}
	
	
	@Test(priority=1)
	public void testPostStudent()
	
	{
		logger.info("This is POST student");
		Response response = StudentEndPoints2.createStudent(studentPayload);
		response.then().log().body();
		id = response.jsonPath().getString("id");
		
		//System.out.println(id);
		Assert.assertEquals(response.getStatusCode(), 201);
			
		logger.info("This is student is created");
		
		
		

	}
	
	
	@Test(priority=2)
	public void testGetStudent()
	{
		Response response = StudentEndPoints2.readStudent(id);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	

	@Test(priority=3)
	public void testPutStudent()
	{
		
		//studentPayload.setName(faker.name().name());
		studentPayload.setLocation(faker.country().name());
		studentPayload.setPhone(faker.phoneNumber().cellPhone());
		// Define array size
        int size = 2;
        String[] course = new String[size];

        // Populate array using Faker
        for (int i = 0; i < size; i++) {
        	course[i] = faker.educator().course();
        }
		 studentPayload.setCourses(course);
		
		Response response = StudentEndPoints2.updateStudent(id,studentPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterupdate = StudentEndPoints2.readStudent(id);
		responseAfterupdate.then().log().body();
	
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);

}
	
	@Test(priority=4)
	public void testDeleteStudent()
	{
		Response response = StudentEndPoints2.deleteStudent(id);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	
}
