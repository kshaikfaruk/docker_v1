package testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import resuablecomponents.BussinessComponents;

public class VerifyNavigateapplication extends BussinessComponents	 {
    @Test
	public void navigateapplication() throws IOException {
		navigate_to_Application(getProperties("url"));
		entervaluein_inputField("hello");
		Assert.assertEquals(false, true);
		
	}
}
