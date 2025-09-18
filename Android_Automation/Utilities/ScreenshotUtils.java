package Utilities;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

import Android_config.AppiumSessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;

public class ScreenshotUtils extends AppiumSessionManager{

	public static String captureScreenshot(AndroidDriver driver, String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timeStamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            Files.copy(src, dest);
            System.out.println("✅ Screenshot captured: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
	
	
	 public static String captureScreenshotOnFailure(ITestResult result) {
		 String screenshotPath=null;
	        if (result.getStatus() == ITestResult.FAILURE) {
	            String testName = result.getName();
	            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timeStamp + ".png";

	            try {
	                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	                File dest = new File(screenshotPath);
	                Files.copy(src, dest);

	                System.out.println("✅ Screenshot captured: " + screenshotPath);
	            } catch (IOException e) {
	                System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
	            }
	        }
	        return screenshotPath;
	    }
	 
}
