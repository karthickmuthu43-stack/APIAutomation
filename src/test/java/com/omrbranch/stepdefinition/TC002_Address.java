package com.omrbranch.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.addaddress.Addaddress_Input_Pojo;
import com.omrbranch.pojo.addaddress.Addaddress_Output_Pojo;
import com.omrbranch.pojo.cityList.CityList_Input_Pojo;
import com.omrbranch.pojo.cityList.CityList_Output_Pojo;
import com.omrbranch.pojo.deleteAddress.DeleteAddress_Input_Pojo;
import com.omrbranch.pojo.getUserAddress.GetUserAddress_Output_Pojo;
import com.omrbranch.pojo.stateList.StateList_Output_Pojo;
import com.omrbranch.pojo.updateaddress.Updateaddress_Input_Pojo;
import com.omrbranch.pojo.updateaddress.Updateaddress_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC002_Address extends BaseClass {
	Response response;
	int addID;

	//*********************************Get State************************
	
	@Given("User adds headers for StateList")
	public void user_adds_headers_for_state_list() {

		initRestAssured();
		addHeader("accept", "application/json");
	}

	@When("User sends {string} request to StateList endpoint")
	public void user_sends_request_to_state_list_endpoint(String string) {

		response = sendRequest("GET", "https://omrbranch.com/api/stateList");
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		//System.out.println(getResponseBody(response));

	}

	@Then("User should verify the stateList response message matches {string} and save the state id")
	public void user_should_verify_the_state_list_response_message_matches_and_save_the_state_id(String state) {

		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<com.omrbranch.pojo.stateList.Datum> stateList = stateList_Output_Pojo.getData();

		for (com.omrbranch.pojo.stateList.Datum datum : stateList) {
			String name = datum.getName();
			if (name.equals(state)) {
				int stateID = datum.getId();
				GlobalData.getGlobalDataInstance().setStateID(stateID);
				
				System.out.println("State name of " + name + "  is " + stateID);
				break;
			}

		}

		
	}
	
	//************************Get City***************************

	@Given("User adds headers for CityList")
	public void user_adds_headers_for_city_list() {

		initRestAssured();


		List<Header> lstHeader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

	}

	@When("User adds request body with state id for city list")
	public void user_adds_request_body_with_state_id_for_city_list() {

		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(String.valueOf(GlobalData.getGlobalDataInstance().getStateID()));

		addPayload(cityList_Input_Pojo);

	}

	@When("User sends {string} request to CityList endpoint")
	public void user_sends_request_to_city_list_endpoint(String string) {

		response = sendRequest(string, "https://omrbranch.com/api/cityList");
		int statusCode = getStatusCode(response);

		System.out.println(statusCode);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		//System.out.println(getResponseBody(response));

	}

	@Then("User should verify the cityList response message matches {string} and save the city id")
	public void user_should_verify_the_city_list_response_message_matches_and_save_the_city_id(String City) {

		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);

		ArrayList<com.omrbranch.pojo.cityList.Datum> cityList = cityList_Output_Pojo.getData();

		for (com.omrbranch.pojo.cityList.Datum datum : cityList) {
			String name = datum.getName();
			if (name.equals(City)) {
				int CityID = datum.getId();

				System.out.println("City name of " + name + "  is " + CityID);
			
			} 

		}

	}
	//******************************Add Address*******************************

	@Given("User adds headers and bearer authorization for accessing address endpoints")
	public void user_adds_headers_and_bearer_authorization_for_accessing_address_endpoints() {
		
	
		
		initRestAssured();
		List<Header> lstHeader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		

	}

	@When("User adds request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_adds_request_body_for_add_new_address(String string1, String string2 , String string3,
			String string4, String string5, String string6, String string7, String string8, String string9,
			String string10) {
		
		
		Addaddress_Input_Pojo addaddress_Input_Pojo = new Addaddress_Input_Pojo(string1,string2,string3,string4,string5,string6,string7,string8,string9,string10);
		addPayload(addaddress_Input_Pojo);
		

	}

	@Then("User sends {string} request to addUserAddress endpoint")
	public void user_sends_request_to_add_user_address_endpoint(String string) {

		
		 response = sendRequest("POST", "https://omrbranch.com/api/addUserAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		System.out.println(getResponseBody(response));
	}

	@Then("User should verify the addUserAddress response message matches {string} and save the address id")
	public void user_should_verify_the_add_user_address_response_message_matches_and_save_the_address_id(
			String getMessage) {
		
		Addaddress_Output_Pojo addaddress_Output_Pojo=response.as(Addaddress_Output_Pojo.class);
		addID=addaddress_Output_Pojo.getAddress_id();
		String Message=addaddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify Message", getMessage, Message);
		
		System.out.println(addID);
		
		GlobalData.getGlobalDataInstance().setAddID(addID);

	}
	
	// *****************************Update address *************************
	

	
	@When("User adds request body to update address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_adds_request_body_to_update_address(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10) {
	    
		System.out.println(String.valueOf(GlobalData.getGlobalDataInstance().getAddID()));
		Updateaddress_Input_Pojo updateaddress_Input_Pojo=new Updateaddress_Input_Pojo(String.valueOf(GlobalData.getGlobalDataInstance().getAddID()),string,string2,string3,string4,string5,string6,string7,string8,string9,string10);
	    addPayload(updateaddress_Input_Pojo);
	}
	
	
	@When("User sends {string} request to updateUserAddress endpoint")
	public void user_sends_request_to_update_user_address_endpoint(String string) {
		
		response = sendRequest("PUT", "https://omrbranch.com/api/updateUserAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		System.out.println(getResponseBody(response));
	
	}
	@Then("User should verify the updateUserAddress response message matches {string}")
	public void user_should_verify_the_update_user_address_response_message_matches(String getmessage) {
		
		Updateaddress_Output_Pojo updateaddress_Output_Pojo=response.as(Updateaddress_Output_Pojo.class);
		String message1=updateaddress_Output_Pojo.getMessage();
		
		Assert.assertEquals("Verify Message", getmessage, message1);
		
		
	    
	}

	
	@Given("User adds headers and bearer authorization for accessing get address endpoints")
	public void user_adds_headers_and_bearer_authorization_for_accessing_get_address_endpoints() {
		
		
		initRestAssured();
		List<Header> lstHeader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		
		Header h2 = new Header("Authorization", "Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
		lstHeader.add(h1);
		lstHeader.add(h2);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		
	    
	}
	@When("User sends {string} request to GetUserAddress endpoint")
	public void user_sends_request_to_get_user_address_endpoint(String string) {
		
		 response = sendRequest("GET", "https://omrbranch.com/api/getUserAddress");
		
		
	}
	@Then("User should verify the GetUserAddress response message matches {string}")
	public void user_should_verify_the_get_user_address_response_message_matches(String getmsg) {
		
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo=response.as(GetUserAddress_Output_Pojo.class);
		String msg=getUserAddress_Output_Pojo.getMessage();
		System.out.println(msg);
		Assert.assertEquals("Verify Message", getmsg, msg);
	
	}
	
	@When("User adds request body with address id")
	public void user_adds_request_body_with_address_id() {
		
		initRestAssured();
		List<Header> lstHeader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		
		Header h3 = new Header("Authorization", "Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
		lstHeader.add(h1);
		lstHeader.add(h2);
		lstHeader.add(h3);
		Headers headers = new Headers(lstHeader);
		addHeaders(headers);
		
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo =new DeleteAddress_Input_Pojo(String.valueOf(GlobalData.getGlobalDataInstance().getAddID()));
		addPayload(deleteAddress_Input_Pojo);
		
	   
	}
	@When("User sends {string} request to DeleteAddress endpoint")
	public void user_sends_request_to_delete_address_endpoint(String string) {
		
		 response = sendRequest("DELETE", "https://omrbranch.com/api/deleteAddress");
		
	    	
	}
	@Then("User should verify the DeleteAddress response message matches {string}")
	public void user_should_verify_the_delete_address_response_message_matches(String string) {
		
		
	    
	}

	

}
