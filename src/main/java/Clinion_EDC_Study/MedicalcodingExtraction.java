package Clinion_EDC_Study;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;

public class MedicalcodingExtraction {

	public MedicalcodingExtraction() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	// ----Adverse Events medical coding extraction---//
	public void MedicalCodngEctrct_AE() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Medical Coding Extraction").click();

		GWait.Wait_GetElementById("ctl07_lnkAdverseEvents", 120).click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// ----Concomitant Medications medical coding extraction---//
	public void MedicalCodngEctrct_CM() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Medical Coding Extraction").click();

		GWait.Wait_GetElementById("ctl07_lnkConComitantMedications", 120).click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// ---Reverse Method----//
	public void Test() {
		String name = "My Name Bablu";
		System.out.println("Before resvers: " + name);
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb = sb.reverse();
		System.out.println("After resvers: " + sb);
		for (int i = 0; i < sb.length(); i++) {
			System.out.println(sb.charAt(i));
		}
	}

	public void Test2() {
		String name = "Test Bablu";
		char[] rev = name.toCharArray();
		char[] result = new char[rev.length];

		/*
		 * for (int i = rev.length - 1; i >= 0; i--) {
		 * System.out.println(rev[i]); }
		 */

		for (int i = 0; i < result.length; i++) {
			result[i] = rev[rev.length - i - 1];
			System.out.println(new String(result));
		}

	}

	public void Test3() {
		String input = "GeeksforGeeks";
		byte[] strAsByteArray = input.getBytes();

		byte[] result = new byte[strAsByteArray.length];

		for (int i = 0; i < strAsByteArray.length; i++){
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];
		}
		System.out.println(new String(result));
	}

	public void Test4() {
		String input = "java";
		byte[] ba = input.getBytes();
		byte[] result = new byte[ba.length];
		int len = ba.length;
		for (byte b : ba) {
			result[len - 1] = b;
			len--;
		}
		System.out.println(new String(result));
	}

}
