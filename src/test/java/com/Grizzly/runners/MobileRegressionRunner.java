package com.Grizzly.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:artifacts/cucumber-reports/Pixel 3 XL API 28/cucumber", "summary",
		"de.monochromata.cucumber.report.PrettyReports:artifacts/cucumber-reports/Pixel_4a_API_30/cucumber-html-reports" }, features = {
				"src/test/resources/features/GrizzlyAndroidApp.feature" }, glue = {
						"com.Grizzly.stepdef" }, dryRun = true, monochrome = true, strict = true
// ,tags = {"@test"}
)

public class MobileRegressionRunner extends RunnerBase {

}
