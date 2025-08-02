package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WatchedVideoCleanerPage extends BasePage {

	public WatchedVideoCleanerPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "More options")
	public WebElement moreOptionsButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Watched Video Cleaner\")")
	public WebElement watchedVideoCleaner;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/md_title")
	public WebElement watchedVideoPopupTitle;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/clean_watched_downloads")
	public WebElement removeWatchedDownloadsCheckbox;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/clean_watched_bookmarks")
	public WebElement removeWatchedBookmarksCheckbox;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/md_buttonDefaultNegative")
	public WebElement cancelButton;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/md_buttonDefaultPositive")
	public WebElement cleanButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"TRENDING (US)\")")
	public WebElement trendingUSTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.extra:id/options_button\").instance(0)")
	public WebElement optionsButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Bookmark\")")
	public WebElement bookmark;

	@AndroidFindBy(accessibility = "Bookmarks")
	public WebElement bookmarksTab;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/options_button")
	public WebElement options;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Mark as Watched\")")
	public WebElement markAsWatched;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Download\")")
	public WebElement download;

	@AndroidFindBy(accessibility = "Downloads")
	public WebElement downloadsTab;

	@AndroidFindBy(id = "free.rm.skytube.extra:id/title_text_view")
	public WebElement videoTitle;

	@AndroidFindBy(xpath = "(//android.widget.Toast)")
	public WebElement toastMessage;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Unbookmark\")")
	public WebElement unbookmark;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"free.rm.skytube.extra:id/title\" and @text=\"Delete Download\"]")
	public WebElement deleteDownload;

	public void navigateToWatchedVideoCleanerPopup() {
		clickMoreOptions();
		clickWatchedVideoCleaner();
	}

	public void clickCancel() {
		safeClick(cancelButton);
	}

	public void clickClean() {
		safeClick(cleanButton);
	}

	public void checkRemoveWatchedDownloadsCheckbox() {
		setCheckbox(removeWatchedDownloadsCheckbox, true);
	}

	public void uncheckRemoveWatchedDownloadsCheckbox() {
		setCheckbox(removeWatchedDownloadsCheckbox, false);
	}

	public void checkRemoveWatchedBookmarksCheckbox() {
		setCheckbox(removeWatchedBookmarksCheckbox, true);
	}

	public void uncheckRemoveWatchedBookmarksCheckbox() {
		setCheckbox(removeWatchedBookmarksCheckbox, false);
	}

	public boolean isWatchedVideoCleanerPopupDisplayed() {
		try {
			if (watchedVideoPopupTitle.isDisplayed() && watchedVideoPopupTitle.getText().equals("Watched Video Cleaner")
					&& cancelButton.isDisplayed() && cleanButton.isDisplayed()) {
				clickCancel();
				return true;
			}

		} catch (Exception e) {
			System.out.println("Watched Video Cleaner popup is not displayed.");
		}
		return false;
	}

	public void bookmarkAndDownload() {
		bookmarkVideoAndMarkAsWatched();
		downloadVideo();
	}

	public boolean isBookmarkExists() {
		safeClick(bookmarksTab);
		return isVideoExists();
	}

	public boolean isDownloadExists() {
		safeClick(downloadsTab);
		return isVideoExists();
	}

	private boolean isVideoExists() {
		try {
			if (videoTitle.isDisplayed())
				return true;

		} catch (Exception e) {
			System.out.println("Video does not exists.");
		}
		return false;
	}

	private void clickMoreOptions() {
		safeClick(moreOptionsButton);
	}

	private void clickWatchedVideoCleaner() {
		safeClick(watchedVideoCleaner);
	}

	private void bookmarkVideoAndMarkAsWatched() {
		safeClick(trendingUSTab);
		safeClick(optionsButton);
		safeClick(bookmark);

		safeClick(bookmarksTab);
		safeClick(options);
		safeClick(markAsWatched);
	}

	private void downloadVideo() {
		safeClick(trendingUSTab);
		safeClick(optionsButton);
		safeClick(download);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast")));

		safeClick(downloadsTab);
	}

}
