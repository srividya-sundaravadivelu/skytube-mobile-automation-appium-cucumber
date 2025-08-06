package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadVedioPage extends BasePage {
	
	public DownloadVedioPage(AppiumDriver driver) {
		super(driver);
	}
	
	@AndroidFindBy(id = "free.rm.skytube.extra:id/options_button")
	public WebElement threeDots;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Download\")")
	public WebElement downloadMenuItem;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"DOWNLOADS\")")
	public WebElement downloadTab;
	
	@AndroidFindBy(id = "free.rm.skytube.extra:id/channel_text_view")
	public WebElement titleOfDownloadedVedio;
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	public WebElement notificationAlert;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"TRENDING (US)\")")
	public WebElement trendingUsTab;
	
	
//	@AndroidFindBy(xpath = "(//android.widget.ImageButton[@resource-id=\"free.rm.skytube.extra:id/options_button\"]")
//	public WebElement threeDotsOfDownloads;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.extra:id/options_button\")")
	public WebElement threeDotsOfDownloads;
	
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Delete Download\")")
	public WebElement deleteDownloadOption;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"free.rm.skytube.extra:id/noDownloadedVideosText\")")
	public WebElement downloadSomeVediosMsg;
	
	public void clickThreeDotMenu() {
		safeClick(threeDots);
	}
	
	public void clickDownloadTab() {
		safeClick(downloadTab);
	}
	
	public void clickThreeDotsOfDownloads() {
		safeClick(threeDotsOfDownloads);
	}
	
	public void clickTrendingUsTab() {
		safeClick(trendingUsTab);
	}
	
	public void clickDownloadMenuItem() {
		
		safeClick(downloadMenuItem);
		safeClick(notificationAlert);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement toastElement = wait.until(ExpectedConditions
		        .presenceOfElementLocated(By.xpath("//android.widget.Toast")));

		String toastMessage = toastElement.getText();
		System.out.println("Captured Toast: " + toastMessage);
		
	}
	
	public String waitForFinalDownloadToast(int maxWaitInSeconds) {
	    long startTime = System.currentTimeMillis();
	    String toastText = null;

	    while ((System.currentTimeMillis() - startTime) < maxWaitInSeconds * 1000) {
	        try {
	            WebDriverWait toastWait = new WebDriverWait(driver, Duration.ofSeconds(1));
	            WebElement toast = toastWait.until(
	                    ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast")));
	            toastText = toast.getText();

	            // Only break if it's NOT the "downloading..." message
	            if (!toastText.toLowerCase().contains("downloading")) {
	                return toastText;
	            }
	        } catch (Exception ignored) {
	            // No toast found in this 1-second slice; keep looping
	        }
	    }

	    throw new RuntimeException("Final toast (completed/failed) not found within timeout");
	}

	
	
	public boolean isDownloadMsgDisplayed() {
		try {

			if (downloadSomeVediosMsg.isDisplayed()) {
				goBack();
				return true;
			}
		} catch (Exception e) {
			System.out.println("Element not visible" + downloadSomeVediosMsg);
		}
		return false;

	}
	public void testDownloadToastFlowDynamicWait() {
	    DownloadVedioPage downloadVedioPage = new DownloadVedioPage(driver);
	    int maxAttempts = 5;
	    int attempt = 0;
	    boolean downloadSuccess = false;

	    while (attempt < maxAttempts) {
	        System.out.println("Attempt #" + (attempt + 1) + " to download video...");

	        downloadVedioPage.clickThreeDotMenu();
	        downloadVedioPage.clickDownloadMenuItem();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	        try {
	            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//android.widget.Toast")
	            ));

	            String message = toast.getText();
	            System.out.println("Captured Toast: " + message);

	            if (message.toLowerCase().contains("downloading")) {
	                // Wait for the final toast (up to 60s)
	                String finalMessage = waitForFinalDownloadToast(60);
	                System.out.println("Final toast: " + finalMessage);

	                if (finalMessage.toLowerCase().contains("downloaded") ||
	                    finalMessage.toLowerCase().contains("complete")) {
	                    downloadSuccess = true;
	                    break;
	                }

	            } else if (message.toLowerCase().contains("downloaded")) {
	                // Sometimes download completes quickly
	                downloadSuccess = true;
	                break;
	            } else {
	                System.out.println("Unexpected first toast, skipping retry...");
	            }

	        } catch (Exception e) {
	            System.out.println("Toast not found. Retrying... " + e.getMessage());
	        }

	        attempt++;
	    }

	    if (!downloadSuccess) {
	        throw new RuntimeException("Download failed after " + maxAttempts + " attempts.");
	    }
	}

}
