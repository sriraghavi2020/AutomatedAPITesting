package APICall_Package;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Get_Call_ResponseValidation {
	
	//GetCall without header

	public CloseableHttpResponse getRequest(String uri) throws ClientProtocolException, IOException {
		CloseableHttpClient link = HttpClients.createDefault();
		HttpGet getURL = new HttpGet(uri);
		CloseableHttpResponse response = link.execute(getURL);
		return response;
	}
	
	
	
	//GetCall with header
	public CloseableHttpResponse getRequest(String uri, HashMap<String, String> headmap) throws ClientProtocolException, IOException {
		CloseableHttpClient link = HttpClients.createDefault();
		HttpGet getURL = new HttpGet(uri);
		for(Map.Entry<String, String> entry : headmap.entrySet()) {
			getURL.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse response = link.execute(getURL);
		return response;
	}
	
	
}
