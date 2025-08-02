package stepdefinitions;

import static engine.Engine.getDriver;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.WatchedVideoCleanerPage;

public class WatchedVideoCleanerSteps {

	WatchedVideoCleanerPage watchedVideoCleanerPage = new WatchedVideoCleanerPage(getDriver());

	@Given("The user has some bookmarks and downloads and is on the Watched Video Cleaner popup")
	public void the_user_has_some_bookmarks_and_downloads_and_is_on_the_watched_video_cleaner_popup() {
		watchedVideoCleanerPage.bookmarkAndDownload();
		watchedVideoCleanerPage.navigateToWatchedVideoCleanerPopup();
	}

	@When("The user taps on Watched Video Cleaner from the three-dot More Options menu")
	public void the_user_taps_on_watched_video_cleaner_from_the_three_dot_more_options_menu() {
		watchedVideoCleanerPage.navigateToWatchedVideoCleanerPopup();
	}

	@Then("The user should see the Watched Video Cleaner popup with buttons Cancel and Clean")
	public void the_user_should_see_the_watched_video_cleaner_popup_with_buttons_cancel_and_clean() {
		Assert.assertTrue(watchedVideoCleanerPage.isWatchedVideoCleanerPopupDisplayed());
	}

	@Given("The user is on the Watched Video Cleaner popup")
	public void the_user_is_on_the_watched_video_cleaner_popup() {
		watchedVideoCleanerPage.navigateToWatchedVideoCleanerPopup();
	}

	@When("The user taps the Cancel button on the Watched Video Cleaner popup")
	public void the_user_taps_the_cancel_button_on_the_watched_video_cleaner_popup() {
		watchedVideoCleanerPage.clickCancel();
	}

	@Then("The watched video cleaner popup should close and the user remains on the home page")
	public void the_watched_video_cleaner_popup_should_close_and_the_user_remains_on_the_home_page() {
		Assert.assertFalse(watchedVideoCleanerPage.isWatchedVideoCleanerPopupDisplayed());
	}
	
	@When("The user checks both Remove watched downloads and Remove watched bookmarks checkboxes and taps the Clean button")
	public void the_user_checks_both_remove_watched_downloads_and_remove_watched_bookmarks_checkboxes_and_taps_the_clean_button() {
		watchedVideoCleanerPage.clickClean();
	}

	@Then("Both Watched bookmarks and downloads should be removed")
	public void both_watched_bookmarks_and_downloads_should_be_removed() {
		Assert.assertFalse(watchedVideoCleanerPage.isBookmarkExists());
		Assert.assertFalse(watchedVideoCleanerPage.isDownloadExists());
	}

	@When("The user checks only the Remove watched bookmarks checkbox and taps the Clean button")
	public void the_user_checks_only_the_remove_watched_bookmarks_checkbox_and_taps_the_clean_button() {
		watchedVideoCleanerPage.checkRemoveWatchedBookmarksCheckbox();
		watchedVideoCleanerPage.uncheckRemoveWatchedDownloadsCheckbox();
		watchedVideoCleanerPage.clickClean();
	}

	@Then("Only the watched bookmarks should be removed")
	public void only_the_watched_bookmarks_should_be_removed() {
		Assert.assertFalse(watchedVideoCleanerPage.isBookmarkExists());
		//Assert.assertTrue(watchedVideoCleanerPage.isDownloadExists());
	}

	@When("The user checks only the Remove watched downloads checkbox and taps the Clean button")
	public void the_user_checks_only_the_remove_watched_downloads_checkbox_and_taps_the_clean_button() {
		watchedVideoCleanerPage.checkRemoveWatchedDownloadsCheckbox();
		watchedVideoCleanerPage.uncheckRemoveWatchedBookmarksCheckbox();
		watchedVideoCleanerPage.clickClean();
	}

	@Then("Only the watched downloads should be removed")
	public void only_the_watched_downloads_should_be_removed() {
		Assert.assertTrue(watchedVideoCleanerPage.isBookmarkExists());
		Assert.assertFalse(watchedVideoCleanerPage.isDownloadExists());
	}



}
