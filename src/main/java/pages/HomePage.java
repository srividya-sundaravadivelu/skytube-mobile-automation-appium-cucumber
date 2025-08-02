//package pages;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.PageFactory;
//
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.pagefactory.AndroidFindBy;
//import io.appium.java_client.pagefactory.AppiumFieldDecorator;
////import io.github.the_sdet.mobile.AppiumUtils;
//
//public class HomePage  {
//
//	@AndroidFindBy(id = "android:id/button3")
//	// @iOSXCUITFindBy(accessibility = "button3")
//	public WebElement dismissPopupButton;
//
//	public HomePage(AppiumDriver driver) {
//		//super(driver);
//		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//	}
//
//	public void dismissVerisonUpdatePopup() {
//		try {
//			if (dismissPopupButton.isDisplayed()) {
//				System.out.println("Version Update Popup is displayed. Clicking OK button to dismiss.");
//				dismissPopupButton.click();
//			} else {
//				System.out.println("Version Update Popup not found. Continuing with test.");
//			}
//		} catch (Exception e) {
//			System.out.println("Version Update Popup already dismissed. Continuing with test.");
//		}
//	}
//}
