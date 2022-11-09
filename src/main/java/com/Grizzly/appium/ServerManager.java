package com.Grizzly.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.HashMap;

import com.Grizzly.constants.PathConstants;
import com.Grizzly.utils.TestUtils;

public class ServerManager {
	private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
	TestUtils utils = new TestUtils();

	public AppiumDriverLocalService getServer() {
		return server.get();
	}

	public void startServer() {
		utils.log().info("starting appium server");
		AppiumDriverLocalService server = WindowsGetAppiumService();
		server.start();
		if (server == null || !server.isRunning()) {
			utils.log().fatal("Appium server not started. ABORT!!!");
			throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
		}
//        server.clearOutPutStreams();
		this.server.set(server);
		utils.log().info("Appium server started");
	}

	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}

	public AppiumDriverLocalService WindowsGetAppiumService() {
		GlobalParamsAppium params = new GlobalParamsAppium();
		return AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingAnyFreePort().withArgument(GeneralServerFlag.SESSION_OVERRIDE)
						.withLogFile(new File(PathConstants.severlogs + File.separator + params.getPlatformName() + "_"
								+ params.getDeviceName() + File.separator + "Server.log")));
	}

	public AppiumDriverLocalService MacGetAppiumService() {
		GlobalParamsAppium params = new GlobalParamsAppium();
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH",
				"/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/bin:/Users/Om/Library/Android/sdk/tools:/Users/Om/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
						+ System.getenv("PATH"));
		environment.put("ANDROID_HOME", "C:/Users/rthakur/AppData/Local/Android/Sdk");
		environment.put("JAVA_HOME", "C:/Program Files/Java/jdk1.8.0_311");
		return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("/usr/local/bin/node"))
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).usingAnyFreePort()
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withEnvironment(environment)
				.withLogFile(new File(PathConstants.severlogs + File.separator + params.getPlatformName() + "_"
						+ params.getDeviceName() + File.separator + "Server.log")));
	}
}
