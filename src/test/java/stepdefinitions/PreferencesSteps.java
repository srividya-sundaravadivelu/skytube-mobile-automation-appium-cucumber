package stepdefinitions;

import static engine.Engine.getDriver;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import engine.Engine;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PreferencesPage;

public class PreferencesSteps {

	PreferencesPage preferencesPage = new PreferencesPage(getDriver());

	@When("The user clicks on Preferences menu")
	public void the_user_clicks_on_preferences_menu() {
		preferencesPage.openPreferences();
	}

	@Then("The user should be able to view {string} menu in the Preferences page")
	public void the_user_should_be_able_to_view_menu_in_the_preferences_page(String menuName) {
		WebElement element = null;

		switch (menuName) {
		case "Video Player":
			element = preferencesPage.getVideoPlayerMenu();
			break;
		case "Video Blocker":
			element = preferencesPage.getVideoBlockerMenu();
			break;
		case "SponsorBlock":
			element = preferencesPage.getSponsorBlockMenu();
			break;
		case "Import/Export":
			element = preferencesPage.getImportExportMenu();
			break;
		case "Privacy":
			element = preferencesPage.getPrivacyMenu();
			break;
		case "Network and Downloads":
			element = preferencesPage.getNetworkDownloadsMenu();
			break;
		case "Others":
			element = preferencesPage.getOthersMenu();
			break;
		case "About":
			element = preferencesPage.getAboutMenu();
			break;
		default:
			throw new IllegalArgumentException("Unknown menu: " + menuName);
		}

		Assert.assertTrue(element.isDisplayed());
		Engine.getDriver().navigate().back();
	}
}
