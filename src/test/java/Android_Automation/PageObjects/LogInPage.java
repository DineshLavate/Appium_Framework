package Android_Automation.PageObjects;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;


public class LogInPage {

	public static final By Standard_userlink = By.xpath("//android.widget.TextView[@text=\"standard_user\"]");
	public static final By LogIn_button = AppiumBy.accessibilityId("test-LOGIN");
    public static final By App_NameText = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)");
	public static final By Login_pageView = AppiumBy.accessibilityId("test-Login");
}
