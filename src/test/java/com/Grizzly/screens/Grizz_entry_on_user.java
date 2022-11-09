package com.Grizzly.screens;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.Grizzly.constants.AssertionValues;
import com.Grizzly.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import ru.yandex.qatools.ashot.Screenshot;

public class Grizz_entry_on_user extends BaseTestMobile {
	public String ActualText = null;
	public String ExpectedText = null;
	private int scriptWaitTime = 2000;
	TestUtils utils = new TestUtils();

//	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60L))
//			.pollingEvery(Duration.ofSeconds(5L)).ignoring(NoSuchElementException.class);
//
//	WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//		public WebElement apply(WebDriver driver) {
//			return driver.findElement(By.id("foo"));
//		}
//	});

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Skip')]")
	public MobileElement skip_btn;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc, 'Next Button')]")
	public MobileElement next_btn;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'WELCOME TO SPORTS BINGO!')]")
	public MobileElement welcome_to_sport_bingo;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'A fun way to watch sports, follow events as they happen in the game, and compete with your friends!')]")
	public MobileElement A_Fun_Way;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'ABOUT THE GAME')]")
	public MobileElement About_The_Game;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Follow along on your bingo card as the events on your card happen.  We use live results to fill in your squares automatically.')]")
	public MobileElement Follow_Along;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'HOW TO PLAY')]")
	public MobileElement How_To_Play;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'When events happen you earn points based on the odds of an event happening and bonus points for completing a line.')]")
	public MobileElement When_Events_Happen;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'TRADE A SQUARE')]")
	public MobileElement Trade_A_Square;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Want a better chance to complete a line? Drag a square you want to trade and drop to preferred location.')]")
	public MobileElement Want_A_Better_Chance;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'COMPETE WITH FRIENDS')]")
	public MobileElement Compete_With_Friends;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Form or join a group with friends for a friendly competition! See who is on top of the leaderboard.')]")
	public MobileElement Form_Join_group;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Legal and Privacy')]")
	public MobileElement Legal_And_Privacy;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'I have read and agree to Sports Bingo’s Terms of Services and Privacy Policy.')]")
	public MobileElement Agree_Terms_And_Privacy_Policy;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-disc, 'GetStarted Button')]")
	public MobileElement Get_Started_Btn;

	public Grizz_entry_on_user click_getStarted() throws InterruptedException {
		Get_Started_Btn.isDisplayed();

		Get_Started_Btn.isEnabled();
		click(Get_Started_Btn, "click get started button");
		BaseTestMobile.appiumWait(scriptWaitTime);

		Get_Started_Btn.isSelected();

		return this;
	}

	public Grizz_entry_on_user textVerify_checkBox_agreeTermsAndPrivacyPolicy() throws InterruptedException {
		Agree_Terms_And_Privacy_Policy.isDisplayed();

		ActualText = AssertionValues.agreeTermsAndPrivacyPolicy;
		System.err.println(ActualText);
		ExpectedText = Agree_Terms_And_Privacy_Policy.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify I have read and agree to Sports Bingo’s Terms of Services and Privacy Policy text");

		Agree_Terms_And_Privacy_Policy.isEnabled();
		click(Agree_Terms_And_Privacy_Policy, "checkbox select agree Terms of serivce and privacy policy");
		Agree_Terms_And_Privacy_Policy.isSelected();

		return this;
	}

	public Grizz_entry_on_user textVerify_legalAndPrivacy() throws InterruptedException {
		Legal_And_Privacy.isDisplayed();

		ActualText = AssertionValues.legalAndPrivacy;
		System.err.println(ActualText);
		ExpectedText = Legal_And_Privacy.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText, "successfully verify legal and privacy text");

		return this;
	}

	public Grizz_entry_on_user textVerify_competeWithFriends() throws InterruptedException {
		Compete_With_Friends.isDisplayed();

		ActualText = AssertionValues.competeWithFriends;
		System.err.println(ActualText);
		ExpectedText = Compete_With_Friends.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText, "successfully verify compete with friends text");

		return this;
	}

	public Grizz_entry_on_user textVerify_formjoinGroup() throws InterruptedException {
		Form_Join_group.isDisplayed();

		ActualText = AssertionValues.formJoinGroup;
		System.err.println(ActualText);
		ExpectedText = Form_Join_group.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify Form or join a group with friends for a friendly competition! See who is on top of the leaderboard text");

		return this;
	}

	public Grizz_entry_on_user textVerify_tradeASquare() throws InterruptedException {
		Trade_A_Square.isDisplayed();

		ActualText = AssertionValues.tradeASquare;
		System.err.println(ActualText);
		ExpectedText = Trade_A_Square.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText, "successfully verify trade a square text");

		return this;
	}

	public Grizz_entry_on_user textVerify_wantABetterChance() throws InterruptedException {
		Want_A_Better_Chance.isDisplayed();

		ActualText = AssertionValues.wantABetterChance;
		System.err.println(ActualText);
		ExpectedText = Want_A_Better_Chance.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify Want a better chance to complete a line? Drag a square you want to trade and drop to preferred location text");

		return this;
	}

	public Grizz_entry_on_user textVerify_howToPlay() throws InterruptedException {
		How_To_Play.isDisplayed();

		ActualText = AssertionValues.howToPlay;
		System.err.println(ActualText);
		ExpectedText = How_To_Play.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText, "successfully verify how to play text");

		return this;
	}

	public Grizz_entry_on_user textVerify_whenEventsHappen() throws InterruptedException {
		When_Events_Happen.isDisplayed();

		ActualText = AssertionValues.whenEventsHappen;
		System.err.println(ActualText);
		ExpectedText = When_Events_Happen.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify When events happen you earn points based on the odds of an event happening and bonus points for completing a line text");

		return this;
	}

	public Grizz_entry_on_user textVerify_aboutTheGame() throws InterruptedException {
		About_The_Game.isDisplayed();
		BaseTestMobile.appiumWait(scriptWaitTime);
		ActualText = AssertionValues.aboutTheGame;
		System.err.println(ActualText);
		ExpectedText = About_The_Game.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText, "successfully verify about the game text");

		return this;
	}

	public Grizz_entry_on_user textVerify_followAlong() throws InterruptedException {
		Follow_Along.isDisplayed();

		ActualText = AssertionValues.followAlong;
		System.err.println(ActualText);
		ExpectedText = Follow_Along.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify Follow along on your bingo card as the events on your card happen.  We use live results to fill in your squares automatically text");

		return this;
	}

	public Grizz_entry_on_user textVerify_welcomeBingo() throws InterruptedException {
		welcome_to_sport_bingo.isDisplayed();

		ActualText = AssertionValues.welcomeToBingo;
		System.err.println(ActualText);
		ExpectedText = welcome_to_sport_bingo.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify welcome to sport bingo text");

		return this;
	}

	public Grizz_entry_on_user textVerify_aFunWay() throws InterruptedException {
		A_Fun_Way.isDisplayed();
		BaseTestMobile.appiumWait(scriptWaitTime);
		ActualText = AssertionValues.aFunWay;
		System.err.println(ActualText);
		ExpectedText = A_Fun_Way.getText();
		System.out.println(ExpectedText);
		verifyActualExpectedResultWithEquals(ActualText, ExpectedText,
				"successfully verify A fun way to watch sports text");

		return this;
	}

	public Grizz_entry_on_user clickSkipBtn() {
		skip_btn.isDisplayed();
		skip_btn.isEnabled();
		click(skip_btn, "press skip button");
		skip_btn.isSelected();
		return this;
	}

	public Grizz_entry_on_user clickNextBtn() {
		next_btn.isDisplayed();
		next_btn.isEnabled();
		click(next_btn, "press next button");
		next_btn.isSelected();
		return this;
	}
}
