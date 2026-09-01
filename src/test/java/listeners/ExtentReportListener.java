package listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ReportManager;

public class ExtentReportListener implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(org.testng.ITestContext context) {
        System.out.println(">>> EXTENT LISTENER onStart CALLED");
        ReportManager.initializeReport();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                ReportManager.getExtentReports()
                        .createTest(result.getMethod().getMethodName());

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip("Test skipped");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        System.out.println(">>> EXTENT LISTENER onFinish CALLED");
        ReportManager.flushReport();
    }
}