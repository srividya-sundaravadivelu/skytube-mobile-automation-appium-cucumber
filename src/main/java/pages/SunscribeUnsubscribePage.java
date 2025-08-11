package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
public class SunscribeUnsubscribePage extends BasePage {

	
	public SunscribeUnsubscribePage(AppiumDriver driver) {
        super(driver);
    }
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.extra:id/options_button\").instance(0)")
	private WebElement firstOptionsButton;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Channel...\")")
	private WebElement channelMenuOption;
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Subscribe\")")
	private WebElement subscribeoption;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Unsubscribe\")")
	private WebElement unsubscribeoption;
	 @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Cocomelon - Nursery Rhymes\")")
	    public WebElement channelNameclick;
	
	@AndroidFindBy(id = "free.rm.skytube.extra:id/channel_subscribe_button")
	private WebElement subscribeButton;
	
	@AndroidFindBy(id = "free.rm.skytube.extra:id/channel_subscribe_button")
	private WebElement unsubscribeButton;
		 
	@AndroidFindBy(xpath = "//*[@text='Subscribed']")
	private WebElement subscribemessage;
	
	@AndroidFindBy(accessibility = "Navigate up")
	private WebElement gobackmain;
	
	@AndroidFindBy(accessibility = "SkyTube Extra")
	private WebElement skyTubeExtra;

    @AndroidFindBy(id = "free.rm.skytube.extra:id/navigation_subscriptions")
    private WebElement subscriptionTab;

    @AndroidFindBy(id = "free.rm.skytube.extra:id/sub_channel_name_text_view")
    private WebElement channelName; // visible when inside channel page

    @AndroidFindBy(id = "free.rm.skytube.extra:id/channel_subs_text_view")
    private WebElement subscribedChannelTextView;

    
    @AndroidFindBy(id = "free.rm.skytube.extra:id/channel_subs_text_view")
    private WebElement channeltext; // visible when inside channel page

	public void navigatetochannelMenu()
	{
		safeClick(firstOptionsButton);
		safeClick(channelMenuOption);
	}
	
	public void clickchannel()
	{
		safeClick(channelNameclick);
	}
	
	public void clickSubscribeButton()
	{
		safeClick(subscribeButton);
	}
	
	public void clickUnsubscribeButton()
	{
		safeClick(unsubscribeButton);
	}
	
	public void subunsubmenuoption() {
		safeClick(firstOptionsButton);
	}
	public void channelMenuOption() {
		safeClick(channelMenuOption);
	}
	
	public void navigateToChannelAndSubscribe() {
//		safeClick(firstOptionsButton);
//		safeClick(channelMenuOption);
		safeClick(subscribeoption);
	}
//	public void navigateToChannelAndSubscribe() {
//		safeClick(firstOptionsButton);
//		safeClick(channelMenuOption);
//		safeClick(subscribeoption);
//	}
//	
	public void navigateToChannelAndUnSubscribe() {
//		safeClick(firstOptionsButton);
//		safeClick(channelMenuOption);
		safeClick(unsubscribeoption);
	}
	public boolean isToastMessageDisplayed(String expectedText) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        return wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//android.widget.Toast[@text='" + expectedText + "']")
	        )) != null;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
    public void openSubscriptionTab() {
    	safeClick(gobackmain);
        safeClick(skyTubeExtra);
    }
    
    public void clickFirstSubscribedChannel() {
        safeClick(channelName);
    }
    
    public boolean isSubscribedChannelScreenDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(subscribedChannelTextView));
            return subscribedChannelTextView.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }


}
