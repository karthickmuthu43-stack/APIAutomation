package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.globaldata.GlobalData;

import io.cucumber.java.en.Then;

public class CommonStep {

	@Then("User should verify the status code is {int}")
	public void user_should_verify_the_status_code_is(int expStatusCode) {
		int actStatusCode = GlobalData.getGlobalDataInstance().getStatusCode();
		Assert.assertEquals("Verify status Code", expStatusCode, actStatusCode);
	}

}
