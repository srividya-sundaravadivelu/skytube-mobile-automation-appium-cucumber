package pages;

import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class VideoBlockerPage extends BasePage {

	public VideoBlockerPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(id = "android:id/button3")
	// @iOSXCUITFindBy(accessibility = "button3")
	public WebElement dismissPopupButton;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/menu_blocker")
	public WebElement shieldButton;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/md_title")
	public WebElement videoBlockerPopupTitle;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/md_buttonDefaultNegative")
	public WebElement cancelButton;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/md_buttonDefaultPositive")
	public WebElement setUpButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Preferences\")")
	public WebElement preferences;

	public void dismissVerisonUpdatePopup() {
		safeClick(dismissPopupButton);
	}

	public void clickVideoBlocker() {
		safeClick(shieldButton);
	}

	public void clickCancel() {
		safeClick(cancelButton);
	}

	public void clickSetup() {
		safeClick(setUpButton);
	}

	public boolean isVideoBlockerPopupDisplayed() {
		try {
			if (videoBlockerPopupTitle.isDisplayed() && videoBlockerPopupTitle.getText().equals("Video Blocker")
					&& cancelButton.isDisplayed() && setUpButton.isDisplayed()) {
				clickCancel();
				return true;
			}

		} catch (Exception e) {
			System.out.println("Video Blocker popup is not displayed.");
		}
		return false;
	}

	public boolean isPreferencesDisplayed() {
		try {

			if (preferences.isDisplayed()) {
				goBack();
				return true;
			}
		} catch (Exception e) {
			System.out.println("Element not visible" + preferences);
		}
		return false;

	}

}
