package pages;

import java.time.Duration;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;



public class SearchPage extends BasePage  {
	
	public SearchPage(AppiumDriver driver) {
        super(driver);
    }
	
    @AndroidFindBy(id = "android:id/button3")
    public WebElement dismissPopupButton;

    @AndroidFindBy(id = "free.rm.skytube.extra:id/menu_search")
    public WebElement searchIcon;

    @AndroidFindBy(id = "free.rm.skytube.extra:id/search_src_text")
    public WebElement searchInput;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cocomelon - Nursery Rhymes']")
    public WebElement channelName;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"TRENDING (US)\")")
    public WebElement trendingUSTab;
    
//    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Error:Try different keywords or remove search filters\")")
//    public WebElement invalidchannelerrormsg;
    
//    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Try different keywords\")")
//    public WebElement invalidchannelerrormsg;
    
    @AndroidFindBy(id = "free.rm.skytube.extra:id/grid_view")
    public WebElement invalidchannelerrormsg;


    public void dismissPopupIfPresent() {
        try {
            if (dismissPopupButton.isDisplayed()) {
                safeClick(dismissPopupButton);
            }
        } catch (Exception e) {
            // popup not present
        }
    }

    public void searchChannel(String channel) {
        safeClick(searchIcon);
        safeSendKeys(searchInput, channel);
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        } else {
            System.out.println("Driver is not AndroidDriver; cannot press ENTER key.");
        }

       
    }

    public boolean isChannelDisplayed(String expectedChannelName) {
        try {
            return channelName.isDisplayed() && channelName.getText().equalsIgnoreCase(expectedChannelName);
        } catch (Exception e) {
            return false;
        }
    }
    public void clickTrendingUSTab() {
        safeClick(trendingUSTab);
    }
    
    
    public boolean isTrendingUSTabDisplayed() {
        try {
            return trendingUSTab.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void errormsg()
    {
    	invalidchannelerrormsg.isDisplayed();
    	invalidchannelerrormsg.getText();
    	System.out.println(invalidchannelerrormsg.getText());
    }
    
    public boolean isErrorMessageDisplayed() {
        try {
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // or reuse global wait
            wait.until(driver -> invalidchannelerrormsg.isDisplayed());
            return invalidchannelerrormsg.isDisplayed();
        } catch (Exception e) {
            System.out.println("Invalid channel error not displayed: " + e.getMessage());
            return false;
        }
    }


}