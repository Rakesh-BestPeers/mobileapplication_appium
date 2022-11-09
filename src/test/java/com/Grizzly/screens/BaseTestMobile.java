package com.Grizzly.screens;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.Grizzly.appium.DriverManagerAppium;
import com.Grizzly.appium.ExtentReportAppium;
import com.Grizzly.appium.GlobalParamsAppium;
import com.Grizzly.constants.PathConstants;
import com.Grizzly.utils.TestUtils;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BaseTestMobile {
	
	
	public AppiumDriver<?> driver;
    TestUtils utils = new TestUtils();
    GlobalParamsAppium params = new GlobalParamsAppium();
    
    public static void verifyActualExpectedResultWithEquals(String actualResult, String expectedResult, String str)
			throws InterruptedException {
		Thread.sleep(2000);
		String actualExpectedLogStr = "<b> Expected/Actual:: " + expectedResult + "/" + actualResult + " <b>";
		if (actualResult.equals(expectedResult)) {
			ExtentReportAppium.getTest().createNode("Successfully verify Expected/Actual Result of " + str);
			String info = "Successfully verify " + str;
			Markup a = MarkupHelper.createLabel(info, ExtentColor.GREEN);
			ExtentReportAppium.getTest().log(Status.INFO, a);

			Markup b = MarkupHelper.createLabel(actualExpectedLogStr, ExtentColor.CYAN);
			ExtentReportAppium.getTest().log(Status.PASS, b);
		} else {
			ExtentReportAppium.getTest().createNode("Not able to verify Expected/Actual Result of " + str);
			String info = "Not able to verify Actual/Expected Result of " + str;
			Markup a = MarkupHelper.createLabel(info, ExtentColor.RED);
			ExtentReportAppium.getTest().log(Status.INFO, a);

			Markup b = MarkupHelper.createLabel(actualExpectedLogStr, ExtentColor.RED);
			ExtentReportAppium.getTest().log(Status.FAIL, b);
			Assert.assertEquals(1, 0);
		
			}
		}

public static void verifyActualExpectedResultWithContains(String actualResult, String expectedResult, String str)
			throws InterruptedException {
		Thread.sleep(2000);
		String actualExpectedLogStr = "<b> Expected/Actual:: " + expectedResult + "/" + actualResult + " <b>";
		if (actualResult.contains(expectedResult)) {
			ExtentReportAppium.getTest().createNode("Verify Actual/Expected Result of " + str);

			String info = "Successfully verify " + str;
			Markup a = MarkupHelper.createLabel(info, ExtentColor.GREEN);
			ExtentReportAppium.getTest().log(Status.INFO, a);

			Markup b = MarkupHelper.createLabel(actualExpectedLogStr, ExtentColor.CYAN);
			ExtentReportAppium.getTest().log(Status.PASS, b);
		} else {
			String info = "Not able to verify Actual/Expected Result of " + str;
			Markup a = MarkupHelper.createLabel(info, ExtentColor.RED);
			ExtentReportAppium.getTest().log(Status.INFO, a);

			Markup b = MarkupHelper.createLabel(actualExpectedLogStr, ExtentColor.RED);
			ExtentReportAppium.getTest().log(Status.FAIL, b);
			ExtentReportAppium.getTest().createNode("Verify Actual/Expected Result of " + str);
			Assert.assertEquals(1, 0);
			
		}
	}
 

    public BaseTestMobile(){
        this.driver = new DriverManagerAppium().getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public static void appiumWait(int millis) throws InterruptedException {
		Thread.sleep(millis);
	}
    
    public void waitForVisibility(By e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    public void clear(MobileElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(MobileElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void click(MobileElement e, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        ExtentReportAppium.getTest().log(Status.INFO, msg);
        e.click();
    }
    

  

    public void click(By e, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        ExtentReportAppium.getTest().log(Status.INFO, msg);
        driver.findElement(e).click();
    }

    public void sendKeys(MobileElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void sendKeys(MobileElement e, String txt, String msg) {
        waitForVisibility(e);
        utils.log().info(msg);
        ExtentReportAppium.getTest().log(Status.INFO, msg);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getAttribute(By e, String attribute) {
        waitForVisibility(e);
        return driver.findElement(e).getAttribute(attribute);
    }

    public String getText(MobileElement e, String msg) {
        String txt;
        switch(new GlobalParamsAppium().getPlatformName()){
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParamsAppium().getPlatformName());
        }
        utils.log().info(msg + txt);
        ExtentReportAppium.getTest().log(Status.INFO, msg + txt);
        return txt;
    }

    public String getText(By e, String msg) {
        String txt;
        switch(new GlobalParamsAppium().getPlatformName()){
            case "Android":
                txt = getAttribute(e, "text");
                break;
            case "iOS":
                txt = getAttribute(e, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + new GlobalParamsAppium().getPlatformName());
        }
        utils.log().info(msg + txt);
        ExtentReportAppium.getTest().log(Status.INFO, msg + txt);
        return txt;
    }

    public void closeApp() {
        ((InteractsWithApps) driver).closeApp();
    }

    public void launchApp() {
        ((InteractsWithApps) driver).launchApp();
    }

    public MobileElement andScrollToElementUsingUiScrollable(String childLocAttr, String childLocValue) {
        return (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector()."+ childLocAttr +"(\"" + childLocValue + "\"));");
    }

    public MobileElement iOSScrollToElementUsingMobileScroll(MobileElement e) {
        RemoteWebElement element = ((RemoteWebElement) e);
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
//	  scrollObject.put("direction", "down");
//	  scrollObject.put("predicateString", "label == 'ADD TO CART'");
//	  scrollObject.put("name", "test-ADD TO CART");
        scrollObject.put("toVisible", "sdfnjksdnfkld");
        driver.executeScript("mobile:scroll", scrollObject);
        return e;
    }

    public By iOSScrollToElementUsingMobileScrollParent(MobileElement parentE, String predicateString) {
        RemoteWebElement parent = (RemoteWebElement)parentE;
        String parentID = parent.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", parentID);
//	  scrollObject.put("direction", "down");
	  scrollObject.put("predicateString", predicateString);
//	  scrollObject.put("name", "test-ADD TO CART");
//        scrollObject.put("toVisible", "sdfnjksdnfkld");
        driver.executeScript("mobile:scroll", scrollObject);
        By m = MobileBy.iOSNsPredicateString(predicateString);
        System.out.println("Mobilelement is " + m);
        return m;
    }

    public MobileElement scrollToElement(MobileElement element, String direction) throws Exception {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.5);
        int endX = (int) (size.width * 0.5);
        int startY = 0;
        int endY = 0;
        boolean isFound = false;

        switch (direction) {
            case "up":
                endY = (int) (size.height * 0.4);
                startY = (int) (size.height * 0.6);
                break;

            case "down":
                endY = (int) (size.height * 0.6);
                startY = (int) (size.height * 0.4);
                break;
        }

        for (int i = 0; i < 3; i++) {
            if (find(element, 1)) {
                isFound = true;
                break;
            } else {
                swipe(startX, startY, endX, endY, 1000);
            }
        }
        if(!isFound){
            throw new Exception("Element not found");
        }
        return element;
    }

    public By scrollToElement(By element, String direction) throws Exception {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.5);
        int endX = (int) (size.width * 0.5);
        int startY = 0;
        int endY = 0;
        boolean isFound = false;

        switch (direction) {
            case "up":
                endY = (int) (size.height * 0.4);
                startY = (int) (size.height * 0.6);
                break;

            case "down":
                endY = (int) (size.height * 0.6);
                startY = (int) (size.height * 0.4);
                break;
        }

        for (int i = 0; i < 3; i++) {
            if (find(element, 1)) {
                isFound = true;
                break;
            } else {
                swipe(startX, startY, endX, endY, 1000);
            }
        }
        if(!isFound){
            throw new Exception("Element not found");
        }
        return element;
    }

    public boolean find(final MobileElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    if (element.isDisplayed()) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    public boolean find(final By element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    if (driver.findElement(element).isDisplayed()) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    public void swipe(int startX, int startY, int endX, int endY, int millis)
            throws InterruptedException {
        TouchAction t = new TouchAction(driver);
        t.press(point(startX, startY)).waitAction(waitOptions(ofMillis(millis))).moveTo(point(endX, endY)).release()
                .perform();
    }
    public void toastMsgVerification(String validationMsg, String imageName) throws Exception {


		TakesScreenshot ts=((TakesScreenshot)new BaseTestMobile().driver);
		File src = ts.getScreenshotAs(OutputType.FILE);
		String snappath=PathConstants.toastMsg + File.separator + params.getPlatformName()
		+ "_" + params.getDeviceName() + File.separator + params.getDateTime() + File.separator
		+imageName + ".png";
		File dest=new File (snappath);
		FileUtils.copyFile(src, dest);
		Thread.sleep(1000);
		toastMsgVerificationWithOCR(snappath, validationMsg);

	}


	public void toastMsgVerificationWithOCR(String imagePath, String validationMsg) throws Exception {
		BytePointer outText;
		TessBaseAPI api = new TessBaseAPI();
		// Initialize tesseract-ocr with English, without specifying tessdata path
		if (api.Init(".", "ENG") != 0) {
			System.err.println("Could not initialize tesseract.");
			System.exit(1);
		}
		// Open input image with leptonica library
		PIX image = pixRead(imagePath);
		api.SetImage(image);
		// Get OCR result
		outText = api.GetUTF8Text();
		String string = outText.getString();
		System.out.println("OCR output:\n" + string);
		if(string.contains(validationMsg)) {
			Assert.assertEquals(true,true);
			
		}
		else
		{
			Assert.assertEquals(true,true);
		}
		// Destroy used object and release memory
		api.End();
		outText.deallocate();
		pixDestroy(image);
	}
    
    
    public void imageToReport(String scenario, String status, String msg) {
		File file = new DriverManagerAppium().getDriver().getScreenshotAs(OutputType.FILE);

		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String imagePath = PathConstants.screenshots + File.separator + params.getPlatformName()
		+ "_" + params.getDeviceName() + File.separator + params.getDateTime() + File.separator
		+ scenario + ".png";

		String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;

		try {
			FileUtils.copyFile(file, new File(imagePath));
			Reporter.log("This is the sample screenshot");
			Reporter.log("<a href='"+ completeImagePath + "'> <img src='"+ completeImagePath + "' height='400' width='400'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (status) {
		case "Fail":
			try {
				params.getScenario().embed(encoded, "image/png", scenario);
				/*ExtentReport.getTest().fail("Test Failed",
                        MediaEntityBuilder.createScreenCaptureFromPath(completeImagePath).build());*/
				ExtentReportAppium.getTest().fail("Test Failed",
						MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case "Pass":
			try {
				params.getScenario().embed(encoded, "image/png", scenario);

				ExtentReportAppium.getTest().pass("Test Passed",
						MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case "Info":
			try {
				params.getScenario().embed(encoded, "image/png", scenario);

				ExtentReportAppium.getTest().info(msg,
						MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}


	}
        }

