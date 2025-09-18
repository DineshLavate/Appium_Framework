package Android_Automation.Android_config;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

import Android_Automation.Utilities.Logs;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumSessionManager {

	 public static AndroidDriver driver;

	    /**
	     * Start an Android session dynamically
	     *
	     * @param deviceName         Name of the device/emulator (e.g., "Pixel_6_API_34" or real device name)
	     * @param udid               Device UDID (for real devices; pass null if emulator)
	     * @param platformVersion    Android version (e.g., "14.0")
	     * @param automationName     Automation engine (usually "UiAutomator2")
	     * @param browserName        For web testing ("Chrome"), or null if testing native app
	     * @param appPath            Full path to .apk file, or null if not testing app
	     * @param appPackage         Package name (for existing installed app), or null
	     * @param appActivity        Activity name (for existing installed app), or null
	     * @return AndroidDriver instance
	     */
	
	 public static AndroidDriver startAndroidSession(
	            String deviceName,
	            String udid,
	            String platformVersion,
	            String automationName,
	            String browserName,
	            String appPath,
	            String appPackage,
	            String appActivity,
	            String platformName,
	            String browserstackUser,
	            String browserstackKey,
	            String browserstackDevice,
	            String browserstackOSVersion,
	            String projectName,
	            String buildName,
	            String testName
	    ) throws MalformedURLException {

	        if (browserstackUser != null && !browserstackUser.isEmpty()
	                && browserstackKey != null && !browserstackKey.isEmpty()) {

	            // ✅ Run on BrowserStack
	            DesiredCapabilities caps = new DesiredCapabilities();

	            // BrowserStack credentials
	            caps.setCapability("browserstack.user", browserstackUser);
	            caps.setCapability("browserstack.key", browserstackKey);

	            // Device & OS
	            caps.setCapability("device", browserstackDevice != null ? browserstackDevice : "Google Pixel 7");
	            caps.setCapability("os_version", browserstackOSVersion != null ? browserstackOSVersion : "13.0");
	            caps.setCapability("platformName", "Android");

	            // Meta info
	            caps.setCapability("project", projectName != null ? projectName : "My Project");
	            caps.setCapability("build", buildName != null ? buildName : "My Build");
	            caps.setCapability("name", testName != null ? testName : "My Test");

	            // Browser OR App
	            if (browserName != null && !browserName.equalsIgnoreCase("null") && !browserName.isEmpty()) {
	                caps.setCapability("browserName", "Chrome");
	            } else if (appPath != null && !appPath.equalsIgnoreCase("null") && !appPath.isEmpty()) {
	                caps.setCapability("app", appPath); // upload via REST API, pass app_url here
	            } else if (appPackage != null && appActivity != null
	                    && !appPackage.equalsIgnoreCase("null") && !appActivity.equalsIgnoreCase("null")) {
	                caps.setCapability("appPackage", appPackage);
	                caps.setCapability("appActivity", appActivity);
	            }

	            driver = new AndroidDriver(
	                    new URL("http://hub.browserstack.com/wd/hub"), caps
	            );

	            System.out.println("✅ Android session started on BrowserStack device: " + browserstackDevice);
	        } else {
	            // ✅ Run locally
	            UiAutomator2Options options = new UiAutomator2Options();
	            options.setDeviceName(deviceName);
	            options.setPlatformName(platformName);
				options.setCapability("uiautomator2ServerInstallTimeout", 120000);
                options.setCapability("adbExecTimeout", 120000);
                options.setCapability("androidInstallTimeout", 300000);           // 5 min

	            if (udid != null && !udid.equalsIgnoreCase("null") && !udid.isEmpty()) {
	                options.setUdid(udid);
	            }
	            if (platformVersion != null && !platformVersion.equalsIgnoreCase("null") && !platformVersion.isEmpty()) {
	                options.setPlatformVersion(platformVersion);
	            }
	            options.setAutomationName(automationName != null ? automationName : "UiAutomator2");

	            if (browserName != null && !browserName.equalsIgnoreCase("null") && !browserName.isEmpty()) {
	                options.setCapability("browserName", "Chrome");
	                String driverpath = System.getProperty("user.dir") + "/AndroidBuilds/chromedriver.exe";
	                options.setChromedriverExecutable(driverpath);
	            } else if (appPath != null && !appPath.equalsIgnoreCase("null") && !appPath.isEmpty()) {
	            	String app_path = System.getProperty("user.dir") + appPath;
	                options.setApp(app_path);
	                options.setCapability("appPackage", "com.swaglabsmobileapp");
                    options.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
                    options.setCapability("appWaitActivity", "com.swaglabsmobileapp.*");
	                System.out.println("📦 Taking app from path: " + app_path);
	            } else if (appPackage != null && appActivity != null
	                    && !appPackage.equalsIgnoreCase("null") && !appActivity.equalsIgnoreCase("null")) {
	                options.setAppPackage(appPackage);
	                options.setAppActivity(appActivity);
	                System.out.println("📦 Starting session with App Package & Activity");
	            }

	            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
	            System.out.println("✅ Android session started locally on device: " + deviceName);
	            Logs.info("✅ Android session started locally on device: " + deviceName);
	        }

	        return driver;
	    }

	    @AfterTest
	    public static void stopAndroidSession() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("🛑 Android session stopped.");
	            Logs.info("🛑 Android session stopped.");
	        }
	    }
}
