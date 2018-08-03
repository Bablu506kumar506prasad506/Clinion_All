package Clinion_StudyConfiguration;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;

public class forgotpasswordClass extends GlobelMethods {
	
	public forgotpasswordClass(){
		PageFactory.initElements(driver, this);
	}
	
	Clinion_WaitMethod GWait = new Clinion_WaitMethod(driver);
	Actions action = new Actions(driver);
	
	public void forgotpasswordM() throws Exception {
		
		Thread.sleep(2000);
		GWait.Wait_GetElementByCSS("a.forgot").click();
		ForgotPassword();
	}

}
