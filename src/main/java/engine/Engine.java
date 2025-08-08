package engine;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import logger.Log;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import static utils.ConfigReader.*;
import static utils.CommonUtils.*;

/**
 * Class to handle Appium Driver Initialization, termination and other driver management utils.
 * 
 */
public class Engine {
    static ThreadLocal<AppiumDriver> tlDriver = new ThreadLocal<>();
    private static AppiumDriverLocalService service;
    private static final String platform = getProperties().getProperty("platform").trim().toLowerCase();
    private static final String executionType = getProperties().getProperty("execution.type").toLowerCase();
    private static final String appPackage = isAndroid() ?
            getAndroidProperties().getProperty("app.package") : getIosProperties().getProperty("app.package");
    private static URL appiumserverUrl;
    
    /**
     * Check if the platform is Android.
     *
     * @return true if platform is Android, false otherwise.    
     */
    public static boolean isAndroid() {
        return platform.equals("android");
    }

    /**
     * Check if the execution is to be done on local
     *
     * @return true if local execution, false otherwise
     */
    public static boolean isLocal() {
        return executionType.equals("local");
    }   
    

    /**
     * Start the Appium server.
     *
     * @return URL of the started Appium server.    
     */
    public static URL startAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress(getProperties().getProperty("appium.server.url.local"))
                .usingAnyFreePort();
               // .withArgument(() -> "--use-plugins", "appium-reporter-plugin");

        // Start the Appium server
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        service.clearOutPutStreams();
        appiumserverUrl = service.getUrl();
        Log.info("Appium Server started at: " + appiumserverUrl);
        return appiumserverUrl;
    }

    /**
     * Stop the Appium server if it is running.
     *
     */
    public static void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            Log.info("Appium server stopped.");
        }
    }

    /**
     * Initialize the Appium driver based on the platform and execution type.
     *  
     */
    public static void initializeDriver() {
        Properties properties = getProperties();
        AppiumDriver driver;
        URL appiumServerURL = isLocal() ? startAppiumServer() : frameUrl(properties.getProperty("appium.server.url.remote"));

        assert appiumServerURL != null;

        if (isAndroid()) {
            driver = isLocal() ?
                    new AndroidDriver(appiumServerURL, getAndroidDesiredCapabilities()) :
                    new AndroidDriver(appiumServerURL, getAndroidRemoteDesiredCapabilities());
        } else {
            driver = isLocal() ?
                    new AndroidDriver(appiumServerURL, getIosDesiredCapabilities()) :
                    new AndroidDriver(appiumServerURL, getIosRemoteDesiredCapabilities());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(properties.getProperty("implicit.wait"))));
        Log.info("Driver Started....");
        tlDriver.set(driver);
    }

    /**
     * Get the current Appium driver.
     *
     * @return the current Appium driver.
     */
    public static AppiumDriver getDriver() {
        return tlDriver.get();
    }
    
    public static URL getAppiumServerUrl() {
        return appiumserverUrl;
    }

    /**
     * Quit the current Appium driver if it is not null.
     * Terminates the app associated with the driver.
     * Waits for a short duration before quitting the driver.
     * Logs information when the app is terminated and driver is quit.
     *  
     */
    public static void quitDriver() {
        AppiumDriver driver = tlDriver.get();
        if (driver != null) {
            try {
                ((AndroidDriver) driver).terminateApp(appPackage);
            } catch (WebDriverException e) {
                waitFor(Duration.ofSeconds(2));
                ((AndroidDriver) driver).terminateApp(appPackage);
            }
            waitFor(Duration.ofSeconds(2));
            driver.quit();
            Log.info("App Terminated...");
        }
    }

    /**
     * Frame a URL from the provided string.
     *
     * @param url the string representation of the URL.
     * @return the URL object parsed from the string. 
     */
    public static URL frameUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            Log.error("Incorrect URL... Please check and try again...", e);
            return null;
        }
    }

    /**
     * Get Android desired capabilities.
     *
     * @return DesiredCapabilities object for Android. 
     */
    private static DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Properties androidProperties = getAndroidProperties();
        capabilities.setCapability("deviceName", androidProperties.getProperty("device.name")); // Use the device name from adb devices
        capabilities.setCapability("platformName", getProperties().getProperty("platform"));
        capabilities.setCapability("platformVersion", androidProperties.getProperty("platform.version"));
        capabilities.setCapability("automationName", "uiAutomator2");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", androidProperties.getProperty("app.activity"));
        capabilities.setCapability("app", androidProperties.getProperty("app"));
        capabilities.setCapability("noReset", Boolean.parseBoolean(androidProperties.getProperty("no.reset"))); // Do not reset app state before this session
        capabilities.setCapability("unicodeKeyboard", Boolean.parseBoolean(androidProperties.getProperty("unicode.keyboard")));
        capabilities.setCapability("resetKeyboard", Boolean.parseBoolean(androidProperties.getProperty("hide.keyboard")));
        capabilities.setCapability("sessionCreationRetry", androidProperties.getProperty("session.creation.retry"));
        capabilities.setCapability("sessionCreationTimeout", androidProperties.getProperty("session.creation.timeout"));
        capabilities.setCapability("appWaitDuration", androidProperties.getProperty("app.wait.timeout"));
        capabilities.setCapability("newCommandTimeout", androidProperties.getProperty("new.command.timeout"));
        capabilities.setCapability("autoGrantPermissions", Boolean.parseBoolean(androidProperties.getProperty("auto.grant.permissions"))); // Automatically grant permissions
        capabilities.setCapability("autoAcceptAlerts", Boolean.parseBoolean(androidProperties.getProperty("auto.accept.alerts"))); // Automatically accept alerts
        capabilities.setCapability("locationServicesAuthorized", Boolean.parseBoolean(androidProperties.getProperty("location.services")));
        capabilities.setCapability("disableWindowAnimation", Boolean.parseBoolean(androidProperties.getProperty("disable.window.animation"))); // Disable window animations for faster testing
        capabilities.setCapability("disableAndroidWatchers", Boolean.parseBoolean(androidProperties.getProperty("disable.android.watchers"))); // Disable Android system event watchers
        capabilities.setCapability("ignoreUnimportantViews", Boolean.parseBoolean(androidProperties.getProperty("ignore.unimportant.views"))); // Ignore unimportant views to improve speed
        capabilities.setCapability("disableNotifications", Boolean.parseBoolean(androidProperties.getProperty("disable.notifications"))); // Disable notifications during test
        
     // 🚀 Auto-start emulator
        capabilities.setCapability("avd", androidProperties.getProperty("avd.name")); // e.g., Pixel_6_API_34
        capabilities.setCapability("avdLaunchTimeout", 500000);
        capabilities.setCapability("avdReadyTimeout", 500000);
        return capabilities;
    }

    /**
     * Get iOS desired capabilities.
     *
     * @return DesiredCapabilities object for iOS.
     */
    private static DesiredCapabilities getIosDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Properties iosProperties = getIosProperties();
        capabilities.setCapability("deviceName", iosProperties.getProperty("device.name")); // Use the device name from adb devices
        capabilities.setCapability("platformName", getProperties().getProperty("platform"));
        capabilities.setCapability("platformVersion", iosProperties.getProperty("platform.version"));
        capabilities.setCapability("automationName", "xcuitest");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", iosProperties.getProperty("app.activity"));
        capabilities.setCapability("app", iosProperties.getProperty("app"));
        capabilities.setCapability("noReset", Boolean.parseBoolean(iosProperties.getProperty("no.reset"))); // Do not reset app state before this session
        capabilities.setCapability("unicodeKeyboard", Boolean.parseBoolean(iosProperties.getProperty("unicode.keyboard")));
        capabilities.setCapability("resetKeyboard", Boolean.parseBoolean(iosProperties.getProperty("hide.keyboard")));
        capabilities.setCapability("sessionCreationRetry", iosProperties.getProperty("session.creation.retry"));
        capabilities.setCapability("sessionCreationTimeout", iosProperties.getProperty("session.creation.timeout"));
        capabilities.setCapability("appWaitDuration", iosProperties.getProperty("app.wait.timeout"));
        capabilities.setCapability("newCommandTimeout", iosProperties.getProperty("new.command.timeout"));
        capabilities.setCapability("autoGrantPermissions", Boolean.parseBoolean(iosProperties.getProperty("auto.grant.permissions"))); // Automatically grant permissions
        capabilities.setCapability("autoAcceptAlerts", Boolean.parseBoolean(iosProperties.getProperty("auto.accept.alerts"))); // Automatically accept alerts
        capabilities.setCapability("locationServicesAuthorized", Boolean.parseBoolean(iosProperties.getProperty("location.services")));
        capabilities.setCapability("disableWindowAnimation", Boolean.parseBoolean(iosProperties.getProperty("disable.window.animation"))); // Disable window animations for faster testing
        capabilities.setCapability("disableAndroidWatchers", Boolean.parseBoolean(iosProperties.getProperty("disable.android.watchers"))); // Disable Android system event watchers
        capabilities.setCapability("ignoreUnimportantViews", Boolean.parseBoolean(iosProperties.getProperty("ignore.unimportant.views"))); // Ignore unimportant views to improve speed
        capabilities.setCapability("disableNotifications", Boolean.parseBoolean(iosProperties.getProperty("disable.notifications"))); // Disable notifications during test
        return capabilities;
    }

    /**
     * Get Android remote desired capabilities.
     *
     * @return MutableCapabilities object for Android remote execution.
     */
    private static MutableCapabilities getAndroidRemoteDesiredCapabilities() {
        Properties androidProperties = getAndroidProperties();
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", getProperties().getProperty("platform"));
        String appName = androidProperties.getProperty("app.name");
        String appValue = appName.contains("apk") ? "storage:filename=" + appName : "storage:" + appName;
        caps.setCapability("app", appValue); // The filename of the mobile app
        //caps.setCapability("deviceName", androidProperties.getProperty("sauce.device.name"));
        caps.setCapability("platformVersion", androidProperties.getProperty("sauce.platform.version"));
        caps.setCapability("automationName", "uiAutomator2");
        //caps.setCapability("unicodeKeyboard", Boolean.parseBoolean(androidProperties.getProperty("unicode.keyboard")));
        //caps.setCapability("resetKeyboard", Boolean.parseBoolean(androidProperties.getProperty("hide.keyboard")));
        caps.setCapability("sessionCreationRetry", androidProperties.getProperty("session.creation.retry"));
        caps.setCapability("sessionCreationTimeout", androidProperties.getProperty("session.creation.timeout"));
        caps.setCapability("appWaitDuration", androidProperties.getProperty("app.wait.timeout"));
        caps.setCapability("newCommandTimeout", androidProperties.getProperty("new.command.timeout"));
        caps.setCapability("sauceLabsImageInjectionEnabled", true);
        caps.setCapability("--session-override", true);
        caps.setCapability("autoGrantPermissions", Boolean.parseBoolean(androidProperties.getProperty("auto.grant.permissions")));
        caps.setCapability("androidDeviceReadyTimeout", androidProperties.getProperty("device.ready.timeout"));
        caps.setCapability("avdLaunchTimeout", androidProperties.getProperty("avd.launch.timeout"));
        caps.setCapability("avdReadyTimeout", androidProperties.getProperty("avd.launch.timeout"));
        caps.setCapability("sauce:options", getSauceOptions());
        return caps;
    }

    /**
     * Get iOS remote desired capabilities.
     *
     * @return MutableCapabilities object for iOS remote execution. 
     */
    private static MutableCapabilities getIosRemoteDesiredCapabilities() {
        Properties iosProperties = getIosProperties();
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", getProperties().getProperty("platform"));
        String appName = iosProperties.getProperty("app.name");
        String appValue = appName.contains("apk") ? "storage:filename=" + appName : "storage:" + appName;
        caps.setCapability("app", appValue);
        caps.setCapability("app", appValue); // The filename of the mobile app
        caps.setCapability("deviceName", iosProperties.getProperty("sauce.device.name"));
        caps.setCapability("platformVersion", iosProperties.getProperty("sauce.platform.version"));
        caps.setCapability("automationName", "xcuitest");
        caps.setCapability("unicodeKeyboard", Boolean.parseBoolean(iosProperties.getProperty("unicode.keyboard")));
        caps.setCapability("resetKeyboard", Boolean.parseBoolean(iosProperties.getProperty("hide.keyboard")));
        caps.setCapability("sessionCreationRetry", iosProperties.getProperty("session.creation.retry"));
        caps.setCapability("sessionCreationTimeout", iosProperties.getProperty("session.creation.timeout"));
        caps.setCapability("appWaitDuration", iosProperties.getProperty("app.wait.timeout"));
        caps.setCapability("newCommandTimeout", iosProperties.getProperty("new.command.timeout"));
        caps.setCapability("sauceLabsImageInjectionEnabled", true);
        caps.setCapability("--session-override", true);
        caps.setCapability("autoGrantPermissions", Boolean.parseBoolean(iosProperties.getProperty("auto.grant.permissions")));
        caps.setCapability("androidDeviceReadyTimeout", iosProperties.getProperty("device.ready.timeout"));
        caps.setCapability("avdLaunchTimeout", iosProperties.getProperty("avd.launch.timeout"));
        caps.setCapability("avdReadyTimeout", iosProperties.getProperty("avd.launch.timeout"));
        caps.setCapability("sauce:options", getSauceOptions());
        return caps;
    }

    /**
     * Get Sauce Labs options.
     *
     * @return MutableCapabilities object for Sauce Labs options. 
     */
    private static MutableCapabilities getSauceOptions() {
        Properties properties = getProperties();
        MutableCapabilities sauceOptions = new MutableCapabilities();
        //sauceOptions.setCapability("username", properties.getProperty("sauce.labs.username"));
        //sauceOptions.setCapability("accessKey", properties.getProperty("sauce.labs.access.key"));

        //sauceOptions.setCapability("build", properties.getProperty("sauce.labs.build"));
        sauceOptions.setCapability("build", properties.getProperty("app.under.test")+" v" + properties.getProperty("app.version"));

        sauceOptions.setCapability("name", properties.getProperty("sauce.labs.test.name"));

        sauceOptions.setCapability("deviceOrientation", properties.getProperty("sauce.labs.device.orientation"));
        return sauceOptions;
    }
    
    
}