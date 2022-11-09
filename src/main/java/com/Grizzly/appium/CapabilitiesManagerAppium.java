
package com.Grizzly.appium;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.Grizzly.constants.Constants;
import com.Grizzly.utils.PropertyManager;
import com.Grizzly.utils.TestUtils;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CapabilitiesManagerAppium {
	TestUtils utils = new TestUtils();

	public DesiredCapabilities getCaps() throws IOException {
		GlobalParamsAppium params = new GlobalParamsAppium();
		Properties props = new PropertyManager().getProps();

		try {
			utils.log().info("getting capabilities");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
			caps.setCapability(MobileCapabilityType.UDID, params.getUDID());
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());
			caps.setCapability("unicodeKeyboard", "true");
			caps.setCapability("resetKeyboard", "true");

			switch (params.getPlatformName()) {
			case "Android":
				caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("android.automationName"));
				if (!Constants.isAWS) {

					caps.setCapability("platformName", "Android");
					caps.setCapability("deviceName", "Pixel 3 XL API 28");

					caps.setCapability("systemPort", params.getSystemPort());
					caps.setCapability("chromeDriverPort", params.getChromeDriverPort());
					if (params.getEmulator().equalsIgnoreCase("true")) {
						caps.setCapability("avd", params.getDeviceName());
						caps.setCapability("avdLaunchTimeout", 120000);
					}

					/*
					 * String androidAppUrl = System.getProperty("user.dir") + File.separator +
					 * "src" + File.separator + "test" + File.separator + "resources" +
					 * File.separator + "apps" + File.separator + "ApiDemos-debug.apk";
					 * utils.log().info("appUrl is" + androidAppUrl);
					 */

					String androidAppUrl = "C:\\Users\\rthakur\\Downloads\\ECI-Mobile-QA-Automation-Framework\\Mobile-automation\\app\\GrizzlyPawMobile.apk";
					caps.setCapability("app", androidAppUrl);
				}

				break;

			case "iOS":
				caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOS.automationName"));
				if (!Constants.isAWS) {

					String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
							+ File.separator + "resources" + File.separator + "apps" + File.separator
							+ "SwagLabsMobileApp.app";
					utils.log().info("appUrl is" + iOSAppUrl);
					caps.setCapability("bundleId", props.getProperty("iOS.bundleId"));
					caps.setCapability("wdaLocalPort", params.getWdaLocalPort());
					caps.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
					caps.setCapability("app", iOSAppUrl);
				}
				break;
			}
			return caps;
		} catch (Exception e) {
			e.printStackTrace();
			utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
			throw e;
		}
	}
}
