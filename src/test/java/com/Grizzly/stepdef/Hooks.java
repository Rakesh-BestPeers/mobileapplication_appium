package com.Grizzly.stepdef;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.Grizzly.appium.DriverManagerAppium;
import com.Grizzly.appium.ExtentReportAppium;
import com.Grizzly.appium.GlobalParamsAppium;
import com.Grizzly.appium.VideoManagerAppium;
import com.Grizzly.constants.PathConstants;
import com.Grizzly.utils.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Hooks {

	public AppiumDriver<?> driver;
	GlobalParamsAppium params = new GlobalParamsAppium();

	@Before
	public void initialize(Scenario scenario) throws Exception {
		ExtentReportAppium.startTest(scenario.getName(), scenario.getName())
				.assignCategory(params.getPlatformName() + "_" + params.getDeviceName())
				.assignAuthor("Rakesh Singh Thakur");

		new VideoManagerAppium().startRecording();
	}

	@Before("@appium")
	public void setUpAppium() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3 XL API 28");
		cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "//app//GrizzlyPawMobile.apk");
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);

	}

	@After
	public void quit(Scenario scenario) throws IOException {

		File file = new DriverManagerAppium().getDriver().getScreenshotAs(OutputType.FILE);

		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String imagePath = PathConstants.screenshots + File.separator + params.getPlatformName() + "_"
				+ params.getDeviceName() + File.separator + params.getDateTime() + File.separator + scenario.getName()
				+ ".png";

		String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;

		if (scenario.isFailed()) {

			try {
				FileUtils.copyFile(file, new File(imagePath));
				Reporter.log("This is the sample screenshot");
				Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath
						+ "' height='400' width='400'/> </a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				scenario.embed(encoded, "image/png", scenario.getName());
				/*
				 * ExtentReport.getTest().fail("Test Failed",
				 * MediaEntityBuilder.createScreenCaptureFromPath(completeImagePath).build());
				 */
				ExtentReportAppium.getTest().fail("Test Failed", MediaEntityBuilder
						.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
			ExtentReportAppium.getTest().fail(scenario.getName());
		}

		ExtentReportAppium.getTest().info(scenario.getName(), MediaEntityBuilder
				.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
		new VideoManagerAppium().stopRecording(scenario.getName());

		ExtentReportAppium.getReporter().flush();
	}
}
