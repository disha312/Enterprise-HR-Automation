package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void initializeReport() {

        ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter("test-output/ExtentReport.html");

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("Project", "Enterprise HR Automation");
        extentReports.setSystemInfo("Framework", "Playwright + TestNG");
        extentReports.setSystemInfo("Environment",
                System.getProperty("env", "test"));
    }

    public static void createTest(String testName) {
        extentTest = extentReports.createTest(testName);
    }

    public static void logPass(String message) {
        extentTest.pass(message);
    }

    public static void logFail(String message) {
        extentTest.fail(message);
    }

    public static void flushReport() {
        extentReports.flush();
    }

    public static ExtentReports getExtentReports() {
        return extentReports;
    }
}