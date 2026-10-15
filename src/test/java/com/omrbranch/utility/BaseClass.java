package com.omrbranch.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	RequestSpecification reqSpec;
	Response response;

	public static String getPropertyFilevalue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectPath() + "\\src\\test\\resources\\config\\Config.properties"));
		Object object = properties.get(key);
		String value = (String) object;
		System.out.println(value);
		return value;
	}

	public static String getProjectPath() {

		return System.getProperty("user.dir");
	}

	public void initRestAssured() {
		reqSpec = RestAssured.given();
	}

	public void addHeader(String key, String value) {
		reqSpec = reqSpec.header(key, value);
	}

	public void addHeaders(Headers headers) {
		reqSpec = reqSpec.headers(headers);
	}

	public void addBasicAuth(String userId, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(userId, password);

	}

	public void addPayload(Object body) {
		reqSpec = reqSpec.body(body);
	}

	public void addPayload(String body) {
		reqSpec = reqSpec.body(body);
	}

	public Response sendRequest(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqSpec.get(endpoint);
			break;
		case "POST":
			response = reqSpec.post(endpoint);
			break;
		case "PUT":
			response = reqSpec.put(endpoint);
			break;
		case "PATCH":
			response = reqSpec.patch(endpoint);
			break;
		case "DELETE":
			response = reqSpec.delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String getResponseBody(Response response) {
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		return asPrettyString;
	}

}
