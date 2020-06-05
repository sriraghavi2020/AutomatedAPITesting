import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import APICall_Package.Get_Call;
import BasePackage.BaseClass;

public class Test_GetCall extends BaseClass {

	String url;
	String APIurl;
	String uri;
	Get_Call get_call;

	@BeforeMethod
	public void setUp() {

//		readPropsFile("url");
//		readPropsFile("APIurl");

		url = readPropsFile("url");
		APIurl = readPropsFile("APIurl");

		uri = url + APIurl;
	}

	@Test
	public void TestGetCall() throws ClientProtocolException, IOException {

		get_call = new Get_Call();
		get_call.getRequest(uri);

	}

}
