package stepdefinitions;

import static engine.Engine.getDriver;
import org.junit.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SunscribeUnsubscribePage;


public class SubscribeUnsubscribeSteps {
	
	SunscribeUnsubscribePage subunsubPage = new SunscribeUnsubscribePage(getDriver());
	
	@When("The user navigate to channel option from menu and click on subscribe option")
	public void the_user_navigate_to_channel_option_from_menu_and_click_on_subscribe_option() {
		//subunsubPage.navigateToChannelAndSubscribe();
		subunsubPage.subunsubmenuoption();
		subunsubPage.channelMenuOption();
		subunsubPage.navigateToChannelAndSubscribe();
	}
	
    @When("The user navigate to channel option from menu and click on unsubscribe option")
    public void the_user_navigate_to_channel_option_from_menu_and_click_on_unsubscribe_option() {
    	subunsubPage.subunsubmenuoption();
		subunsubPage.channelMenuOption();
		subunsubPage.navigateToChannelAndUnSubscribe();
    	
//    	subunsubPage.navigateToChannelAndUnSubscribe();
    	
    }

	@Then("The {string} popup message should be displayed")
	public void the_popup_message_should_be_displayed(String expectedMessage) {
	    Assert.assertTrue("Subscribed popup message displayed!", 
	            subunsubPage.isToastMessageDisplayed(expectedMessage));

	}
		
	@When("The user navigate to channel and click on subscribe option")
    public void the_user_navigate_to_channel_and_click_on_subscribe_option() {
		subunsubPage.clickchannel();
		subunsubPage.clickSubscribeButton();
    }

    @When("The user navigate to channel and click on unsubscribe option")
    public void the_user_navigate_to_channel_and_click_on_unsubscribe_option() {
    	//subunsubPage.navigatetochannelMenu();
		subunsubPage.clickchannel();
		subunsubPage.clickUnsubscribeButton();
    }
    
    
    @When("The user navigate to subscription page")
    public void the_user_navigate_to_subscription_page() {
    	subunsubPage.openSubscriptionTab();
    }

    @When("click on subscribe channel")
    public void click_on_subscribe_channel() {
    	subunsubPage.clickFirstSubscribedChannel();
    }

    @Then("The user navigate to subscribed channel")
    public void the_user_navigate_to_subscribed_channel() {
        boolean isChannelDisplayed = subunsubPage.isSubscribedChannelScreenDisplayed();
        Assert.assertTrue("Subscribed channel screen is not displayed!", isChannelDisplayed);
    }


}