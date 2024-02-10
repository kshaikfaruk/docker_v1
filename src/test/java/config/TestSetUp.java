package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSetUp {
	public static WebDriver driver;
	public static TakesScreenshot ts;
	public  static JavascriptExecutor js;
	public static Properties ps;
	public ExtentHtmlReporter htmlreporter;
	 public ExtentReports extent;
	 public ExtentTest test;
	 ITestResult Result;
	 public static ThreadLocal<WebDriver> threadLocalVariable = new ThreadLocal<>();
    @BeforeSuite
	public void Suite() {
    	
    	Date d= new Date();
    String reportName="localRun"+d.getDate()+" "+d.getMonth()+"_"+d.getYear()+"_"+d.getHours()+"_"+d.getSeconds();
    	htmlreporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/"+reportName+".html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setReportName("Automation test results");
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		
	
		
		
	}
    @BeforeMethod
    public void openBrowser() throws IOException {
    	test=extent.createTest("test");
    	driver=openApplication(getProperties("browser"));
    }
    
    @AfterMethod
    public void teardown(ITestResult results) {
    	if(results.getStatus()==ITestResult.FAILURE) {
    		String methodName=results.getMethod().getMethodName();
    		String excepionMessage=Arrays.toString(results.getThrowable().getStackTrace());
    		String logtext="<b> + Testcase-failed</b>"+results.getMethod()+"<details>message:"+excepionMessage+"</details></b>";
    		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.RED);
    		test.fail(m);
    	}else if(results.getStatus()==ITestResult.SUCCESS) {
    		String methodName=results.getMethod().getMethodName();
    		String logtext="<b> + Testcase-Passed</b>";
    		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
    		test.pass(m);
    	}else if(results.getStatus()==ITestResult.SKIP) {
    		String methodName=results.getMethod().getMethodName();
    		String logtext="<b> + Testcase-skipped</b>";
    		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.GREY);
    		test.skip(m);
    	}
    	
    }
    
    @AfterSuite
    public void closeApplication() {
    	extent.flush();
    	if(driver!=null) {
    		driver.quit();
    	}
    }
   
    public String getProperties(String s) throws IOException {
    	File f= new File(System.getProperty("user.dir")+"/config.properties");
    	FileInputStream fis= new FileInputStream(f);
    	ps= new Properties();
    	ps.load(fis);
    	String s1=ps.getProperty(s);
    	return s1;
    }
    
    public WebDriver openApplication(String s2) {
    	switch(s2) {
    	case "chrome":
    		WebDriverManager.chromedriver().setup();
        	driver=new ChromeDriver();
        	break;
    	case "firefox":
    		WebDriverManager.firefoxdriver().setup();
        	driver=new FirefoxDriver();
        	break; 	
        	
    	}
    	return driver;
    }
    public static  WebDriver getDriver() {
    	WebDriver localdriver;
    	localdriver=driver;
    	return localdriver;
    }
}
