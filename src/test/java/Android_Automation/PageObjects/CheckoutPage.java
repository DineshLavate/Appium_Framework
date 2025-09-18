package Android_Automation.PageObjects;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;

public class CheckoutPage {

	public static final By CheckoutPage_Title = By.xpath("//android.widget.TextView[@text='CHECKOUT: INFORMATION']");
	public static final By First_nameInputBox = AppiumBy.accessibilityId("test-First Name");
	public static final By Last_nameInputBox = AppiumBy.accessibilityId("test-Last Name");
	public static final By ZipCode_InputBox = AppiumBy.accessibilityId("test-Zip/Postal Code");
    public static final By Continue_button = By.xpath("//android.widget.TextView[@text='CONTINUE']");
	public static final By finish_button = AppiumBy.accessibilityId("test-FINISH");
	public static final By Success_message = By.xpath("//android.widget.TextView[@index='0']");
	public static final By Success_desc = By.xpath("//android.widget.TextView[@index='1']");
    public static final By BackTo_homebutton = By.xpath("//android.widget.TextView[@text='BACK HOME']");
	
}
