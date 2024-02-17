package testScripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class Verify_mobileapllication_lunched {
    @Test
	public void verifyUserLunched() throws MalformedURLException {
    	UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("emulator-5554");
		options.setApp(System.getProperty("user.dir")+"\\app\\ApiDemos-debug.apk");	
		options.setAdbExecTimeout(Duration.ofSeconds(90000));
       AndroidDriver driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Preference']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebElement ele=	driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']"));
	ele.click();
	//	TouchAction s= new TouchAction(driver);
//	    s.tap(TapOptions.tapOptions().withElement(ElementOption.element(element1))).perform();
//	    ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
//	    		ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
//	    		"duration",2000));
////
		
	}
}
	  