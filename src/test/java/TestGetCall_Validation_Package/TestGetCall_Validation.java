package TestGetCall_Validation_Package;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import APICall_Package.Get_Call_ResponseValidation;
import BasePackage.BaseClass;
import Util_JSONResponse_Package.GetCall_JSONResponse;

public class TestGetCall_Validation extends BaseClass{
	
	
	String url;
	String APIurl;
	String uri;
	Get_Call_ResponseValidation get_call_responsevalidation;
	CloseableHttpResponse response;

	@BeforeMethod
	public void setUp() {

//		readPropsFile("url");
//		readPropsFile("APIurl");

		url = readPropsFile("url");
		APIurl = readPropsFile("APIurl");

		uri = url + APIurl;
	}
	
	
	@Test
	public void testGetCall_withoutHeader() throws ClientProtocolException, IOException {
		get_call_responsevalidation = new Get_Call_ResponseValidation();
		response = get_call_responsevalidation.getRequest(uri);
		
		
		//1 Geeting Status code 
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status code is " + statusCode);
		// Status code Validation
		Assert.assertEquals(statusCode, reponse_code_200, "Code not matched");
		
		
		//2. Getting JSON Response
		
		//Getting response as JSONString
		String JSONStringRes = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		//Converting to JSONObject
		JSONObject responseJSON = new JSONObject(JSONStringRes);
		
		System.out.println("JSON PayLoad is "+responseJSON);
		
		
		// JSON Object Validation
		String per_page_value = GetCall_JSONResponse.getJSONElements(responseJSON, "per_page");
		System.out.println("per_page Value is " +per_page_value);
		Assert.assertEquals(Integer.parseInt(per_page_value), 6, "value not matched");
		
		
		// JSON Array Validation
		String firsName = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/first_name");
		System.out.println("First name is " +firsName);
		Assert.assertEquals(firsName, "Michael", "name not matched");
		
		String lastName = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/last_name");
		System.out.println("First name is " +lastName);
		Assert.assertEquals(lastName, "Lawson", "name not matched");
		
		String id  = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/id");
		System.out.println("ID is "+id);
		Assert.assertEquals(id, "7", "value not matched");
		
		String email_id = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/email");
		System.out.println("Email id is "+email_id);
		Assert.assertEquals(email_id, "michael.lawson@reqres.in", "email not matched");
		
		//3. Getting Header info
		
		//header info are given in array format
		Header[] header = response.getAllHeaders();
		//converting array to HashMap (key, value) format
		HashMap<String, String> allHeader = new HashMap<String, String>();
		//after conversion getting header details in HashMap variable
		for(Header h : header) {
			allHeader.put(h.getName(), h.getValue());
		}
		System.out.println("Header details are "+allHeader);

		
	}
	
	@Test
	public void testGetCall_withHeader() throws ClientProtocolException, IOException {
		get_call_responsevalidation = new Get_Call_ResponseValidation();
		
		HashMap<String, String> headmap = new HashMap<String, String>();
		headmap.put("User-Agent", "PostmanRuntime/7.25.0");
		headmap.put("Accept-Encoding", "gzip, deflate, br");
		headmap.put("Connection", "keep-alive");
		
		
		response = get_call_responsevalidation.getRequest(uri);
				
		
		//1 Geeting Status code 
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status code is " + statusCode);
		// Status code Validation
		Assert.assertEquals(statusCode, reponse_code_200, "Code not matched");
		
		
		//2. Getting JSON Response
		String JSONStringRes = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(JSONStringRes);
		System.out.println("JSON PayLoad is "+responseJSON);
		
		
		// JSON Object Validation
		String per_page_value = GetCall_JSONResponse.getJSONElements(responseJSON, "per_page");
		System.out.println("per_page Value is " +per_page_value);
		Assert.assertEquals(Integer.parseInt(per_page_value), 6, "value not matched");
		
		
		// JSON Array Validation
		String firsName = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/first_name");
		System.out.println("First name is " +firsName);
		Assert.assertEquals(firsName, "Michael", "name not matched");
		
		String lastName = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/last_name");
		System.out.println("First name is " +lastName);
		Assert.assertEquals(lastName, "Lawson", "name not matched");
		
		String id  = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/id");
		System.out.println("ID is "+id);
		Assert.assertEquals(id, "7", "value not matched");
		
		String email_id = GetCall_JSONResponse.getJSONElements(responseJSON, "data[0]/email");
		System.out.println("Email id is "+email_id);
		Assert.assertEquals(email_id, "michael.lawson@reqres.in", "email not matched");
		
		//3. Getting Header info
		
		//header info are given in array format
		Header[] header = response.getAllHeaders();
		//converting array to HashMap (key, value) format
		HashMap<String, String> allHeader = new HashMap<String, String>();
		//after conversion getting header details in HashMap variable
		for(Header h : header) {
			allHeader.put(h.getName(), h.getValue());
		}
		System.out.println("Header details are "+allHeader);

		
	}
	
}
