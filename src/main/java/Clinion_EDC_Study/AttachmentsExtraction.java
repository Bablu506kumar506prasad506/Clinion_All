package Clinion_EDC_Study;

import java.io.FileInputStream;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class AttachmentsExtraction {
	public AttachmentsExtraction() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	// -----Download Single Attachment------//
	public void Attachment_Single() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Attachments Extraction").click();

		// ctl07_gvAttachments_ctl02_gvChildAttachments_ctl02_lnkChildTitle
		// ctl07_gvAttachments_ctl03_gvChildAttachments_ctl02_lnkChildTitle
		//---filter need to apply leter---//
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("AttachmentExtraction");
		int rowcount = st.getRows();
		for (int i = 1; i <= rowcount - 1; i++) {
			String Attachment_Data = st.getCell(1, i).getContents();
			System.out.println(Attachment_Data);
			GWait.Wait_GetElementByLinkText(Attachment_Data).click();
			GlobelMethods.isAlertPresent();

		}
		GWait.Wait_GetElementByLinkText("Logout").click();

	}

	// -----Download Multiple Attachments------//
	public void Attachment_All() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Attachments Extraction").click();

		GWait.Wait_GetElementById("ctl07_lnkExtract").click();
		GlobelMethods.isAlertPresent();

		GWait.Wait_GetElementByLinkText("Logout").click();

	}

}
