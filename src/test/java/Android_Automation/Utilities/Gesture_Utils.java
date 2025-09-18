package Android_Automation.Utilities;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Android_Automation.Android_config.AppiumSessionManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Gesture_Utils extends AppiumSessionManager {

	public static void double_tap(AndroidDriver driver, WebElement element) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		org.openqa.selenium.interactions.Sequence doubleTap = new org.openqa.selenium.interactions.Sequence(finger, 1);

		doubleTap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.fromElement(element), 0, 0));
		doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		doubleTap.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofMillis(100)));

		doubleTap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.fromElement(element), 0, 0));
		doubleTap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		doubleTap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(doubleTap));

	}

    public static void tapElement(By element) throws Exception {
        try {
            driver.findElement(element).click();
            System.out.println("✅ Tapped element successfully");
        } catch (Exception e) {
            System.err.println("❌ Failed to tap element: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void waitForElement(By element, long Itime) throws Exception {
    	try {
    		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Itime));
    		wait.until(ExpectedConditions.elementToBeClickable(element));
    		Logs.info("Element is clickable");
    	}catch (Exception e) {
    		ExceptionHandling.HandleExecption(e, "Element not available");
    	}
    }
    
    public static void typeinTextBox(By locator , String data,String elementName) throws Exception {
		try {
			if(driver.findElement(locator).isDisplayed()) {
				driver.findElement(locator).clear();
				driver.findElement(locator).sendKeys(data);
				Logs.info("Text Entered into "+elementName);
			}else {
				Logs.info("Unable to enter text into "+elementName);
			}
		}catch(Exception e) {
//			ExceptionHandling.HandleExecption(e, "Unable to Identify text field ");
			e.printStackTrace();
		}
	}
    
    public static String getElementText(By locator, String elementName) throws Exception {
		String sText="";
		try {
			if(driver.findElement(locator).isDisplayed()) {
				sText=driver.findElement(locator).getText();
				Logs.info("Text got from "+elementName);
			}else
			{
				Logs.info("Unable to get Text from "+elementName);
			}
			
		}catch(Exception e) {
            e.printStackTrace();

		}
		return sText;
	}
    
    public static boolean isDisplayed(By profileName) throws Exception {
		boolean flag = false;
		
		try {
			flag = driver.findElement(profileName).isDisplayed();
			Logs.info("elements is displayed");
		}catch(Exception e) {
            e.printStackTrace();
		}
		return flag;	
	}

    public static String getElementAttribute(By object, String AttributeName, String elementName) throws Exception {
		String sText = "";
		try {
			if(driver.findElement(object).isDisplayed()) {
				sText = driver.findElement(object).getAttribute(AttributeName);
				Logs.info("elements is getting attribute");
			}else {
				Logs.info("failed to get Text from the "+ elementName + "Element");
			}
		}catch(Exception e) {
			ExceptionHandling.HandleExecption(e, "Element Text not available");
		}
		return sText;
		
	}

    public void tapAtCoordinates(int x, int y) {
        try {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(tap));

            System.out.println("✅ Tapped at coordinates: " + x + "," + y);
        } catch (Exception e) {
            System.err.println("❌ Failed to tap at coordinates: " + e.getMessage());
        }
    }

    public void longPressElement(WebElement element, int seconds) {
        try {
            Point loc = element.getLocation();
            int x = loc.getX() + (element.getSize().getWidth() / 2);
            int y = loc.getY() + (element.getSize().getHeight() / 2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence longPress = new Sequence(finger, 1);
            longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
            longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            longPress.addAction(new Pause(finger, Duration.ofSeconds(seconds)));
            longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(longPress));

            System.out.println("✅ Long pressed element for " + seconds + "s");
        } catch (Exception e) {
            System.err.println("❌ Failed to long press: " + e.getMessage());
        }
    }

    public void swipe(int startX, int startY, int endX, int endY, int durationMs) {
        try {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMs),
                    PointerInput.Origin.viewport(), endX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(swipe));

            System.out.println("✅ Swiped from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
        } catch (Exception e) {
            System.err.println("❌ Swipe failed: " + e.getMessage());
        }
    }

    public static void scrollToText(String visibleText) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"" + visibleText + "\"))"));
            System.out.println("✅ Scrolled to text: " + visibleText);
        } catch (Exception e) {
            System.err.println("❌ Scroll failed: " + e.getMessage());
        }
    }

    public static void doubleTap(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Map<String, Object> params = new HashMap<>();
            params.put("elementId", ((RemoteWebElement) element).getId());
            params.put("tapCount", 2);
            js.executeScript("mobile: doubleClickGesture", params);
            System.out.println("✅ Double tap performed");
        } catch (Exception e) {
            System.err.println("❌ Double tap failed: " + e.getMessage());
        }
    }

    public static void pressBack() {
        try {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            System.out.println("✅ Pressed BACK key");
        } catch (Exception e) {
            System.err.println("❌ Failed to press BACK: " + e.getMessage());
        }
    }

    public static void pressHome() {
        try {
            driver.pressKey(new KeyEvent(AndroidKey.HOME));
            System.out.println("✅ Pressed HOME key");
        } catch (Exception e) {
            System.err.println("❌ Failed to press HOME: " + e.getMessage());
        }
    }

    public static void pressEnter() {
        try {
            driver.pressKey(new KeyEvent(AndroidKey.ENTER));
            System.out.println("✅ Pressed ENTER key");
        } catch (Exception e) {
            System.err.println("❌ Failed to press ENTER: " + e.getMessage());
        }
    }

    public static void pinch(WebElement element) {
        try {
            Point loc = element.getLocation();
            int centerX = loc.getX() + (element.getSize().getWidth() / 2);
            int centerY = loc.getY() + (element.getSize().getHeight() / 2);

            // First finger (move outward up)
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence seq1 = new Sequence(finger1, 1);
            seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
            seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            seq1.addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
                    centerX, centerY - 200));
            seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Second finger (move outward down)
            PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
            Sequence seq2 = new Sequence(finger2, 1);
            seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
            seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            seq2.addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
                    centerX, centerY + 200));
            seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(seq1, seq2));
            System.out.println("✅ Pinch (Zoom Out) performed");
        } catch (Exception e) {
            System.err.println("❌ Pinch failed: " + e.getMessage());
        }
    }

    public static void zoom(WebElement element) {
        try {
            Point loc = element.getLocation();
            int centerX = loc.getX() + (element.getSize().getWidth() / 2);
            int centerY = loc.getY() + (element.getSize().getHeight() / 2);

            // First finger (move inward from top)
            PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence seq1 = new Sequence(finger1, 1);
            seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),
                    centerX, centerY - 200));
            seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            seq1.addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
                    centerX, centerY));
            seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Second finger (move inward from bottom)
            PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
            Sequence seq2 = new Sequence(finger2, 1);
            seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),
                    centerX, centerY + 200));
            seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            seq2.addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
                    centerX, centerY));
            seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(seq1, seq2));
            System.out.println("✅ Zoom (Zoom In) performed");
        } catch (Exception e) {
            System.err.println("❌ Zoom failed: " + e.getMessage());
        }
    }
    
}
