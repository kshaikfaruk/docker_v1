package resuablecomponents;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import or.Homepage;
import responseValidation.CreateUserresponse;

public class BussinessComponents extends TechnicalComponents {
    public Homepage hs;
    
	public  void navigate_to_Application(String url) {
		test.log(Status.PASS, "navigate to the url"+url);
		TechnicalComponents.navigateTOUrl(url);
		TechnicalComponents.waitforWaitElement(20);
		
	}
	
	public  void entervaluein_inputField(String text) throws IOException {
	hs= new Homepage(driver);
	test.log(Status.PASS, "Enter value in the input field");
	 System.out.println(TechnicalComponents.takescreenshot());
	 test.log(Status.PASS,"<b>" + "<font color=" + "red>" + "Screenshot" + "</font>" + "</b>",
				MediaEntityBuilder.createScreenCaptureFromPath(TechnicalComponents.takescreenshot())
						.build());
	hs.type(hs.inputField,text);
	hs.Click_searchButton(hs.search_button);
	test.log(Status.PASS,"Click on the search button",MediaEntityBuilder.createScreenCaptureFromPath(TechnicalComponents.takescreenshot())
			.build() );
	}
	public  void verifyText() throws IOException {
	hs= new Homepage(driver);
	hs.verifyResultsText(hs.results);
	test.log(Status.PASS,"Click on the search button",MediaEntityBuilder.createScreenCaptureFromPath(TechnicalComponents.takescreenshot())
			.build() );
	}
	
	 public void verifythe_givenapi(String baseurl,String playload,String pathparam,String method,String endpotinturl,String element) {
		 test.log(Status.PASS, "given required details");
		 givenPayload( baseurl,playload,pathparam);
		 test.log(Status.PASS, "htting api request");
	    resp= hiturl(method,endpotinturl);
	    test.log(Status.PASS, "verifiying api response");
	    CreateUserresponse s= Verifythe_response(resp,element);
	    test.log(Status.PASS, "id :"+s.getId());
	    test.log(Status.PASS, "created AT"+s.getCreatedAt());
	    
	 }
	
}

