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

import config.DriverFactory;
import config.TestSetUp;

public class TechnicalComponents extends TestSetUp {
	public static WebDriverWait wait;
   public static WebDriver localdriver;
	public static void navigateTOUrl(String url) {
		localdriver= DriverFactory.getInstance().getDriver();
		localdriver.get(url);
	}
	public  static void type(String text,WebElement element) {
		 highlightElement( element);
	   element.sendKeys(text);
	}
	public  static void waitforWaitElement(int timeout) {
		localdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		}
	public  static void waitforWebElementtoload(Duration timeout,WebElement element) {
		  wait= new WebDriverWait(localdriver, timeout);
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
		localdriver.switchTo().frame(element);
		  
		}
	public  static void switchtoWindow(WebElement element) {
		  Set<String>session=localdriver.getWindowHandles();
		  Iterator<String> i=session.iterator();
		  String firstSession=localdriver.getWindowHandle();
		  String secondwindow;
		  while(i.hasNext()) {
			  secondwindow=i.next();
		  if(i.next()!=firstSession) {
			  localdriver.switchTo().window(secondwindow);
			  break;
		  }
		}
	}
	public static String takescreenshot() {
		String filepath="";
		try {
			ts=(TakesScreenshot) localdriver;
			File f=ts.getScreenshotAs(OutputType.FILE);
			Date d= new Date();
			String screenshotName=d.getDate()+" "+d.getMonth()+"_"+d.getYear()+"_"+d.getHours()+"_"+d.getSeconds();
			filepath=System.getProperty("user.dir")+"/reports/screenshot/"+screenshotName+".jpg";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepath;
	}
	 public static void highlightElement(WebElement element) {
		 js=(JavascriptExecutor)localdriver;
			js.executeScript("arguments[0].style.border='red 2px solid';", element);
	 }
	 public  static String gettext(WebElement element) {
	    String text=element.getText();
	     return text;
		}
}


