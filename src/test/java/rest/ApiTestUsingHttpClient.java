package rest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class ApiTestUsingHttpClient {

	@Test
	public void test() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("https://demoqa.com/utilities/weather/city/Hyderabad");

		// Send the request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		// Convert the result as a String to a JSON object
		String result = EntityUtils.toString(httpResponse.getEntity());

		JSONObject jo = new JSONObject(result);

		System.out.println(result);
		System.out.println(jo.length());
		System.out.println(jo.get("City"));
		
		// Verify the response type
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
	}

	@Test
	public void test1() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/users");
		// Send the request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String result = EntityUtils.toString(httpResponse.getEntity());
		String header = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
		System.out.println(result);
		// Verify the response type
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
		Assert.assertEquals(header,"application/json");

	}

}
