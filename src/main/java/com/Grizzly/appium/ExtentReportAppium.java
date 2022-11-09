package com.Grizzly.appium;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.Grizzly.constants.PathConstants;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportAppium {
	static ExtentReports extent;
	final static String filePath = PathConstants.reports;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();

	public synchronized static ExtentReports getReporter() {
		GlobalParamsAppium params = new GlobalParamsAppium();
		if (extent == null) {
			ExtentHtmlReporter html = new ExtentHtmlReporter(
					filePath + File.separator + params.getPlatformName() + File.separator + params.getDeviceName()
							+ File.separator + "Grizzly_Mobile_Extent_Report_" + params.getDateTime() + ".html");

			extent = new ExtentReports();

			html.config().setDocumentTitle("Grizzly PAW Mobile");
			html.config().setReportName("Grizzly Mobile Automation Report");
			html.config().setTheme(Theme.DARK);
			html.setAnalysisStrategy(AnalysisStrategy.TEST);

			extent.setSystemInfo("Organization : ", "Eze_Castle_Integration");
			extent.setSystemInfo("Application : ", "GrizzlyPAW");
			extent.setSystemInfo("Automation Tester : ", "Rakesh Singh Thakur");
			extent.attachReporter(html);
		}

		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = getReporter().createTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
