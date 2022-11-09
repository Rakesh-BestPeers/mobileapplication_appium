package com.Grizzly.runners;

import io.cucumber.testng.FeatureWrapper;


import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.*;

import com.Grizzly.appium.DriverManagerAppium;
import com.Grizzly.appium.GlobalParamsAppium;
import com.Grizzly.appium.ServerManager;
import com.Grizzly.constants.Constants;
import com.Grizzly.constants.PathConstants;
import com.Grizzly.utils.TestUtils;

import java.io.File;


@SuppressWarnings("unused")
public class RunnerBase {
	private static final ThreadLocal<TestNGCucumberRunner> testNGCucumberRunner = new ThreadLocal<>();

	public static TestNGCucumberRunner getRunner(){
		return testNGCucumberRunner.get();
	}

	private static void setRunner(TestNGCucumberRunner testNGCucumberRunner1){
		testNGCucumberRunner.set(testNGCucumberRunner1);
	}

	@Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort","chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort"})
	@BeforeClass(alwaysRun = true)
	public void setUpClass(String emulator, String platformName, String udid, String deviceName, @Optional("Android") String systemPort,
			@Optional("Android") String chromeDriverPort,
			@Optional("iOS") String wdaLocalPort,
			@Optional("iOS") String webkitDebugProxyPort) throws Exception {

		ThreadContext.put("ROUTINGKEY", PathConstants.logs + File.separator + platformName + "_" + deviceName);

		GlobalParamsAppium params = new GlobalParamsAppium();
		TestUtils utils = new TestUtils();
		params.setDateTime(utils.dateTime());
		params.setPlatformName(platformName);
		params.setUDID(udid);
		params.setDeviceName(deviceName);

		switch(platformName){
		case "Android":
			params.setSystemPort(systemPort);
			params.setChromeDriverPort(chromeDriverPort);
			params.setEmulator(emulator);
			break;
		case "iOS":
			params.setWdaLocalPort(wdaLocalPort);
			params.setWebkitDebugProxyPort(webkitDebugProxyPort);
			break;
		}

	/*	if(Constants.isServer) {
			new ServerManager().startServer();
		}*/

		new DriverManagerAppium().initializeDriver();

		setRunner(new TestNGCucumberRunner(this.getClass()));
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
		getRunner().runScenario(pickle.getPickle());
	}

	@DataProvider
	public Object[][] scenarios() {
		return getRunner().provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		DriverManagerAppium driverManager = new DriverManagerAppium();
		if(driverManager.getDriver() != null){
			driverManager.getDriver().quit();
			driverManager.setDriver(null);
		}
		ServerManager serverManager = new ServerManager();
		if(serverManager.getServer() != null){
			serverManager.getServer().stop();
		}
		if(testNGCucumberRunner != null){
			getRunner().finish();
		}
	}
}
