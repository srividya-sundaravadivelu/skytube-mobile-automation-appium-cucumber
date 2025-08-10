package stepdefinitions;

import static engine.Engine.getDriver;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.VideoBlockerPage;


public class VideoBlockerSteps {
	
	//HomePage homePage = new HomePage(getDriver());	
	VideoBlockerPage videoBlockerPage = new VideoBlockerPage(getDriver());
	
	@Given("The user is on the application's home page")
	public void the_user_is_on_the_application_s_home_page() {
		videoBlockerPage.dismissVerisonUpdatePopup();
		videoBlockerPage.clickTrendingUsTab();
	}
	
	@When("The user taps the shield icon next to search button")
	public void the_user_taps_the_shield_icon_next_to_search_button() {
	   videoBlockerPage.clickVideoBlocker();	  
	}
	
	@Then("The user should see the Video Blocker popup with buttons Cancel and Setup")
	public void the_user_should_see_the_video_blocker_popup_with_buttons_cancel_and_setup() {
	    Assert.assertTrue(videoBlockerPage.isVideoBlockerPopupDisplayed());
	}
	
	@Given("The user is on the video blocker popup")
	public void the_user_is_on_the_video_blocker_popup() {
		videoBlockerPage.clickVideoBlocker();
	}
	
	@When("The user taps the Cancel button")
	public void the_user_taps_the_cancel_button() {
	   videoBlockerPage.clickCancel();
	}
	@Then("The popup should close and the user remains on the home page")
	public void the_popup_should_close_and_the_user_remains_on_the_home_page() {
		Assert.assertFalse(videoBlockerPage.isVideoBlockerPopupDisplayed());
	}
	
	@When("The user taps the Set up button")
	public void the_user_taps_the_set_up_button() {
		videoBlockerPage.clickSetup();
	}
	@Then("The user is navigated to the Preferences page")
	public void the_user_is_navigated_to_the_preferences_page() {
	    Assert.assertTrue(videoBlockerPage.isPreferencesDisplayed());
	}

}
