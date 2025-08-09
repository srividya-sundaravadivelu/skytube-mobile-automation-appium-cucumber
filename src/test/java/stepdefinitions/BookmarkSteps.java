package stepdefinitions;

import static engine.Engine.getDriver;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BookmarkPage;

public class BookmarkSteps {

	BookmarkPage bookmarkPage = new BookmarkPage(getDriver());

	@Given("The user is in the home page")
	public void the_user_is_in_the_home_page() {
		bookmarkPage.clickOkButtonIfVisible();
	}

	@When("The user clicks on Bookmarks Tab")
	public void the_user_clicks_on_bookmarks_tab() {
		bookmarkPage.clickBookmarkTab();
	}

	@Then("The user should be able to view the message Bookmark some videos to find them here.")
	public void the_user_should_be_able_to_view_the_message_bookmark_some_videos_to_find_them_here() {
		Assert.assertTrue(bookmarkPage.isEmptyBookmarkMessageVisible());
	}

	@When("The user clicks on More Options and then clicks on Bookmark in the menu")
	public void the_user_clicks_on_more_options_and_then_clicks_on_bookmark_in_the_menu() {
		bookmarkPage.clickTrendingUSMenu();
		bookmarkPage.clickMoreOptionsOnHomePage();
		bookmarkPage.clickBookmarkMenu();
	}

	@Then("The user should be able to view the bookmark video under Bookmarks tab with a message Bookmarked")
	public void the_user_should_be_able_to_view_the_bookmark_video_under_bookmarks_tab_with_a_message_bookmarked() {
		Assert.assertTrue("Bookmark message displayed!", bookmarkPage.verifyToastMessage("Bookmarked"));
		bookmarkPage.clickBookmarkTab();
	}

	@Given("The user is under Bookmarks tab")
	public void the_user_is_under_bookmarks_tab() {
		bookmarkPage.clickOkButtonIfVisible();
		bookmarkPage.clickBookmarkTab();
	}

	@When("The user clicks on More Options and then clicks on Unbookmark in the menu")
	public void the_user_clicks_on_more_options_and_then_clicks_on_unbookmark_in_the_menu() {
		bookmarkPage.clickMoreOptionsOnBookmarkTab();
		bookmarkPage.clickUnbookmarkMenu();
	}

	@Then("The user should not be able to view the bookmarked video under Bookmarks tab with a message Bookmark removed")
	public void the_user_should_not_be_able_to_view_the_bookmarked_video_under_bookmarks_tab_with_a_message_bookmark_removed() {
		bookmarkPage.verifyToastMessage("Bookmark removed");
	}
}
