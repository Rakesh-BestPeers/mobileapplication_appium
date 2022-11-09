package com.Grizzly.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import org.openqa.selenium.Dimension;
import com.Grizzly.constants.FrameworkConstants;


public final class SystemUtil {

	private SystemUtil() {
	}

	public static Dimension getDimension() {
		java.awt.Dimension awtDimension = Toolkit.getDefaultToolkit().getScreenSize();
		short width = (short) awtDimension.getWidth();
		short height = (short) awtDimension.getHeight();
		return new Dimension(width, height);
	}

	public static void pressKey(int key) throws AWTException, IOException {
		Robot robot = new Robot();
		robot.keyPress(key);
		robot.keyRelease(key);
		int waitTime = FrameworkConstants.keyPressWait;
		int ms = TimeUtil.convertSecsToMillisWithRange(waitTime, 1, 60);
		robot.delay(ms);
	}

	
	public static void pressKeys(int[] keys) throws AWTException, IOException {
		Robot robot = new Robot();
		for (int ctr = 0; ctr < keys.length; ctr++) {
			robot.keyPress(keys[ctr]); // Press and hold keys
		}
		int waitTime = FrameworkConstants.keyPressWait;
		int millis = TimeUtil.convertSecsToMillisWithRange(waitTime, 1, 60);
		robot.delay(millis);
		for (int ctr = keys.length - 1; ctr > -1; ctr--) {
			robot.keyRelease(keys[ctr]); // Release keys in reverse order
		}
	}

}
