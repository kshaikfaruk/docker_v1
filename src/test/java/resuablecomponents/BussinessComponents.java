package resuablecomponents;

import java.io.IOException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import config.ExtentFactory;
import or.Homepage;
import responseValidation.CreateUserresponse;
import responseValidation.ResponseVale;

public class BussinessComponents extends TechnicalComponents {
    public Homepage hs;
 
    
	public  void navigate_to_Application(String url) {
		test.log(Status.PASS, "navigate to the url"+url);
		TechnicalComponents.navigateTOUrl(url);
		TechnicalComponents.waitforWaitElement(20);
		
	}
	
	public  void entervaluein_inputField(String text) throws IOException {
	hs= new Homepage(driver);
	ExtentFactory.getInstance().getExtent().log(Status.PASS, "Enter value in the input field");
	 System.out.println(TechnicalComponents.takescreenshot());
	 ExtentFactory.getInstance().getExtent().log(Status.PASS,"<b>" + "<font color=" + "red>" + "Screenshot" + "</font>" + "</b>",
				MediaEntityBuilder.createScreenCaptureFromPath(TechnicalComponents.takescreenshot())
						.build());
	hs.type(hs.inputField,text);
	hs.Click_searchButton(hs.search_button);
	ExtentFactory.getInstance().getExtent().log(Status.PASS,"Click on the search button",MediaEntityBuilder.createScreenCaptureFromPath(TechnicalComponents.takescreenshot())
			.build() );
	}
	public  void verifyText(String Text) throws IOException {
	hs= new Homepage(driver);
	hs.verifyResultsText(hs.results,Text);
	ExtentFactory.getInstance().getExtent().log(Status.PASS,"Click on the search button",MediaEntityBuilder.createScreenCaptureFromPath(TechnicalComponents.takescreenshot())
			.build() );
	}
	
	 public void verifythe_givenapi(String baseurl,String playload,String pathparam,String method,String endpotinturl,String element, int statuscode) {
		 ExtentFactory.getInstance().getExtent().log(Status.PASS, "given required details");
		 givenPayload( baseurl,playload,pathparam);
		 ExtentFactory.getInstance().getExtent().log(Status.PASS, "htting api request");
	    resp= hiturl(method,endpotinturl);
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "verifiying api response");
	    CreateUserresponse s= Verifythe_response(resp,element,statuscode);
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "id :"+s.getId());
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "created AT"+s.getCreatedAt());
	    
	 }
	 
	 public void verifythe_givenapiresponse(String baseurl,String playload,String pathparam,String method,String endpotinturl,String element, int statuscode) {
		 ExtentFactory.getInstance().getExtent().log(Status.PASS, "given required details");
		 givenPayload( baseurl,playload,pathparam);
		 ExtentFactory.getInstance().getExtent().log(Status.PASS, "htting api request");
	    resp= hiturl(method,endpotinturl);
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "verifiying api response");
	    ResponseVale s= Verifyapiresonse(resp,element,statuscode);
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "id :"+s.getPage());
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "created AT"+s.getPer_page());
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "firstName"+s.getData().get(1).getFirst_name());
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "lastName"+s.getData().get(1).getLast_name());
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "email"+s.getData().get(1).getEmail());
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "url"+s.getSupport().getUrl());
	    ExtentFactory.getInstance().getExtent().log(Status.PASS, "url"+s.getSupport().getText());
	    
	    
	 }
	
}

