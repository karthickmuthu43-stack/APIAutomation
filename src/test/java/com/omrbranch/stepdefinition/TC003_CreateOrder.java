package com.omrbranch.stepdefinition;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.SetAddress_Input_Pojo;
import com.omrbranch.pojo.addToCart.AddToCart_Input_Pojo;

import com.omrbranch.pojo.createorder.Createorder_Input_Pojo;
import com.omrbranch.pojo.createorder.Createorder_Output_Pojo;
import com.omrbranch.pojo.getCartItems.GetCartItems_Output_Pojo;
import com.omrbranch.pojo.getSearchResult.GetSearchResult_Input_Pojo;
import com.omrbranch.pojo.getSearchResult.GetSearchResult_Output_Pojo;
import com.omrbranch.pojo.searchproduct.Datum;
import com.omrbranch.pojo.searchproduct.Searchproduct_Input_Pojo;
import com.omrbranch.pojo.searchproduct.Searchproduct_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TC003_CreateOrder extends BaseClass {

	Response response;

	// **********************Cart***********************
	@Given("User sets bearer authorization for ClearCart endpoint")
	public void user_sets_bearer_authorization_for_clear_cart_endpoint() {

	}

	@When("User sends {string} request to the ClearCart endpoint")
	public void user_sends_request_to_the_clear_cart_endpoint(String string) {

	}

	@Then("Verify the ClearCart response message is {string}")
	public void verify_the_clear_cart_response_message_is(String string) {

	}

//**********************Search Product*********************

	@Given("User sets headers for SearchProduct")
	public void user_sets_headers_for_search_product() {

		initRestAssured();
		List<Header> lstHeader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

	}

	@When("User sets request body to search for product {string}")
	public void user_sets_request_body_to_search_for_product(String string) {

		Searchproduct_Input_Pojo searchproduct_Input_Pojo = new Searchproduct_Input_Pojo(string);
		addPayload(searchproduct_Input_Pojo);

	}

	@When("User sends {string} request to the SearchProduct endpoint")
	public void user_sends_request_to_the_search_product_endpoint(String string) {

		response = sendRequest("POST", "https://omrbranch.com/api/searchProduct");
		int statusCode = getStatusCode(response);

		System.out.println(statusCode);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		// System.out.println(getResponseBody(response));

	}

	@Then("Verify the search result includes product name {string} and Save the category ID and product ID from response")
	public void verify_the_search_result_includes_product_name_and_save_the_category_id_and_product_id_from_response(
			String string) {

		Searchproduct_Output_Pojo searchproduct_Output_Pojo = response.as(Searchproduct_Output_Pojo.class);

		ArrayList<com.omrbranch.pojo.searchproduct.Datum> list = searchproduct_Output_Pojo.getData();
		for (com.omrbranch.pojo.searchproduct.Datum datum : list) {
			String text = datum.getText();
			if (text.equals(string)) {
				int prodID = datum.getId();
				GlobalData.getGlobalDataInstance().setProdID(prodID);
				int catID = datum.getCategory_id();
				GlobalData.getGlobalDataInstance().setCatID(catID);
				System.out.println("Product ID :" + prodID);
				System.out.println("Catagory Id:" + catID);

			}
		}

	}

// ******************************Variant*******************

	@Given("User sets bearer authorization for GetSearchProductList endpoint")
	public void user_sets_bearer_authorization_for_get_search_product_list_endpoint() {

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

		System.out.println(GlobalData.getGlobalDataInstance().getLogtoken());

	}

	@When("User sets request body with saved product ID")
	public void user_sets_request_body_with_saved_product_id() {

		GetSearchResult_Input_Pojo getSearchResult_Input_Pojo = new GetSearchResult_Input_Pojo(
				String.valueOf(GlobalData.getGlobalDataInstance().getCatID()),
				String.valueOf(GlobalData.getGlobalDataInstance().getProdID()), "category");

		addPayload(getSearchResult_Input_Pojo);

	}

	@When("User sends {string} request to the GetSearchProductList endpoint")
	public void user_sends_request_to_the_get_search_product_list_endpoint(String string) {

		response = sendRequest("POST", "https://omrbranch.com/api/getSearchResult");

	}

	@Then("Verify the response includes product with specification {string} and save the variant ID")
	public void verify_the_response_includes_product_with_specification_and_save_the_variant_id(String string) {

		GetSearchResult_Output_Pojo getSearchResult_Output_Pojo = response.as(GetSearchResult_Output_Pojo.class);

		ArrayList<com.omrbranch.pojo.getSearchResult.Datum> dataList = getSearchResult_Output_Pojo.getData();

		for (com.omrbranch.pojo.getSearchResult.Datum datum : dataList) {

			ArrayList<com.omrbranch.pojo.getSearchResult.Variation> variations = datum.getVariations();

			for (com.omrbranch.pojo.getSearchResult.Variation variation : variations) {

				if ("1 kg".equals(variation.getSpecifications())) {

					System.out.println("Specification Verified : " + variation.getSpecifications());

					int variationId = variation.getId();

					System.out.println("Variation ID : " + variationId);

					GlobalData.getGlobalDataInstance().setVariationId(variationId);

					Assert.assertEquals(variation.getSpecifications(), string);

					break;
				}
			}
		}
	}

// *********************************AddtoCart*****************

	@Given("User sets bearer authorization for AddToCart endpoint")
	public void user_sets_bearer_authorization_for_add_to_cart_endpoint() {

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

	@When("User sets request body using saved variant ID")
	public void user_sets_request_body_using_saved_variant_id() {

		AddToCart_Input_Pojo addToCart_Input_Pojo = new AddToCart_Input_Pojo(
				String.valueOf(GlobalData.getGlobalDataInstance().getProdID()),
				String.valueOf(GlobalData.getGlobalDataInstance().getVariationId()), "plus");
		addPayload(addToCart_Input_Pojo);
	}

	@When("User sends {string} request to the AddToCart endpoint")
	public void user_sends_request_to_the_add_to_cart_endpoint(String string) {

		response = sendRequest("POST", "https://omrbranch.com/api/addToCart");
		int statusCode = getStatusCode(response);

		System.out.println(getResponseBody(response));

	}

	@Then("Verify the AddToCart response message is {string}")
	public void verify_the_add_to_cart_response_message_is(String string) {

	}

//*************************Get Cart************

	@Given("User sets bearer authorization for GetCart endpoint")
	public void user_sets_bearer_authorization_for_get_cart_endpoint() {

		initRestAssured();
		List<Header> lstHeader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");

		Header h2 = new Header("Authorization", "Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());

		lstHeader.add(h1);
		lstHeader.add(h2);

		Headers headers = new Headers(lstHeader);
		addHeaders(headers);

	}

	@When("User sends {string} request to the GetCart endpoint")
	public void user_sends_request_to_the_get_cart_endpoint(String string) {

		response = sendRequest("GET", "https://omrbranch.com/api/getCartItems");

	}

	@Then("Verify the GetCart response message is {string} Save the cart ID")
	public void verify_the_get_cart_response_message_is_save_the_cart_id(String string) {

		GetCartItems_Output_Pojo getCartItems_Output_Pojo = response.as(GetCartItems_Output_Pojo.class);
		ArrayList<com.omrbranch.pojo.getCartItems.Datum> cartList = getCartItems_Output_Pojo.getData();

		for (com.omrbranch.pojo.getCartItems.Datum datum : cartList) {

			String prodName = datum.getProduct_name();

			if (prodName.equals("Happilo 100% Natural Premium California Almonds | Premium Badam Giri (1 kg)")) {
				int cartID = datum.getCart_id();
				
				GlobalData.getGlobalDataInstance().setCartID(cartID);
				System.out.println("Cart ID  is " + cartID);
			}

		}

	}
	
	
	//********************************Set Address************************
	@Given("User sets bearer authorization for SetAddress endpoint")
	public void user_sets_bearer_authorization_for_set_address_endpoint() {
		

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
	@When("User sets request body with saved address ID and cart ID")
	public void user_sets_request_body_with_saved_address_id_and_cart_id() {
		
		SetAddress_Input_Pojo setAddress_Input_Pojo =new SetAddress_Input_Pojo(String.valueOf(GlobalData.getGlobalDataInstance().getAddID()), String.valueOf(GlobalData.getGlobalDataInstance().getCartID()) );
		addPayload(setAddress_Input_Pojo);
		

	}
	@When("User sends {string} request to the SetAddress endpoint")
	public void user_sends_request_to_the_set_address_endpoint(String string) {
		
		response = sendRequest("POST", "https://omrbranch.com/api/setAddress");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
	   
	}
	@Then("Verify the SetAddress response message is {string}")
	public void verify_the_set_address_response_message_is(String string) {
		
		
	    
	}
	
	//**************************Create order*************
	
	
	@Given("User sets bearer authorization for CreateOrder endpoint")
	public void user_sets_bearer_authorization_for_create_order_endpoint() {
		
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
	@When("User sets request body to create order with payment details {string}, {string}, {string}, {string}, {string}, {string}")
	public void user_sets_request_body_to_create_order_with_payment_details(String string, String string2, String string3, String string4, String string5, String string6, io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

	    Map<String, String> data = list.get(0);

	    String selectCard = data.get("Select card");
	    String cardNo = data.get("CardNo");
	    String cardName = data.get("CardName");
	    String month = data.get("Month");
	    String year = data.get("Year");
	    String cvv = data.get("CVV");
		
		Createorder_Input_Pojo Createorder_Input_Pojo=new Createorder_Input_Pojo("debit_card", cardNo, selectCard, month, year, cvv);
		addPayload(Createorder_Input_Pojo);
		


		
		
		
	}
	@When("User sends {string} request to the CreateOrder endpoint")
	public void user_sends_request_to_the_create_order_endpoint(String string) {
		
		response = sendRequest("POST", "https://omrbranch.com/api/createOrder");
		
		System.out.println(getResponseBody(response));
		


		
	}
	@Then("Verify the CreateOrder response message is {string}")
	public void verify_the_create_order_response_message_is(String getmessage) {

Createorder_Output_Pojo createorder_Output_Pojo=response.as(Createorder_Output_Pojo.class);
		
		int orderID=createorder_Output_Pojo.getData().getOrder_no();
		String msg=createorder_Output_Pojo.getMessage();
		
		System.out.println("order no is :" + orderID);
		
		Assert.assertEquals("Verify Message", getmessage, msg);
	 
	}


}
