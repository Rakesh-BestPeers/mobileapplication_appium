package com.Grizzly.screens;

import org.apache.commons.lang3.RandomStringUtils;

import com.Grizzly.constants.AssertionValues;
import com.Grizzly.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import ru.yandex.qatools.ashot.Screenshot;

public class Grizz_create_new_user extends BaseTestMobile {
	public String ActualText = null;
	public String ExpectedText = null;
	private int scriptWaitTime = 2000;
	TestUtils utils = new TestUtils();

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'IT’S MORE FUN TO PLAY TOGETHER!')]")
	public MobileElement Its_More_Fun_To_Play;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Customize your profile pic and user ID so your friends can find you!')]")
	public MobileElement Customize_Your_Profile;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Or you can skip now and do that later under My Profile.')]")
	public MobileElement Skip_Now_My_Profile;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Your custom username')]")
	public MobileElement User_Id_TextField;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc, 'Done Button')]")
	public MobileElement Done_Btn;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@class, 'android.widget.ImageView')]")
	public MobileElement Profile_Image_Click;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Cancel')]")
	public MobileElement Profile_Image_Cancel;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Camera')]")
	public MobileElement Profile_Image_Camera;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc, 'Select photo')]")
	public MobileElement Profile_Image_SelectPhoto;

	public Grizz_create_new_user click_profileImage_Cancel() throws InterruptedException {
		Profile_Image_Cancel.isDisplayed();

		Profile_Image_Cancel.isEnabled();
		click(Profile_Image_Cancel, "click get profile image cancel button");
		BaseTestMobile.appiumWait(scriptWaitTime);

		Profile_Image_Cancel.isSelected();

		return this;
	}

	public Grizz_create_new_user click_profileImage() throws InterruptedException {
		Profile_Image_Click.isDisplayed();

		Profile_Image_Click.isEnabled();
		click(Profile_Image_Click, "click get profile image button");
		BaseTestMobile.appiumWait(scriptWaitTime);

		Profile_Image_Click.isSelected();

		return this;
	}

	public Grizz_create_new_user click_doneButton() throws InterruptedException {
		Done_Btn.isDisplayed();

		Done_Btn.isEnabled();
		click(Done_Btn, "click get done button");
		BaseTestMobile.appiumWait(scriptWaitTime);

		Done_Btn.isSelected();

		return this;
	}

	public Grizz_create_new_user textField_userId() throws InterruptedException {
		User_Id_TextField.clear();
		BaseTestMobile.appiumWait(scriptWaitTime);
		User_Id_TextField.sendKeys("Automation--" + RandomStringUtils.randomNumeric(15));

		return this;
	}

	public Grizz_create_new_user textVerify_skipNowMyProfile() throws InterruptedException {
		Skip_Now_My_Profile.isDisplayed();

		ActualText = AssertionValues.skipNowMyProfile;
		System.err.println(ActualText);
		ExpectedText = Skip_Now_My_Profile.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify Or you can skip now and do that later under My Profile text");

		return this;
	}

	public Grizz_create_new_user textVerify_customizeYourProfile() throws InterruptedException {
		Customize_Your_Profile.isDisplayed();

		ActualText = AssertionValues.customizeYourProfile;
		System.err.println(ActualText);
		ExpectedText = Customize_Your_Profile.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify Customize your profile pic and user ID so your friends can find you text");

		return this;
	}

	public Grizz_create_new_user textVerify_itsMoreFunToPlay() throws InterruptedException {
		Its_More_Fun_To_Play.isDisplayed();

		ActualText = AssertionValues.itsMoreFunToPlay;
		System.err.println(ActualText);
		ExpectedText = Its_More_Fun_To_Play.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify IT’S MORE FUN TO PLAY TOGETHER text");

		return this;
	}

}