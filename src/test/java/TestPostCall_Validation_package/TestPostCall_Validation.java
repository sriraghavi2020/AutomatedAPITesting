package TestPostCall_Validation_package;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import APICall_Package.Post_Call_Validation;
import BasePackage.BaseClass;
import PayLoad_Data.UserData_PostCall;

public class TestPostCall_Validation extends BaseClass {

	String url;
	String PostAPIurl;
	String uri;
	Post_Call_Validation post_call_validation;
	CloseableHttpResponse response;

	@BeforeMethod
	public void setUp() {

		url = readPropsFile("url");
		PostAPIurl = readPropsFile("PostAPIurl");

		uri = url + PostAPIurl;

	}

	@Test
	public void TestPostCall_WithValidation() throws JsonGenerationException, JsonMappingException, IOException {
		post_call_validation = new Post_Call_Validation();
		// Header information
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");

		// Jackson Api
		// *Java object
		ObjectMapper mapper = new ObjectMapper();
		UserData_PostCall userData = new UserData_PostCall("tom", "leader");

		// Converting java object to JSON----> moving the above data to json file
		mapper.writeValue(new File(
				"C:\\Users\\Kiriti\\eclipse-workspace\\APITesting\\src\\main\\java\\PayLoad_Data\\payload.json"),
				userData);

		// Converting *java Object to JsonString
		String payloadAsString = mapper.writeValueAsString(userData);
		System.out.println("PayLoad is " + payloadAsString);

		// Hitting PostRequest
		
		//*response
		response = post_call_validation.postCallWithHeader(uri, payloadAsString, headermap);

		// Validation of PostRequest
		// 1. Status code
		int StatusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status code is " + StatusCode);
		Assert.assertEquals(StatusCode, reponse_code_201);

		// 2.payload
		// Getting Json Response
		String Jsonresponse = EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println("PostCall response as String " + Jsonresponse);
		
		
		//Converting JSON String to Objcet to compare and Assert
		JSONObject obj = new JSONObject(Jsonresponse);
		System.out.println("PostCall response as JSON Object "+obj);
		
		//Converting JSON object to Java Object
		UserData_PostCall userDataResponse = mapper.readValue(Jsonresponse, UserData_PostCall.class);
		System.out.println(userDataResponse);
		
		System.out.println(userData.getName().equals(userDataResponse.getName()));
		System.out.println(userData.getJob().equals(userDataResponse.getJob()));
		String id = userDataResponse.getId();
		System.out.println("Id: "+id);
		String CrtAt = userDataResponse.getCreatedAt();
		System.out.println("Created At: "+CrtAt);
		
		Assert.assertTrue(userData.getName().equals(userDataResponse.getName()));
		Assert.assertTrue(userData.getJob().equals(userDataResponse.getJob()));
	
	
	
	
	}
	
	
	

}
