package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BookmarkPage extends BasePage {

	public BookmarkPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(id = "android:id/button3")
	private WebElement okButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"TRENDING (US)\")")
	private WebElement trendingUSMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.extra:id/options_button\").instance(0)")
	private WebElement moreOptionIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Bookmark\")")
	private WebElement bookmarkMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Unbookmark\")")
	private WebElement unBookmarkMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"BOOKMARKS\")")
	private WebElement bookmarkTab;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/options_button")
	private WebElement moreOptionBookmarkIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Bookmarked\")")
	private String toastXpath;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Bookmark some videos to find them here')]")
	private WebElement emptyBookmarkText;

	public void clickOkButtonIfVisible() {
		try {
			if (okButton.isDisplayed()) {
				okButton.click();
			}
		} catch (Exception e) {
			// element not present, ignore
		}
	}

	public void clickTrendingUSMenu() {
		trendingUSMenu.click();
	}

	public void clickMoreOptionsOnHomePage() {
		moreOptionIcon.click();
	}

	public void clickBookmarkMenu() {
		bookmarkMenu.click();
	}

	public void clickUnbookmarkMenu() {
		unBookmarkMenu.click();
	}

	public void clickBookmarkTab() {
		bookmarkTab.click();
	}

	public void clickMoreOptionsOnBookmarkTab() {
		moreOptionBookmarkIcon.click();
	}

	public boolean verifyToastMessage(String expectedText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//android.widget.Toast[@text='" + expectedText + "']"))) != null;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isEmptyBookmarkMessageVisible() {
		try {
			return emptyBookmarkText.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
