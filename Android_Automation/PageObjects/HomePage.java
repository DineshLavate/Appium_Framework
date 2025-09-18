package PageObjects;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;

public class HomePage {

	public static final By filter_button = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView");
	public static final By Add_toCart_button = AppiumBy.accessibilityId("test-ADD TO CART");
	public static final By Cart_View = By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.ImageView");
	public static final By price_onPrpage = By.xpath("//android.widget.TextView[@content-desc=\"test-Price\"]");
	public static final By price_onCartpage = By.xpath("//android.widget.TextView[0]");
	public static final By check_outButton = By.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]");
	public static final By hamberge_menue = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)");
	public static final By Log_outButton = AppiumBy.accessibilityId("test-LOGOUT");
	
	public static By SelectOptionWithVisibleText(String text) {
		  final By textXpath  = By.xpath("//android.widget.TextView[@text=\""+text+"\"]");
	   	  return textXpath;
	   	
	}
	
	public static By ElementWith_VisibleText(String text) {
		  final By textXpath  = By.xpath("//android.widget.TextView[@text=\""+text+"\"]");
	   	  return textXpath;
	   	
	}
	
	
	
	
	
	

}
