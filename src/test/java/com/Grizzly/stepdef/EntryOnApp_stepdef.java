package com.Grizzly.stepdef;

import com.Grizzly.screens.Grizz_entry_on_user;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class EntryOnApp_stepdef {

	@Given("user verify text on starting pages")
	public void user_verify_text_on_starting_pages() throws InterruptedException {
		new Grizz_entry_on_user().textVerify_aboutTheGame();
		new Grizz_entry_on_user().textVerify_aFunWay();

	}

	@Then("user click on Next button")
	public void user_click_on_Next_button() {
		new Grizz_entry_on_user().clickNextBtn();
	}

}
