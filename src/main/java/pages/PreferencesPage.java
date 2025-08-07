package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PreferencesPage extends BasePage {

	public PreferencesPage(AppiumDriver driver) {
		super(driver);
	}
	
	@AndroidFindBy(accessibility = "More options")
	private WebElement moreOptionsBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Preferences\")")
	private WebElement preferencesMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_deny_button\")")
	private WebElement notificationDenyBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Video Player\")")
	private WebElement videoPlayerMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Video Blocker\")")
	private WebElement videoBlockerMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"SponsorBlock\")")
	private WebElement sponsorBlockMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Import/Export\")")
	private WebElement importExportMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Privacy\")")
	private WebElement privacyMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Network and Downloads\")")
	private WebElement networkDownloadsMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Others\")")
	private WebElement othersMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"About\")")
	private WebElement aboutMenu;

	public void openPreferences() {
		moreOptionsBtn.click();
		preferencesMenu.click();
//		notificationDenyBtn.click();
//        wait.until(ExpectedConditions.visibilityOf(preferencesMenu)).click();
//        wait.until(ExpectedConditions.visibilityOf(notificationDenyBtn)).click();
	}
	
	public WebElement getVideoPlayerMenu() {
	    return videoPlayerMenu;
	}
	public WebElement getVideoBlockerMenu() {
	    return videoBlockerMenu;
	}
	public WebElement getSponsorBlockMenu() {
	    return sponsorBlockMenu;
	}
	public WebElement getImportExportMenu() {
	    return importExportMenu;
	}
	public WebElement getPrivacyMenu() {
	    return privacyMenu;
	}
	public WebElement getNetworkDownloadsMenu() {
	    return networkDownloadsMenu;
	}
	public WebElement getOthersMenu() {
	    return othersMenu;
	}
	public WebElement getAboutMenu() {
	    return aboutMenu;
	}
}
