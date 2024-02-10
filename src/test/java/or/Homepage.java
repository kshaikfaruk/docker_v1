package or;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import resuablecomponents.TechnicalComponents;

public class Homepage extends TechnicalComponents {

	@FindBy(xpath="//input[@name='search']")
	public WebElement inputField;
	@FindBy(xpath="//button[@class='absolute inset-y-0 right-0 w-12 h-full flex items-center justify-center ']")
	public WebElement search_button;
	
	@FindBy(xpath="//div[@class='flex items-center']/span")
	public WebElement results;
	
public Homepage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
 public void type(WebElement element,String s) {
	 TechnicalComponents.type(s, element);
 }

 public void Click_searchButton(WebElement element) {
	 TechnicalComponents.click(element);
 }
 
 public void verifyResultsText(WebElement element) {
	TechnicalComponents.waitforWebElementtoload(Duration.ofSeconds(20), element);
	String text= TechnicalComponents.gettext(element);
	String s1=text.split(" ")[1];
	Assert.assertEquals("Search",s1);
 }

}
