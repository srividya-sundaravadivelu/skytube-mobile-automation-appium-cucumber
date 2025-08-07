package stepdefinitions;

import static engine.Engine.getDriver;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;

public class SearchPageSteps {
	
	SearchPage searchPage = new SearchPage(getDriver());

    @When("The user search the {string} channel")
    public void the_user_search_the_channel(String channelName) {
        searchPage.searchChannel(channelName);
    }

    @Then("The {string} channel name should displayed")
    public void the_channel_name_should_displayed(String expectedChannelName) {
        Assert.assertTrue("Expected channel name is not displayed.",
                searchPage.isChannelDisplayed(expectedChannelName));
    }
    
    @Then("The {string} channel name should not be displayed")
    public void the_channel_name_should_not_be_displayed(String channelName) {
        Assert.assertFalse("Invalid channel was incorrectly displayed!",
                searchPage.isChannelDisplayed(channelName));
    }

    @Then("The error message should be displayed")
    public void the_error_message_should_be_displayed() {
        //Assert.assertTrue(searchPage.isErrorMessageDisplayed());
        boolean isDisplayed = searchPage.isErrorMessageDisplayed();
        System.out.println("Is invalid channel error shown? " + isDisplayed);
        Assert.assertTrue("Expected error message for invalid channel was not displayed", isDisplayed);

    }

}
