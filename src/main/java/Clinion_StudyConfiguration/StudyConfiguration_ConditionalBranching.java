package Clinion_StudyConfiguration;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class StudyConfiguration_ConditionalBranching {

	public StudyConfiguration_ConditionalBranching() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	
	

	public void Config_ConditionalBranching() throws Exception {
		GlobelMethods.Superadmin_Login();

		Thread.sleep(2000);
		Configure_NewStudy();

		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("ConditionalBranchingConfigurati");
       
        FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb1 = Workbook.getWorkbook(fi);
		Sheet r1 = wb1.getSheet("ConditionalBranchingConfigurati");
		
		GWait.Wait_GetElementById("imgCB", 120).click();
		GWait.Wait_GetElementByCSS("#Table9 > tbody > tr > td > a > span > b").click();

		int countrows = r1.getRows();
		System.out.println("No. of Rows: "+countrows);
		for (int i = 1; i <= countrows - 1; i++) {
			 int noOfColumns = sheet.getRow(i).getLastCellNum();
			String SourceVisit = r1.getCell(0, i).getContents();
			String SourcePages = r1.getCell(1, i).getContents();
			String SourcePanels = r1.getCell(2, i).getContents();
			String SourceItems = r1.getCell(3, i).getContents();
			String CB_Value1 = r1.getCell(4, i).getContents();
			String CB_Value2 = r1.getCell(5, i).getContents();
			String CB_Action = r1.getCell(6, i).getContents();
			String CB_IsGlobal = r1.getCell(7, i).getContents();
			String CB_IsCross = r1.getCell(8, i).getContents();
			String CB_Message = r1.getCell(9, i).getContents();
			String DestinationVisit = r1.getCell(10, i).getContents();
			String DestinationPages = r1.getCell(11, i).getContents();
			

			Select SVisit = new Select(GWait.Wait_GetElementById("ctl18_ddlSourceVisit", 120));
			SVisit.selectByVisibleText(SourceVisit);

			Select SPages = new Select(GWait.Wait_GetElementById("ctl18_ddlSourcePage", 120));
			SPages.selectByVisibleText(SourcePages);

			Select SPanels = new Select(GWait.Wait_GetElementById("ctl18_ddlSourcePanels", 120));
			SPanels.selectByVisibleText(SourcePanels);

			Select SItems = new Select(GWait.Wait_GetElementById("ctl18_ddlSourceItems", 120));
			SItems.selectByVisibleText(SourceItems);

			if (CB_Value1.equals("Not Null")) {
				GWait.Wait_GetElementById("ctl18_rbtnNotNull", 120).click();
			} else {
				GWait.Wait_GetElementById("ctl18_rbtnValue", 120).click();
				Thread.sleep(1500);
				GWait.Wait_GetElementById("ctl18_txtConditionalBranchingValue", 120).sendKeys(CB_Value2);
			}

			if (CB_Action.equals("Disable First")) {
				GWait.Wait_GetElementById("ctl18_rbtnDsableFirst", 120).click();
			} else {
				GWait.Wait_GetElementById("ctl18_rbtnEnableFirst", 120).click();
			}

			if (CB_IsGlobal.equals("Yes")) {
				GWait.Wait_GetElementById("ctl18_ChkIsGlobal", 120).click();
			}

			if (CB_IsCross.equals("Yes")) {
				GWait.Wait_GetElementById("ctl18_chkIsCrossCondition", 120).click();
			}

			GWait.Wait_GetElementById("ctl18_txtconditionbranchmessage", 120).sendKeys(CB_Message);

			Select SDVisit = new Select(GWait.Wait_GetElementById("ctl18_ddlDestinationVisit", 120));
			SDVisit.selectByVisibleText(DestinationVisit);
			Select SDPages = new Select(GWait.Wait_GetElementById("ctl18_ddlDestinationPage", 120));
			SDPages.selectByVisibleText(DestinationPages);
			// ctl18_tvPanelsn0Nodes
			int m=1;
			int k = 0;
			
//			int coloumncount = r1.getColumns();
			System.out.println("No. of Columns: "+noOfColumns);
			
			for (int l = 12; l < noOfColumns; l++) {
				
				String DestinationPanelsItems = r1.getCell(l, i).getContents();
				
				String[] SDItems = DestinationPanelsItems.split(",");
				System.out.println("Length of items given in excel: " + SDItems.length);
				
				WebElement ExpendIcon = GWait.Wait_GetElementByXpath("//table["+m+"]/tbody/tr/td[1]/a/img");
				ExpendIcon.click();
				List<WebElement> listoftable = GlobelMethods.driver
						.findElements(By.cssSelector("#ctl18_tvPanelsn" + k + "Nodes>table"));
				System.out.println("no. of table: " + listoftable.size());
				
				for (int j = 0; j <= listoftable.size();) {
					
					WebElement PanelNames = GWait.Wait_GetElementById("ctl18_tvPanelsn" + j + "CheckBox");
					WebElement PanelName_Data = GWait.Wait_GetElementById("ctl18_tvPanelst" + j + "");
					
					System.out.println("Web data: " + PanelName_Data.getText());
					for (String string : SDItems) {
					if (PanelName_Data.getText().equals(string)) {
						PanelNames.click();
						break;
						}
					}
					j++;
					k++;
				}
				m++;
			}
			Thread.sleep(1000);
			GWait.Wait_GetElementById("ctl18_btnSave", 120).click();
			Thread.sleep(4000);
		}

	}

	public void Configure_NewStudy() throws Exception {
		Thread.sleep(1000);
		GWait.Wait_GetElementByCSS("img[alt=\"Configure eCRF\"]").click();

	}
	
}
