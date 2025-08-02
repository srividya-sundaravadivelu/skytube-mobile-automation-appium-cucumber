package utils;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

import logger.*;
/**
 * Utility class for Cucumber use cases.
 *
 * 
 */
@SuppressWarnings({ "unused" })
public class CucumberUtils {

	/**
	 * ThreadLocal variable to hold the current scenario.
	 */
	public static ThreadLocal<Scenario> tlScenario = new ThreadLocal<>();

	/**
	 * HTML style for success log messages.
	 */
	private static final String SUCCESS_LOG = "<span style='color: #06980e;'>$message</span>";

	/**
	 * HTML style for failure log messages.
	 */
	private static final String FAILURE_LOG = "<span style='color: red;'>$message</span>";

	/**
	 * HTML style for warning log messages.
	 */
	private static final String WARN_LOG = "<span style='color: #ff8800;'>$message</span>";

	/**
	 * HTML style for skip log messages.
	 */
	private static final String SKIP_LOG = "<span style='color: #d4d170;'>$message</span>";

	/**
	 * HTML style for abort log messages.
	 */
	private static final String ABORT_LOG = "<span style='color: #5c5c5c;'>$message</span>";

	/**
	 * Retrieves the current Cucumber scenario.
	 *
	 * @return The current scenario
	 * @throws NullPointerException if the scenario is not set
	 */
	public static Scenario getCurrentScenario() {
		if (tlScenario.get() == null)
			throw new NullPointerException("Set Scenario First...");
		else
			return tlScenario.get();
	}

	/**
	 * Sets the current Cucumber scenario.
	 *
	 * @param scenario The scenario to set
	 */
	public static void setCurrentScenario(Scenario scenario) {
		tlScenario.set(scenario);
	}

	public static void attachScreenshot(AppiumDriver driver) {
		getCurrentScenario().attach(driver.getScreenshotAs(OutputType.BYTES), "image/png", "Attached Image");
	}

	
	public static void logSuccessToReport(String message) {
		getCurrentScenario().log(SUCCESS_LOG.replace("$message", message));
		Log.info(message);
	}


	public static void logFailureToReport(String message) {
		getCurrentScenario().log(FAILURE_LOG.replace("$message", message));
		Log.info(message);
	}

	
	public static String getFeatureNameFromScenario(Scenario scenario, boolean withPackageName) {
		String uri = scenario.getUri().toString();
		String featureName, packageName;
		String[] test = uri.split("/");
		if (uri.startsWith("file")) {
			int size = test.length;
			featureName = test[size - 1].split("\\.")[0];
			packageName = test[size - 2];
		} else {
			featureName = test[1].split("\\.")[0];
			packageName = test[0].split(":")[1];
		}
		Log.info("Feature: " + featureName);
		Log.info("Package: " + packageName);
		if (withPackageName)
			return packageName + " - " + featureName;
		else
			return featureName;
	}
	
	public static void logToReport(String text) {
	    getCurrentScenario().log(text);
	    Log.info(text);
	  }

	
}