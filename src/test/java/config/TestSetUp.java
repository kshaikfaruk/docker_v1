package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import responseValidation.CreateUserresponse;

public class TestSetUp {
	public static WebDriver driver;
	public static TakesScreenshot ts;
	public  static JavascriptExecutor js;
	public static Properties ps;
	public ExtentSparkReporter htmlreporter;
	 public ExtentReports extent;
	 public ExtentTest test;
	 ITestResult Result;
	 DesiredCapabilities ds;
	 public static RequestSpecification rs;
		public static Response resp;
 public static  ThreadLocal<WebDriver> threadLocalVariable = new ThreadLocal<>();
    @BeforeSuite
	public void Suite() {
    	Date d= new Date();
     String reportName="localRun"+d.getDate()+" "+d.getMonth()+"_"+d.getYear()+"_"+d.getHours()+"_"+d.getSeconds();
     htmlreporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+reportName+".html");
    	//htmlreporter= new ExtentReporter(System.getProperty("user.dir")+"/reports/"+reportName+".html");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setReportName("Automation test results");
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		
	}
    @BeforeMethod
    public void openBrowser(Method method) throws IOException {	
    	  test=  extent.createTest(method.getName());
  	    ExtentFactory.getInstance().setExtent(test);
    	driver=openApplication(getProperties("browser"));
    }
    
    @AfterMethod
    public void teardown(ITestResult results) {
    	if(results.getStatus()==ITestResult.FAILURE) {
    		String methodName=results.getMethod().getMethodName();
    		String excepionMessage=Arrays.toString(results.getThrowable().getStackTrace());
    		String logtext="<b> + Testcase-failed</b>"+results.getMethod()+"<details>message:"+excepionMessage+"</details></b>";
    		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.RED);
    		ExtentFactory.getInstance().getExtent().fail(m);
    	}else if(results.getStatus()==ITestResult.SUCCESS) {
    		String methodName=results.getMethod().getMethodName();
    		String logtext="<b> + Testcase-Passed</b>";
    		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
    		ExtentFactory.getInstance().getExtent().pass(m);                  
    	}else if(results.getStatus()==ITestResult.SKIP) {
    		String methodName=results.getMethod().getMethodName();
    		String logtext="<b> + Testcase-skipped</b>";
    		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.GREY);
    		ExtentFactory.getInstance().getExtent().skip(m);
    	}
    	if( DriverFactory.getInstance().getDriver()!=null) {
    		DriverFactory.getInstance().closeBrowser();
    	}
    	
    }
    
    @AfterSuite
    public void closeApplication() {
    	extent.flush();
    
    }
   
    public String getProperties(String s) throws IOException {
    	File f= new File(System.getProperty("user.dir")+"/config.properties");
    	FileInputStream fis= new FileInputStream(f);
    	ps= new Properties();
    	ps.load(fis);
    	String s1=ps.getProperty(s);
    	return s1;
    }
    
    public WebDriver openApplication(String s2) throws MalformedURLException {
    	switch(s2) {
    	case "chrome":
    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe"); 
    		ChromeOptions co = new ChromeOptions();
            co.addArguments("--remote-allow-origins=*");
            co.addArguments("--disable-dev-shm-usage");
            driver=new ChromeDriver(co);
            DriverFactory.getInstance().setDriver(driver);
        	break;
    	case "firefox":
    		WebDriverManager.firefoxdriver().setup();
        	driver=new FirefoxDriver();
        	break; 	
    	case "andorid":
    		ds= new DesiredCapabilities();
    		 ds.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
    		 ds.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");
    		 ds.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
    		 ds.setCapability(MobileCapabilityType.APP, "E:\\mobiletesting\\Mobile_testing_v1\\app\\General-Store.apk");
    		 ds.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
    		 driver= new RemoteWebDriver(new URL("https://0.0.0.0:4723/wd/hub"),ds);
    	    break;
    	case "api": 
    		System.out.println("api test");
    		break;
    	default:
    		System.out.println("browser not found");
    	}
    	return DriverFactory.getInstance().getDriver();
    }
	 public  static void givenPayload(String baseurl,String playload, String pathparam ) {
		  rs= RestAssured.given().header("contentType","application/json").header("pathParam", pathparam).baseUri(baseurl).body(playload);    
			}
	 
	 public  static Response hiturl(String method,String endpotinturl) {
		    if(method.equals("get")) {
		    	 resp =rs.when().get(endpotinturl).then().extract().response();
		    	 
			}else if(method.equals("post")) {
				resp =rs.when().post(endpotinturl).then().extract().response();
			}
		    return resp;
}
	 public static  CreateUserresponse Verifythe_response(Response respo, String element) {
		   Assert.assertEquals(respo.statusCode(), 201);
		   CreateUserresponse s=respo.as(CreateUserresponse.class);
//		   JsonPath js= new JsonPath(s);
//		  String s1= js.get(element);
//		  int i=Integer.parseInt(s1);
//		  Assert.assertEquals(i, 230);
//		  
return s;
}


    
}
