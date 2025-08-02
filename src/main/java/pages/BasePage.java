package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {
	AppiumDriver driver;
	
	public BasePage(AppiumDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public boolean safeClick(WebElement element) {
		try {
			if (element.isDisplayed()) {
				element.click();
				return true;
			} else {
				System.out.println("Element not clickable (not displayed): " + element);
			}
		} catch (Exception e) {
			System.out.println("Element not clickable (not displayed): " + element);			
		}
		return false;
	}
	
	public void setCheckbox(WebElement checkbox, boolean shouldBeChecked) {
	    try {
	        boolean isChecked = checkbox.getAttribute("checked").equalsIgnoreCase("true");

	        if (isChecked != shouldBeChecked) {
	            checkbox.click(); // Toggle checkbox state
	            System.out.println("Checkbox toggled to: " + shouldBeChecked);
	        } else {
	            System.out.println("Checkbox already in desired state: " + shouldBeChecked);
	        }

	    } catch (Exception e) {
	        System.err.println("Failed to toggle checkbox: " + e.getMessage());
	    }
	}
	
	public void goBack() {
		driver.navigate().back();
	}

}
