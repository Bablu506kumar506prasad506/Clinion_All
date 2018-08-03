package Clinion_EDC_Study;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class CRFDataExtraction {

	public CRFDataExtraction() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	public void eCRFDataExtraction_PanelWise() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String PNLID = "ctl07_gvPanelDataExtraction_ctl02_lnkScreen1";

		// ----Panel Wise eCRF data extraction----//
		WebElement idpanl = GWait.Wait_GetElementById(PNLID, 120);

		if (idpanl.getText().equals("Date of Visit")) {
			WebElement table_element = GWait.Wait_GetElementByCSS("#ctl07_gvPanelDataExtraction");
			ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
			System.out.println("Rows count: " + rows.size());

			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet st = wb.getSheet("PanelWiseDataExtraction");
			int rowcounrt = st.getRows();
			System.out.println("Rows count: " + rows.size());
			for (int i = 01; i <= rowcounrt - 1; i++) {

				String PNL_Data = st.getCell(2, i).getContents();
				System.out.println("Panel Data: " + PNL_Data);

				for (int j = 02; j < rows.size() - 1; j++) {
					if (j <= 9) {
						WebElement link = GWait
								.Wait_GetElementById("ctl07_gvPanelDataExtraction_ctl0" + j + "_lnkScreen1", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
							GlobelMethods.AcceptDoenloadPopup();
							GlobelMethods.isAlertPresent();
							break;
						}
					} else if (j >= 10) {
						WebElement link = GWait
								.Wait_GetElementById("ctl07_gvPanelDataExtraction_ctl" + j + "_lnkScreen1", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
							GlobelMethods.AcceptDoenloadPopup();
							GlobelMethods.isAlertPresent();
							break;
						}
					}

				}

			}
			GWait.Wait_GetElementByLinkText("Logout").click();
		}
	}

	public void eCRFDataExtraction_PageWise() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String PAGID = "ctl07_gvCrf_ctl02_lnkScreen";

		// ----Page Wise eCRF data extraction----//
		WebElement idpage = GWait.Wait_GetElementById(PAGID, 120);

		if (idpage.getText().trim().equals("Date Of Visit")) {
			WebElement table_element = GWait.Wait_GetElementByCSS("#ctl07_gvCrf > tbody");
			ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
			System.out.println("Rows count: " + rows.size());

			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet st = wb.getSheet("PageWiseDataExtraction");
			int rowcounrt = st.getRows();
			System.out.println("Rows count: " + rowcounrt);
			for (int i = 01; i <= rowcounrt - 1; i++) {

				String PNL_Data = st.getCell(2, i).getContents();
				System.out.println("Panel Data: " + PNL_Data);

				for (int j = 02; j < rows.size() - 1; j++) {
					if (j <= 9) {
						WebElement link = GWait.Wait_GetElementById("ctl07_gvCrf_ctl0" + j + "_lnkScreen", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
							GlobelMethods.AcceptDoenloadPopup();
							GlobelMethods.isAlertPresent();
							break;
						}
					} else if (j >= 10) {
						WebElement link = GWait.Wait_GetElementById("ctl07_gvCrf_ctl" + j + "_lnkScreen", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
							GlobelMethods.AcceptDoenloadPopup();
							GlobelMethods.isAlertPresent();
							break;
						}
					}

				}

			}
			GWait.Wait_GetElementByLinkText("Logout").click();
		}
	}

	// -----Lab Page Data Extraction-----//
	public void eCRFDataExtraction_LabPage() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String LABPAGID = "ctl07_grdLabPageExtraction_ctl02_lnkScreen";

		// ----Lab Page eCRF data extraction----//
		Thread.sleep(3500);
		boolean existsElement;
		try {
			existsElement = "ctl07_grdLabPageExtraction_ctl02_lnkScreen" != null;
			if (existsElement==true) {
				WebElement idpage = GWait.Wait_GetElementById(LABPAGID, 120);

				if (idpage.getText().trim().equals("Primary Diagnosis")) {
					WebElement table_element = GWait.Wait_GetElementByCSS("#ctl07_grdLabPageExtraction>tbody");
					ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
					System.out.println("Rows count: " + rows.size());

					FileInputStream fi = new FileInputStream(
							System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
					Workbook wb = Workbook.getWorkbook(fi);
					Sheet st = wb.getSheet("LabPageDataExtraction");
					int rowcounrt = st.getRows();
					System.out.println("Rows count: " + rowcounrt);
					for (int i = 01; i <= rowcounrt - 1; i++) {

						String PNL_Data = st.getCell(2, i).getContents();
						System.out.println("Panel Data: " + PNL_Data);

						for (int j = 02; j < rows.size() - 1; j++) {
							if (j <= 9) {
								WebElement link = GWait
										.Wait_GetElementById("ctl07_grdLabPageExtraction_ctl0" + j + "_lnkScreen", 120);
								System.out.println(link);
								String linktext = link.getText();
								System.out.println("LinkData: " + linktext);
								if (linktext.equalsIgnoreCase(PNL_Data)) {
									link.click();
									GlobelMethods.AcceptDoenloadPopup();
									GlobelMethods.isAlertPresent();
									break;
								}
							} else if (j >= 10) {
								WebElement link = GWait
										.Wait_GetElementById("ctl07_grdLabPageExtraction_ctl" + j + "_lnkScreen", 120);
								System.out.println(link);
								String linktext = link.getText();
								System.out.println("LinkData: " + linktext);
								if (linktext.equalsIgnoreCase(PNL_Data)) {
									link.click();
									GlobelMethods.AcceptDoenloadPopup();
									GlobelMethods.isAlertPresent();
									break;
								}
							}

						}

					}
					GWait.Wait_GetElementByLinkText("Logout").click();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GWait.Wait_GetElementByLinkText("Logout").click();
		}
	}

	// -----DashBoard Data Extraction-----//
	public void eCRFDataExtraction_DashBORD() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String DASHBRDID = "ctl07_lnkDashBoardExtraction";
		

		// ----DashBoard Data Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementById(DASHBRDID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// -----Edit Checks Extraction-----//
	public void eCRFDataExtraction_Editcheck() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String EDTCHKID = "ctl07_lnkEditCheckExtraction";
		

		// ----Edit Checks Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementById(EDTCHKID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// -----Per Visit Data Extraction-----//
	public void eCRFDataExtraction_PerVisit() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String PERVIDITID = "ctl07_lnkSubjectExtractionbyVisit";

		// ----Per Visit Data Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementById(PERVIDITID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// -----Per Subject Data Extraction-----//
	public void eCRFDataExtraction_PerSubjt() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String PERSUBJTID = "ctl07_lnkDataExtractionperSubject";

		// ----Per Subject Data Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementById(PERSUBJTID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

}
