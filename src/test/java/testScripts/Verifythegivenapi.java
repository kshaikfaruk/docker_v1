package testScripts;

import org.testng.annotations.Test;

import resuablecomponents.BussinessComponents;

public class Verifythegivenapi extends BussinessComponents {
    @Test
	public void verifyapiMessage() {
	 String s="{\r\n"
	 		+ "    \"name\": \"morpheus\",\r\n"
	 		+ "    \"job\": \"leader\"\r\n"
	 		+ "}";
    	verifythe_givenapi("https://reqres.in/api",s,"","post","/users","id");
    	
    	verifythe_givenapi("https://reqres.in/api","","2","get","/users","id");
	}
}
