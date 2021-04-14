package soap;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class SoapExample {

	@Test
	public void getConversionRate() throws Exception {
		FileInputStream fileInputStream = new FileInputStream(new File("SoapRequestFile.xml"));
		RestAssured.baseURI = "http://currencyconverter.kowabunga.net";

		Response response = given().header("Content-Type", "text/xml").and()
				.body(IOUtils.toString(fileInputStream, "UTF-8")).when().post("/converter.asmx").then().statusCode(200)
				.and().log().all().extract().response();

		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		String rate = jsXpath.getString("GetConversionRateResult");
		System.out.println("rate returned is: " + rate);
	}

	@Test
	public void getCurrencies() throws Exception {
		RestAssured.baseURI = "http://currencyconverter.kowabunga.net";
		Response response = given().header("Content-Type", "text/xml").and().when()
				.post("/converter.asmx/GetCurrencies").then().statusCode(200).and().log().all().extract().response();

		System.out.println("response returned is: " + response.asString());
	}

	@Test
	public void getConversionAmount() throws Exception {
		FileInputStream fileInputStream = new FileInputStream(new File("SoapRequestFile.xml"));
		RestAssured.baseURI = "http://currencyconverter.kowabunga.net";

		Response response = given().header("Content-Type", "text/xml").and()
				.body(IOUtils.toString(fileInputStream, "UTF-8")).when().post("/converter.asmx").then().statusCode(200)
				.and().log().all().extract().response();

		System.out.println("conversion amount is: " + response.asString());
	}

}
