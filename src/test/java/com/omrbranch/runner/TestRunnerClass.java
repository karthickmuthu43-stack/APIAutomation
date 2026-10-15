package com.omrbranch.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.omrbranch.report.Reporting;
import com.omrbranch.utility.BaseClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@Login or @StateListv or @CityListv or @AddAddress or  @Cart or @product or @Variant or @Addtocart or @Savecart or @Setaddress or @CreateOrder", dryRun = false, plugin = { "pretty",
		"json:target/output.json" }, features = "src/test/resources", glue = "com.omrbranch.stepdefinition")
public class TestRunnerClass extends BaseClass {

	@AfterClass
	public static void afterClass() {
		Reporting.generateJvmReport(getProjectPath() + "\\target\\output.json");
	}

}
