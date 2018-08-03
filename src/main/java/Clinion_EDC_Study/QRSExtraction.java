package Clinion_EDC_Study;

import java.io.FileInputStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class QRSExtraction {

	public QRSExtraction() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	// ----QRS Extraction With Filtered Data---//
	public void QRSExtraction_WithFilter() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Qrs Extraction").click();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("QRSExtraction");

		int RowsCount = st.getRows();
		int ColmCount = st.getColumns();
		for (int i = 1; i < RowsCount; i++) {

			String ExportType_Data = st.getCell(1, 1).getContents();

			String SiteId_Data = st.getCell(2, i).getContents();
			String Subject_Data = st.getCell(3, i).getContents();
			String VisitName_Data = st.getCell(4, i).getContents();
			String Status_Data = st.getCell(5, i).getContents();
			String Queries_Data = st.getCell(6, i).getContents();

			WebElement excelexport = GWait.Wait_GetElementById("ctl07_ImgBtnExcel", 120);
			
			if (excelexport.getAttribute("title").equalsIgnoreCase(ExportType_Data)) {

				// ----Select Site---//
				Select Siteidslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrssites", 120));
				Siteidslct.selectByVisibleText(SiteId_Data);
				// ----Select Subject----//
				Select Subjctslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrssubjects", 120));
				Subjctslct.selectByVisibleText(Subject_Data);
				// ----Select Visit Name----//
				Select Visitslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrsvisitname", 120));
				Visitslct.selectByValue(VisitName_Data);
				// ----Select Status----//
				Select Statusslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrsstatus", 120));
				Statusslct.selectByVisibleText(Status_Data);
				// ----Select Queries type----//
				Select Queriesslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQueries", 120));
				Queriesslct.selectByVisibleText(Queries_Data);
				Thread.sleep(1500);
				GWait.Wait_GetElementById("ctl07_btnQrsfilter", 120).click();
				Thread.sleep(500);
				WebElement excelexport1 = GWait.Wait_GetElementById("ctl07_ImgBtnExcel", 120);
				excelexport1.click();
				WebElement pdfexport = GWait.Wait_GetElementById("ctl07_ImgBtnPdf", 120);
				pdfexport.click();
				WebElement Wordexport = GWait.Wait_GetElementById("ctl07_ImgbtnWord", 120);
				Wordexport.click();
			} 

		}
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();

	}

	// ----QRS extraction with all data---//
	public void QRSExtraction_All() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Qrs Extraction").click();
		
		Select Siteidslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrssites", 120));
		Siteidslct.selectByVisibleText("ALL");
		// ----Select Subject----//
		Select Subjctslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrssubjects", 120));
		Subjctslct.selectByVisibleText("ALL");
		// ----Select Visit Name----//
		Select Visitslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrsvisitname", 120));
		Visitslct.selectByVisibleText("ALL");
		// ----Select Status----//
		Select Statusslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQrsstatus", 120));
		Statusslct.selectByVisibleText("ALL");
		// ----Select Queries type----//
		Select Queriesslct = new Select(GWait.Wait_GetElementById("ctl07_ddlQueries", 120));
		Queriesslct.selectByVisibleText("All Queries");

		// ---Click Filter---//
		Thread.sleep(500);
		GWait.Wait_GetElementById("ctl07_btnQrsfilter", 120).click();
		WebElement excelexport = GWait.Wait_GetElementById("ctl07_ImgBtnExcel", 120);
		excelexport.click();
		Thread.sleep(500);
		WebElement pdfexport = GWait.Wait_GetElementById("ctl07_ImgBtnPdf", 120);
		pdfexport.click();
		Thread.sleep(500);
		WebElement Wordexport = GWait.Wait_GetElementById("ctl07_ImgbtnWord", 120);
		Wordexport.click();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

}
