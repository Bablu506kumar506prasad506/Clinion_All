package Clinion_EDC_Study;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class NotesExtraction {

	public NotesExtraction() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	static Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	static Actions action = new Actions(GlobelMethods.driver);

	public void NoteExtraction_PerSubject() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Notes Extraction").click();

		// ----Per Subject---//ctl07_lnkDataExtractionperSubject

		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementById("ctl07_lnkDataExtractionperSubject", 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// ----Panel Wise Note Extraction---//
	public void NoteExtraction_PanelNote() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Notes Extraction").click();

		// ----Per Subject---//ctl07_lnkDataExtractionperSubject

		Thread.sleep(3500);
		try {
			WebElement idpage = GWait.Wait_GetElementById("ctl07_lnkPanelExtraction", 120);
			idpage.click();
			GlobelMethods.isAlertPresent();
			Thread.sleep(3000);
			GWait.Wait_GetElementByLinkText("Logout").click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GWait.Wait_GetElementByLinkText("Logout").click();
		}
	}

	// ----Page Wise Note Extraction---//
	public void NoteExtraction_PageNote() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Notes Extraction").click();

		Thread.sleep(3500);
		boolean existsElement;

		existsElement = "ctl07_gvCrf_ctl02_lnkScreen" != null;
		if (existsElement == true) {
			WebElement table_element = GWait.Wait_GetElementByCSS("#ctl07_pnlNotes>table>tbody>tr>td>table>tbody");
			ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
			System.out.println("Rows count: " + rows.size());

			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet st = wb.getSheet("LabPageDataExtraction");
			int rowcounrt = st.getRows();
			System.out.println("Rows count: " + rowcounrt);
			for (int i = 01; i <= rowcounrt - 1; i++) {

				// String PNL_Data = st.getCell(2, i).getContents();
				// System.out.println("Panel Data: " + PNL_Data);

				for (int j = 02; j < rows.size() - 1; j++) {
					if (j <= 9) {
						WebElement link = GWait.Wait_GetElementById("ctl07_gvCrf_ctl0" + j + "_lnkScreen", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						link.click();
						GlobelMethods.AcceptDoenloadPopup();
						GlobelMethods.isAlertPresent();
						break;
					} else if (j >= 10) {
						WebElement link = GWait.Wait_GetElementById("ctl07_gvCrf_ctl" + j + "_lnkScreen", 120);
						;
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						link.click();
						GlobelMethods.AcceptDoenloadPopup();
						GlobelMethods.isAlertPresent();
						break;
					}

				}
			}
			GWait.Wait_GetElementByLinkText("Logout").click();
		}

	}

	// ----Visit Wise Note Extraction---//
	public void NoteExtraction_VisitNote() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Notes Extraction").click();

		Thread.sleep(3500);
		boolean existsElement;
		existsElement = "ctl07_gvVisits_ctl02_lnkScreen" != null;
		if (existsElement == true) {
			WebElement table_element;
			try {
				table_element = GWait.Wait_GetElementByCSS("#ctl07_gvVisits>tbody");

				ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
				System.out.println("Rows count: " + rows.size());

				FileInputStream fi = new FileInputStream(
						System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
				Workbook wb = Workbook.getWorkbook(fi);
				Sheet st = wb.getSheet("LabPageDataExtraction");
				int rowcounrt = st.getRows();
				System.out.println("Rows count: " + rowcounrt);
				for (int i = 01; i <= rowcounrt - 1; i++) {

					// String PNL_Data = st.getCell(2, i).getContents();
					// System.out.println("Panel Data: " + PNL_Data);

					for (int j = 02; j < rows.size() - 1; j++) {
						if (j <= 9) {
							WebElement link = GWait.Wait_GetElementById("ctl07_gvVisits_ctl0" + j + "_lnkScreen", 120);
							System.out.println(link);
							String linktext = link.getText();
							System.out.println("LinkData: " + linktext);
							link.click();
							GlobelMethods.AcceptDoenloadPopup();
							GlobelMethods.isAlertPresent();
							break;
						} else if (j >= 10) {
							WebElement link = GWait.Wait_GetElementById("ctl07_gvVisits_ctl" + j + "_lnkScreen", 120);
							System.out.println(link);
							String linktext = link.getText();
							System.out.println("LinkData: " + linktext);
							link.click();
							GlobelMethods.AcceptDoenloadPopup();
							GlobelMethods.isAlertPresent();
							break;
						}

					}
				}
				GWait.Wait_GetElementByLinkText("Logout").click();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				GWait.Wait_GetElementByLinkText("Logout").click();
			}

		}
	}

}
