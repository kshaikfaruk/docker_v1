package resuablecomponents;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import config.TestSetUp;

public class TechnicalComponents extends TestSetUp {
	public static WebDriverWait wait;
	public static void navigateTOUrl(String url) {
		driver.get(url);
	}
	public  static void type(String text,WebElement element) {
		 highlightElement( element);
	   element.sendKeys(text);
	}
	public  static void waitforWaitElement(int timeout) {
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		}
	public  static void waitforWebElementtoload(Duration timeout,WebElement element) {
		  wait= new WebDriverWait(driver, timeout);
		  wait.until(ExpectedConditions.visibilityOf(element));
		}
	public  static void click(WebElement element) {
		 highlightElement( element);
		  element.click();
		}
	public  static void selectoptionforDropdown(WebElement element,String option) {
		  Select s= new Select(element);
		  s.selectByVisibleText(option);
		  
		}
	public  static void switchIframe(WebElement element) {
		  driver.switchTo().frame(element);
		  
		}
	public  static void switchtoWindow(WebElement element) {
		  Set<String>session=driver.getWindowHandles();
		  Iterator<String> i=session.iterator();
		  String firstSession=driver.getWindowHandle();
		  String secondwindow;
		  while(i.hasNext()) {
			  secondwindow=i.next();
		  if(i.next()!=firstSession) {
			  driver.switchTo().window(secondwindow);
			  break;
		  }
		}
	}
	public static String takescreenshot() {
		String filepath="";
		try {
			ts=(TakesScreenshot)driver;
			File f=ts.getScreenshotAs(OutputType.FILE);
			Date d= new Date();
			String screenshotName=d.getDate()+" "+d.getMonth()+"_"+d.getYear()+"_"+d.getHours()+"_"+d.getSeconds();
			filepath=System.getProperty("user.dir")+"/reports/screenshot/"+screenshotName+".jpg";
			Files.copy(f, new File(filepath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepath;
	}
	 public static void highlightElement(WebElement element) {
		 js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].style.border='red 2px solid';", element);
	 }
	 public  static String gettext(WebElement element) {
	    String text=element.getText();
	     return text;
		}
	
}




