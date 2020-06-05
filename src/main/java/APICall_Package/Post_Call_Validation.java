package APICall_Package;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Post_Call_Validation {

	public CloseableHttpResponse postCallWithHeader(String uri, String payloadAsString, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {

		// 1.Link
		CloseableHttpClient HttpLink = HttpClients.createDefault();

		// 2.Http Post Request
		HttpPost postURL = new HttpPost(uri);
		
		//payloadAsString
		postURL.setEntity(new StringEntity(payloadAsString));

		// Header information
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			postURL.addHeader(entry.getKey(), entry.getValue());
		}

		// 3. Hit the post request
		CloseableHttpResponse response = HttpLink.execute(postURL);
		return response;
	}

}
