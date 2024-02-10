package testScripts;

import java.io.IOException;

import org.testng.annotations.Test;

import resuablecomponents.BussinessComponents;

public class VerifyUserNavigatetoApplication extends BussinessComponents {

	@Test
	public void navigateToapplication() throws IOException {
		navigate_to_Application(getProperties("url"));
		entervaluein_inputField("hello");
		verifyText();
		
	}
}
