package com.omrbranch.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.omrbranch.utility.BaseClass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends BaseClass {

	public static void generateJvmReport(String jsonFile) {

		// Project root path
		String projectPath = getProjectPath();

		// Report output directory
		File reportOutputDirectory = new File(projectPath + "\\target");

		// Configuration
		Configuration configuration = new Configuration(reportOutputDirectory, "OMR Branch API Automation Project");

		// ===============================
		// CLASSIFICATIONS (API RELATED)
		// ===============================
		configuration.addClassifications("Project", "OMR Branch API");
		configuration.addClassifications("Module", "Booking / Login API");
		configuration.addClassifications("Environment", "QA");
		configuration.addClassifications("API Type", "REST");
		configuration.addClassifications("Framework", "RestAssured + Cucumber");
		configuration.addClassifications("Language", "Java");
		configuration.addClassifications("Build Tool", "Maven");

		// JSON report path
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add(jsonFile);

		// Generate report
		ReportBuilder builder = new ReportBuilder(jsonFiles, configuration);
		builder.generateReports();
	}

}
