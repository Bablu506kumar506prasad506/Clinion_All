package Clinion_Execution;

import java.io.FileInputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Clinion_CDM_Study.CRFDataExtraction3Point1;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class Clinion_CRFdataExtractionExecutionClassClinion3Point1 {
	
	
	@BeforeMethod
	public static void BrowserInit() throws Exception{
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");
		
		String url_link = st.getCell(0, 3).getContents();
		String BrowserName_Data = st.getCell(1, 2).getContents();
		
		GlobelMethods.LauncherBrowser(BrowserName_Data, url_link);
		
	}
	
	@Test(priority = 0)
	public void eCRFDataExtractionClinion3Point1PanelWise() throws Exception {
		CRFDataExtraction3Point1 eCRFDE = new CRFDataExtraction3Point1();
		eCRFDE.eCRFDataExtractionClinion3Point1_PanelWise();
	}
	
	@Test(priority = 1)
	public void eCRFDataExtractionClinion3Point1PageWise() throws Exception {
		CRFDataExtraction3Point1 eCRFDE = new CRFDataExtraction3Point1();
		eCRFDE.eCRFDataExtractionClinion3Point1_PageWise();
	}
	
	@Test(priority = 2)
	public void eCRFDataExtractionClinion3Point1DashBORD() throws Exception {
		CRFDataExtraction3Point1 eCRFDE = new CRFDataExtraction3Point1();
		eCRFDE.eCRFDataExtraction_DashBORD();
	}
	
	@Test(priority = 3)
	public void eCRFDataExtractionClinion3Point1Editcheck() throws Exception {
		CRFDataExtraction3Point1 eCRFDE = new CRFDataExtraction3Point1();
		eCRFDE.eCRFDataExtraction_Editcheck();
	}
	
	@Test(priority = 4)
	public void eCRFDataExtractionClinion3Point1PerVisit() throws Exception {
		CRFDataExtraction3Point1 eCRFDE = new CRFDataExtraction3Point1();
		eCRFDE.eCRFDataExtraction_PerVisit();
	}
	
	@Test(priority = 5)
	public void eCRFDataExtractionClinion3Point1PerSubjt() throws Exception {
		CRFDataExtraction3Point1 eCRFDE = new CRFDataExtraction3Point1();
		eCRFDE.eCRFDataExtraction_PerSubjt();
	}
	
	@AfterMethod
	public void closebrowser() {
		GlobelMethods.driver.quit();
	}

}
