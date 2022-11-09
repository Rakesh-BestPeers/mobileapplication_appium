package com.Grizzly.constants;

import java.io.File;

public interface PathConstants {

	// artifacts
	String screenshots = "artifacts" + File.separator + "Screenshots";
	String reports = "artifacts" + File.separator + "html";
	String video = "artifacts" + File.separator + "Videos";
	String severlogs = "artifacts" + File.separator + "Severlogs";
	String logs = "artifacts" + File.separator + "logs";
	// String cucumberReports = "artifacts" + File.separator + "html"+
	// File.separator +"Extent";

	// testdata
	String xmlPath = "strings/strings.xml";
	String jsonPath = "data/loginUsers.json";
	String toastMsg = "artifacts" + File.separator + "verifyScreenShots";
	// String testData = "./src/test/resources/data/DataSheet.xlsx";

	// changes done 
	String downloadImgPath = System.getProperty("user.dir") + "//src//test//resources//imageDownloaded//elips.svg";
	String storedImgPath = System.getProperty("user.dir") + "//src//test//resources//imageStored//";
	String testData = "./src/test/resources/data/DataSheet.xlsx";
}
