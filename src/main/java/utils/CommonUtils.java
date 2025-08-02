package utils;

import io.cucumber.java.Scenario;
import logger.Log;

import java.time.Duration;
import java.util.Properties;

import static engine.Engine.getDriver;
import static utils.CucumberUtils.*;

/**
 * Class to handle common utilities
 *
 */
public class CommonUtils {
    /**
     * Attaches screenshot to the Cucumber report based on the configuration.
     *
     * @param scenario The Scenario object from Cucumber.
     */
    public static void attachScreenshotPerConfig(Scenario scenario) {
        Properties properties = ConfigReader.getProperties();
        System.out.print(properties.getProperty("screenshot"));
        if (properties.getProperty("screenshot").equals("only.fail")) {
            if (scenario.isFailed()) {
                logFailureToReport(scenario.getName() + " Failed...");
                attachScreenshot(getDriver());
            }
        } else if (properties.getProperty("screenshot").equals("only.pass")) {
            if (!scenario.isFailed()) {
                logSuccessToReport(scenario.getName() + " Passed...");
                attachScreenshot(getDriver());
            }
        } else {
            Log.info("Taking Screenshot.");
            attachScreenshot(getDriver());
        }
    }
    
    public enum STATUS {
        /**
         * Represents status PASS.
         */
        PASS("PASS"),

        /**
         * Represents status FAIL.
         */
        FAIL("FAIL");

        /**
         * The status string.
         */
        public final String status;

        /**
         * Constructs a STATUS enum with the given status string.
         *
         * @param status
         *            the status string
         */
        STATUS(String status) {
          this.status = status;
        }
      }
    
    public static void waitFor(Duration duration) {
        try {
          Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
          Log.error("Error while applying wait...", e);
        }
        Log.info(duration.toSeconds() + " seconds of wait completed...");
      }
    
}
