package testScripts;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resuablecomponents.BussinessComponents;

public class VerifyUserNavigatetoApplication extends BussinessComponents {

	@Test(dataProvider="testdata")
	
	public void navigateToapplication(String username, String code) throws IOException {
		navigate_to_Application(getProperties("url"));
		entervaluein_inputField(username);
		verifyText(code);
		
	}
	
	@DataProvider(name="testdata")
	public Object[][] testData(){
		Object[][] t= new Object[2][2];
		t[0][0]="user1@gmail.com";
		t[0][1]="Search";
		t[1][0]="user1@gmail.com";
		t[1][1]="Search";
		return t;
		
	}
	
	
}
