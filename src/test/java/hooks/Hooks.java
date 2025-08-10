package hooks;

import io.cucumber.java.*;
import logger.Log;
import reporter.PluginReporter;

import java.io.IOException;
import java.util.HashSet;

import static engine.Engine.*;
import static utils.CucumberUtils.*;
import static utils.CommonUtils.attachScreenshotPerConfig;
import static utils.ResultManager.*;
import static reporter.PluginReporter.*;

/**
 * Hooks class containing setup and tear-down methods for test scenarios.
 *
 */
@SuppressWarnings("unused")
public class Hooks {

    static String featureName;
    static HashSet<String> features = new HashSet<>();
    public static ThreadLocal<Scenario> tlScenario = new ThreadLocal<>();

    /**
     * Method executed before all tests.
     *
     */
    @BeforeAll
    public static void beforeAll() {
    	initializeDriver();
    }

    /**
     * Method executed before each test.
     *
     * @param scenario The scenario being executed.
     */
    @Before
    public void beforeTest(Scenario scenario) {   	
    	
        String currentFeatureName = getFeatureNameFromScenario(scenario, false);
        if (!features.contains(currentFeatureName)) {
            featureName = currentFeatureName;
            features.add(featureName);

            // Initialize Result Map for Current Feature
            initializeResultCollector(featureName);
        }
        setCurrentScenario(scenario);
        logToReport(scenario.getName() + " Started...");
    }

    /**
     * Method executed after each test.
     *
     * @param scenario The scenario being executed.
     */
    @After
    public void afterTest(Scenario scenario) {
        // Update Result for Current Scenario
        updateResult(scenario, featureName);
        attachScreenshotPerConfig(scenario);
        
        String sessionId = getDriver().getSessionId().toString();
        String testName = scenario.getName();
      
        String testStatus = scenario.isFailed() ? "failed" : "passed";
        String errorMessage = ""; // TODO:     

        setTestInfo(sessionId, testName, testStatus, errorMessage,getAppiumServerUrl());
    }

    /**
     * Method executed after all tests.
     * @throws InterruptedException 
     * @throws IOException 
     *
     */
    @AfterAll
    public static void afterAll() throws IOException, InterruptedException {
        printResult();
        
        String report = PluginReporter.getReport(getAppiumServerUrl());
        PluginReporter.deleteReportData(getAppiumServerUrl());
        createReportFile(report, "report");
        
        quitDriver();
        stopAppiumServer();
        String home = System.getProperty("user.dir");
        Log.info("------------------------------------------------------");
        Log.info("Reports Generated...");
        Log.info("------------------------------------------------------");
        Log.info("Cucumber: " + home + "\\testReports\\CucumberReport.html");
        Log.info("Cucumber Enhanced: " + home + "\\testReports\\cucumber-html-reports\\overview-features.html");
        Log.info("Time Line: " + home + "\\testReports\\timelineReport\\index.html");
        Log.info("Extent HTML: " + home + "\\testReports\\ExtentReport.html");
        Log.info("Extent PDF: " + home + "\\testReports\\ExtentReport.pdf");
        Log.info("------------------------------------------------------");
        
    }
    
    
}