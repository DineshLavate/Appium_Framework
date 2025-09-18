package Android_Automation.Utilities;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener{

	ExtentReports extent = ExtentReportManager.getReporter();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
            // Log failure
            test.get().fail("❌ Test Failed: " + result.getThrowable());
            String  screenshotPath = ScreenshotUtils.captureScreenshotOnFailure(result);
            // Attach screenshot to report
            test.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped ⚠️: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // generate the report
    }
    
}
