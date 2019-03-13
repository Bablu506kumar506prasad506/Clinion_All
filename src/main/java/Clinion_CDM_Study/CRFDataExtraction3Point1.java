package Clinion_CDM_Study;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.webdriven.commands.IsAlertPresent;

import Clinion_EDC_Study.CRFDataExtraction;
import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class CRFDataExtraction3Point1 {

	public CRFDataExtraction3Point1() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);
	JavascriptExecutor js = (JavascriptExecutor)GlobelMethods.driver;
	
	public static String PNL_Data;

	public void eCRFDataExtractionClinion3Point1_PanelWise() throws Exception {

		GlobelMethods.Datamanager_Login3point1();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String PNLID = "ctl07_gvPanelDataExtraction_ctl02_lnkScreen1";
		String PNLXpath = "//*[@id=\"ctl00_ContentPlaceHolder1_gvPanelDataExtraction\"]/tbody/tr[1]/th/table/tbody/tr/th";
//		                ctl00_ContentPlaceHolder1_gvPanelDataExtraction
		// ----Panel Wise eCRF data extraction----//
		WebElement idpanl = GWait.Wait_GetElementByXpath(PNLXpath, 120);
		System.out.println("Panel Header Name: "+idpanl.getText());
		if (idpanl.getText().equals("Panel Names")) {
			WebElement table_element = GWait.Wait_GetElementByXpath("//table[contains(@id,'gvPanelDataExtraction')]");
			ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
			System.out.println("Rows count: " + rows.size());

			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet st = wb.getSheet("PanelWiseDataExtraction");
			int rowcounrt = st.getRows();
			System.out.println("Rows count: " + rows.size());
			for (int i = 01; i <= rowcounrt - 1; i++) {

				PNL_Data = st.getCell(3, i).getContents();
				System.out.println("Panel Data: " + PNL_Data);

				for (int j = 02; j < rows.size() - 1; j++) {
					if (j <= 9) {
						WebElement link = GWait
								.Wait_GetElementByXpath("//a[contains(@id,'gvPanelDataExtraction_ctl0" + j + "_lnkScreen1')]", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
							
//								GlobelMethods.AcceptDoenloadPopup();
								GlobelMethods.isAlertPresent();
							
							break;
						}
					} else if (j >= 10) {
						WebElement link = GWait
								.Wait_GetElementByXpath("//a[contains(@id,'gvPanelDataExtraction_ctl" + j + "_lnkScreen1')]", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
//							GlobelMethods.AcceptDoenloadPopup();
							GlobelMethods.isAlertPresent();
							break;
						}
					}

				}
				

				//------Data verification from excel and eCRF page------//
				String DNLDfilename = CRFDataExtraction3Point1.PNL_Data.replaceAll("\\s+", "");
				
				System.out.println("Donloaded page data: "+DNLDfilename);
				File fs = new File("C:\\Users\\bablu.p\\Downloads\\"+DNLDfilename+".xlsx");
				FileInputStream fis = new FileInputStream(fs);
				XSSFWorkbook wb1 = new XSSFWorkbook(fis);
				XSSFSheet st1 = wb1.getSheetAt(0);
				
				XSSFRow rows1 = st1.getRow(1);
				XSSFCell SiteNameV = rows1.getCell(0);
				String SiteName = SiteNameV.toString();
				XSSFCell PatIDV = rows1.getCell(1);
				String PatID = PatIDV.toString();
				
				js.executeScript("window.scrollBy(1000,0)");
				Thread.sleep(500);
				GWait.Wait_GetElementByLinkText("Data Capture").click();
				Select eleSite = new Select(GWait.Wait_GetElementByXpath("//Select[contains(@id,'_ddlSites')]", 120));
				eleSite.selectByVisibleText(SiteName);
				Thread.sleep(500);
				Select elePTID = new Select(GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_ddlSubjectId", 120));
				elePTID.selectByVisibleText(PatID);
				Thread.sleep(500);
				WebElement eleGo = GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnFilter", 120);
				js.executeScript("arguments[0].click();",eleGo);
				
				GWait.Wait_GetElementByLinkText(PatID).click();
				
				XSSFCell VISTNAMEV = rows1.getCell(2);
				String VISTNAME = VISTNAMEV.toString();
				String TrimVisitname = " "+VISTNAME;
				Thread.sleep(3000);
				int sizeOfTable = GlobelMethods.driver.findElements(By.xpath("//*[@id='lstAudit']/table")).size();
				System.out.println(sizeOfTable);
				for (int k = 1; k < sizeOfTable; k++) {
					WebElement visit1Name = GWait.Wait_GetElementByXpath("//*[@id=\"lstAudit\"]/table["+k+"]/tbody/tr[1]/td/div[1]", 120);
					
					String regex = "&nbsp;";
					String removeStartingspaceVSTNAM = visit1Name.getText().replace(regex, "");
					
					System.out.println(visit1Name.getText());
					System.out.println(TrimVisitname);
					if (visit1Name.getText().equals(TrimVisitname)) {
						Thread.sleep(3000);
						GWait.Wait_GetElementById("imgdivScrrenslist"+k+".00v", 120).click();
						break;
					}
				}
				
				XSSFCell PAGENAMEV = rows1.getCell(4);
				String PAGENAME = PAGENAMEV.toString();
				GWait.Wait_GetElementByLinkText(PAGENAME).click();
				
				//------ecrf Data verification method------//
				
				GlobelMethods.eCRFDataVerificationClinion3point1();
			
				

			}
			GWait.Wait_GetElementByLinkText("Logout").click();
		}
	}

	public void eCRFDataExtractionClinion3Point1_PageWise() throws Exception {

		GlobelMethods.Datamanager_Login3point1();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String PAGID = "ctl07_gvCrf_ctl02_lnkScreen";
		String PAGXpath = "//table[contains(@id,'_gvCrf')]/tbody/tr[1]/th/table/tbody/tr/th[1]";
						////*[@id="ctl00_ContentPlaceHolder1_gvCrf"]/tbody/tr[1]/th/table/tbody/tr/th[1]
		// ----Page Wise eCRF data extraction----//
		WebElement idpage = GWait.Wait_GetElementByXpath(PAGXpath, 120);

		if (idpage.getText().trim().equals("Page Name")) {
			WebElement table_element = GWait.Wait_GetElementByXpath("//table[contains(@id,'gvCrf')]");
			ArrayList<WebElement> rows = (ArrayList<WebElement>) table_element.findElements(By.tagName("tr"));
			System.out.println("Rows count: " + rows.size());

			FileInputStream fi = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
			Workbook wb = Workbook.getWorkbook(fi);
			Sheet st = wb.getSheet("PageWiseDataExtraction");
			int rowcounrt = st.getRows();
			System.out.println("Rows count: " + rowcounrt);
			for (int i = 01; i <= rowcounrt - 1; i++) {

				PNL_Data = st.getCell(3, i).getContents();
				System.out.println("Excel Data: " + PNL_Data);

				for (int j = 02; j < rows.size() - 1; j++) {
					if (j <= 9) {
						WebElement link = GWait.Wait_GetElementByXpath("//span[contains(@id,'gvCrf_ctl0" + j + "_')]", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
							WebElement ExlLink = GWait.Wait_GetElementByXpath("//a[contains(@id,'gvCrf_ctl0" + j + "_lnkXlScreen')]", 120);
							Thread.sleep(500);
							ExlLink.click();
							
//							GlobelMethods.AcceptDoenloadPopup();
							Thread.sleep(2000);
							GlobelMethods.isAlertPresent();
							break;
						}
					} else if (j >= 10) {
						WebElement link = GWait.Wait_GetElementByXpath("//span[contains(@id,'gvCrf_ctl" + j + "_')]", 120);
						System.out.println(link);
						String linktext = link.getText();
						System.out.println("LinkData: " + linktext);
						if (linktext.equalsIgnoreCase(PNL_Data)) {
							link.click();
							WebElement ExlLink = GWait.Wait_GetElementByXpath("//a[contains(@id,'gvCrf_ctl" + j + "_lnkXlScreen')]", 120);
							Thread.sleep(500);
							ExlLink.click();
//							GlobelMethods.AcceptDoenloadPopup();
							Thread.sleep(2000);
							GlobelMethods.isAlertPresent();
							break;
						}
					}

				}
				
				//-----ecrf PAge data verifivation-----//
				GlobelMethods GB = new GlobelMethods();
				GB.PageWiseMainDataVerificationClinion3Point1_M();

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
		String LABPAGXpath = "//tr/td[3]/table/tbody/tr[5]/td/table/tbody/tr[1]/th";
		// ----Lab Page eCRF data extraction----//
		Thread.sleep(3500);
		boolean existsElement;
		try {
			//ctl07_grdLabPageExtraction_ctl02_lnkScreen
			existsElement = "ctl00_ContentPlaceHolder1_grdLabPageExtraction_ctl02_lnkScreen" != null;
			if (existsElement==true) {
				WebElement idpage = GWait.Wait_GetElementByXpath(LABPAGXpath, 120);

				if (idpage.getText().trim().equals("Lab Page Data Extraction")) {
					WebElement table_element = GWait.Wait_GetElementByXpath("//table[contains(@id,'grdLabPageExtraction')]");
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
						for (int j = 02; j < rows.size() - 1; j++) {
							
							if (j <= 9) {
								WebElement link = GWait
										.Wait_GetElementByXpath("//a[contains(@id,'grdLabPageExtraction_ctl0" + j + "_')]", 120);
								System.out.println(link);
								String linktext = link.getText().trim();
								System.out.println("LinkData: " + linktext);
								System.out.println("Panel Data: " + PNL_Data);
								if (linktext.equalsIgnoreCase(PNL_Data.trim())) {
									link.click();
									Thread.sleep(1500);
									GlobelMethods.driver.switchTo().frame(GlobelMethods.driver.findElement(By.tagName("iframe")));
									GWait.Wait_GetElementByCSS("#lnkbtndownloadall").click();
									GlobelMethods.isAlertPresent();
									GlobelMethods.driver.switchTo().defaultContent();
									GWait.Wait_GetElementByXpath("//div[@id='cboxContent']/div[9]").click();
									link.click();
									Thread.sleep(1500);
									GlobelMethods.driver.switchTo().frame(GlobelMethods.driver.findElement(By.tagName("iframe")));
									WebElement View = GWait.Wait_GetElementById("gvLabPageExtraction_ctl02_lblprocessing");
									if (View == null ) {
										GWait.Wait_GetElementByCSS("#gvLabPageExtraction_ctl02_lnkbtndownLoad").click();
										GlobelMethods.AcceptDoenloadPopup();
										GlobelMethods.isAlertPresent();
										GlobelMethods.driver.switchTo().defaultContent();
										GWait.Wait_GetElementByXpath("//div[@id='cboxContent']/div[9]").click();
										break;
									} else {
										GlobelMethods.driver.switchTo().defaultContent();
										GWait.Wait_GetElementByXpath("//div[@id='cboxContent']/div[9]").click();
									}
									
								}
								
							} else if (j >= 10) {
								WebElement link = GWait
										.Wait_GetElementByXpath("//a[contains(@id,'grdLabPageExtraction_ctl" + j + "_')]", 120);
								System.out.println(link);
								String linktext = link.getText().trim();
								System.out.println("LinkData: " + linktext);
								if (linktext.equalsIgnoreCase(PNL_Data.trim())) {
									link.click();
									Thread.sleep(1500);
									GlobelMethods.driver.switchTo().frame(GlobelMethods.driver.findElement(By.tagName("iframe")));
									GWait.Wait_GetElementByCSS("#lnkbtndownloadall").click();
									GlobelMethods.isAlertPresent();////div[@id='cboxContent']/div[9]
									GlobelMethods.driver.switchTo().defaultContent();
									GWait.Wait_GetElementByXpath("//div[@id='cboxContent']/div[9]").click();
									link.click();
									Thread.sleep(1500);
									GlobelMethods.driver.switchTo().frame(GlobelMethods.driver.findElement(By.tagName("iframe")));
									WebElement View = GWait.Wait_GetElementById("gvLabPageExtraction_ctl02_lblprocessing");
									if (View == null ) {
										GWait.Wait_GetElementByCSS("#gvLabPageExtraction_ctl02_lnkbtndownLoad").click();
										GlobelMethods.AcceptDoenloadPopup();
										GlobelMethods.isAlertPresent();
										GlobelMethods.driver.switchTo().defaultContent();
										GWait.Wait_GetElementByXpath("//div[@id='cboxContent']/div[9]").click();
										break;
									} else {
										GlobelMethods.driver.switchTo().defaultContent();
										GWait.Wait_GetElementByXpath("//div[@id='cboxContent']/div[9]").click();
									}
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

		GlobelMethods.Datamanager_Login3point1();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String DASHBRDID = "//a[contains(@id,'lnkDashBoardExtraction')]";
		

		// ----DashBoard Data Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementByXpath(DASHBRDID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// -----Edit Checks Extraction-----//
	public void eCRFDataExtraction_Editcheck() throws Exception {

		GlobelMethods.Datamanager_Login3point1();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();

		String EDTCHKID = "//a[contains(@id,'lnkEditCheckExtraction')]";
		

		// ----Edit Checks Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementByXpath(EDTCHKID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// -----Per Visit Data Extraction-----//
	public void eCRFDataExtraction_PerVisit() throws Exception {

		GlobelMethods.Datamanager_Login3point1();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();
		//ImgXlExtractionVisit
//		String PERVIDITID3point1 = "//a/img[contains(@id,'ImgXlExtractionVisit')]";
		
		String PERVIDITID = "//a[contains(@id,'_lnkSubjectExtractionbyVisit')]";
		// ----Per Visit Data Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementByXpath(PERVIDITID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

	// -----Per Subject Data Extraction-----//
	public void eCRFDataExtraction_PerSubjt() throws Exception {

		GlobelMethods.Datamanager_Login3point1();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("CRF Data Extraction").click();
		//ImgXlExtractionSubject
//		String PERVIDITID3point1 = "//a/img[contains(@id,'ImgXlExtractionSubject')]";
		String PERSUBJTID = "//a[contains(@id,'_lnkDataExtractionperSubject')]";

		// ----Per Subject Data Extraction----//
		Thread.sleep(3500);
		WebElement idpage = GWait.Wait_GetElementByXpath(PERSUBJTID, 120);
		idpage.click();
		GlobelMethods.isAlertPresent();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

}
