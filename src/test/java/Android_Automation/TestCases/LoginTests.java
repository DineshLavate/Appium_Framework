package Android_Automation.TestCases;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Android_Automation.Android_config.*;
import Android_Automation.PageObjects.*;
import Android_Automation.Utilities.*;
import io.appium.java_client.android.AndroidDriver;

public class LoginTests extends AppiumSessionManager{
	public static AndroidDriver driver;
	
	@BeforeTest
	public static void StartAndroid_Session() throws MalformedURLException, InterruptedException {
	String deviceName = System.getProperty("deviceName", Utils.getFileProperty("deviceName"));
	String udid = Utils.getFileProperty("udid");
	String platformVersion = Utils.getFileProperty("platformVersion");
	String automationName = Utils.getFileProperty("automationName");
	String browserName = Utils.getFileProperty("browserName");
	String appPath = Utils.getFileProperty("appPath");
	String appPackage = Utils.getFileProperty("appPackage");
	String appActivity = Utils.getFileProperty("appActivity");
	
	driver = AppiumSessionManager.startAndroidSession(deviceName,udid, platformVersion, automationName, browserName, appPath, null,null,"Android",null,null,null,null,null,null,null);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@Test
	public void Add_productToCart() throws Exception {
		
	Thread.sleep(5000);
	Gesture_Utils.waitForElement(LogInPage.App_NameText, 10);
	boolean LogoName = Gesture_Utils.isDisplayed(LogInPage.App_NameText);
	Assert.assertEquals(LogoName, true);
	
	Gesture_Utils.tapElement(LogInPage.Standard_userlink);
	Gesture_Utils.tapElement(LogInPage.LogIn_button);
	Gesture_Utils.waitForElement(HomePage.filter_button, 5);
	Gesture_Utils.tapElement(HomePage.filter_button);
	Gesture_Utils.tapElement(HomePage.SelectOptionWithVisibleText("Price (low to high)"));
	Gesture_Utils.scrollToText("Sauce Labs Backpack");
	Gesture_Utils.tapElement(HomePage.SelectOptionWithVisibleText("Sauce Labs Backpack"));
    
	Thread.sleep(3000);
	String price_onproductpage = Gesture_Utils.getElementText(HomePage.price_onPrpage, "price");
	System.out.println("price on product page"+price_onproductpage);
	Gesture_Utils.waitForElement(HomePage.Add_toCart_button, 5);
	Gesture_Utils.tapElement(HomePage.Add_toCart_button);
	
	
	//Validate product is added to cart
	Gesture_Utils.tapElement(HomePage.Cart_View);
	Thread.sleep(3000);
	boolean Added_productName = Gesture_Utils.isDisplayed(HomePage.ElementWith_VisibleText("Sauce Labs Backpack"));
	Assert.assertEquals(Added_productName,true);

	
	Thread.sleep(5000);
	String price_onCartpage = Gesture_Utils.getElementText(HomePage.ElementWith_VisibleText(price_onproductpage), "price_Cartpage");
	System.out.println("Text price"+price_onCartpage);
	Assert.assertEquals(price_onproductpage, price_onCartpage);
	
	//checkout the product
	Gesture_Utils.waitForElement(HomePage.check_outButton, 5);
	Gesture_Utils.tapElement(HomePage.check_outButton);
	
	Thread.sleep(3000);
	Gesture_Utils.waitForElement(CheckoutPage.CheckoutPage_Title, 5);
	boolean flag = Gesture_Utils.isDisplayed(CheckoutPage.CheckoutPage_Title);
	System.out.println(flag);
	Gesture_Utils.typeinTextBox(CheckoutPage.First_nameInputBox, "Dinesh", "FirstName Text box");
	Gesture_Utils.typeinTextBox(CheckoutPage.Last_nameInputBox, "Lavate", "LastName Text box");
	Gesture_Utils.typeinTextBox(CheckoutPage.ZipCode_InputBox, "411044", "ZipCode Text box");
	Gesture_Utils.tapElement(CheckoutPage.Continue_button);
	
	Thread.sleep(3000);
	Gesture_Utils.scrollToText("FINISH");
	Gesture_Utils.tapElement(CheckoutPage.finish_button);

	Thread.sleep(3000);
	String Success_message = Gesture_Utils.getElementText(CheckoutPage.Success_message,"Success Message");
	System.out.println(Success_message);
	Assert.assertEquals(Success_message, "CHECKOUT: COMPLETE!");
	
	String actual_OrderDes = Gesture_Utils.getElementText(CheckoutPage.Success_desc,"Success Description");
	System.out.println(actual_OrderDes);
	Assert.assertEquals(actual_OrderDes, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
	
	Thread.sleep(2000);
	Gesture_Utils.waitForElement(CheckoutPage.BackTo_homebutton, 5);
	Gesture_Utils.tapElement(CheckoutPage.BackTo_homebutton);
	
	Thread.sleep(5000);
	Gesture_Utils.waitForElement(HomePage.hamberge_menue, 5);
	Gesture_Utils.tapElement(HomePage.hamberge_menue);
	
	Gesture_Utils.waitForElement(HomePage.Log_outButton, 5);
	Gesture_Utils.tapElement(HomePage.Log_outButton);
	
	Gesture_Utils.waitForElement(LogInPage.Login_pageView, 5);
	Assert.assertTrue(Gesture_Utils.isDisplayed(LogInPage.Login_pageView));
		
	}
	

}
