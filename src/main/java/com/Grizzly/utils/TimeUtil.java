package com.Grizzly.utils;

public final class TimeUtil {

	private TimeUtil() {
	}

	public static int convertSecsToMillisWithRange(int waitTime, int begin, int end) {
		if (waitTime < begin) {
			waitTime = begin;
		}
		if (waitTime > end) {
			waitTime = end;
		}
		return waitTime * 1000;

	}

	public static void timeOuts(long wait) throws InterruptedException {
		Thread.sleep(wait);
	}

}
