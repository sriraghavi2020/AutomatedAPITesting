package APICall_Package;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import BasePackage.BaseClass;

public class Get_Call extends BaseClass{
	
	
public void getRequest(String uri) throws ClientProtocolException, IOException {
		
		// 1. link
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 2. getting the uri
		HttpGet httpget = new HttpGet(uri);
		
        //3.Getting the response//   or hitting the url
		CloseableHttpResponse response = httpClient.execute(httpget);
		
        //3a. Getting the status code
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status code is " + statusCode);
		
		//3b. Getting JSON Response
		String JSONStringRes = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject JSONResponse = new JSONObject(JSONStringRes);
		System.out.println("JSON PayLoad is "+JSONResponse);
		
		//3c. Getting Header info
		
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
