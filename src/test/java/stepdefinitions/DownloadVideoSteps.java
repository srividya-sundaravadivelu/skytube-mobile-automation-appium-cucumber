package stepdefinitions;

import static engine.Engine.getDriver;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DownloadVedioPage;

public class DownloadVideoSteps {

	DownloadVedioPage downloadVedioPage = new DownloadVedioPage(getDriver());

	@When("The user shoul be on the application's downloads tab")
	public void the_user_shoul_be_on_the_application_s_downloads_tab() {
		downloadVedioPage.clickDownloadTab();
	}

	@Then("The Downloads Tab should display a message Download some videos to find them here")
	public void the_downloads_tab_should_display_a_message_download_some_videos_to_find_them_here() {
//		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
//
//		// Locate the empty state message element
//		WebElement emptyMessageElement = wait
//				.until(ExpectedConditions.visibilityOf(downloadVedioPage.downloadSomeVediosMsg));	

		// Assert.assertEquals("Download some videos to find them here.",
		// emptyMessageElement.getText());

		String msg = downloadVedioPage.downloadSomeVediosMsg.getText();
		Assert.assertEquals("Download some videos to find them here.", msg);
	}

	@When("user taps three dots next to the video and select Download option of the video")
	public void user_taps_three_dots_next_to_the_video_and_select_download_option_of_the_video() {

		downloadVedioPage.testDownloadToastFlowDynamicWait();

	}

	@Then("The selected video should be under downloads tab")
	public void the_selected_video_should_be_under_downloads_tab() {
		System.out.println("Clicking on DOWNLOADS tab...");
		// Click the "DOWNLOADS" tab
		downloadVedioPage.clickDownloadTab(); // better than accessing field directly

		// Wait for the title of downloaded video to be visible
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		WebElement videoTitle = wait.until(ExpectedConditions.visibilityOf(downloadVedioPage.titleOfDownloadedVedio));

		String downloadedTitle = videoTitle.getText();
		System.out.println("Downloaded video title: " + downloadedTitle);

		// Assert to check title is not null or empty
		Assert.assertTrue("Downloaded video not found in Downloads tab!",
				downloadedTitle != null && !downloadedTitle.trim().isEmpty());
	}

	@Given("The user is on the application's downloads tab")
	public void the_user_is_on_the_application_s_downloads_tab() {
		downloadVedioPage.clickDownloadTab();
	}

	@When("user taps three dots next to the video and select Delete Download option of the video")
	public void user_taps_three_dots_next_to_the_video_and_select_delete_download_option_of_the_video() {

		downloadVedioPage.clickThreeDotsOfDownloads();

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		WebElement deleteOption = wait.until(ExpectedConditions.visibilityOf(downloadVedioPage.deleteDownloadOption));
		deleteOption.click();

	}

	@Then("The selected video should be deleted from downloads tab")
	public void the_selected_video_should_be_deleted_from_downloads_tab() {

		boolean isDeleted = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
				.until(ExpectedConditions.invisibilityOf(downloadVedioPage.titleOfDownloadedVedio));

		Assert.assertTrue("Video was not deleted from downloads tab!", isDeleted);
	}

}
