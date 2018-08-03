package Clinion_StudyWorkflow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class StudyAdminTasksRolesManagement {
	
	public StudyAdminTasksRolesManagement(){
		PageFactory.initElements(GlobelMethods.driver, this);
	}
	
	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);
	
	public void StudyAdmin_RoleManagement() throws Exception {
		
		GlobelMethods.StudyAdmin_Login();
		GWait.Wait_GetElementByXpath("//tr/td[3]/table/tbody/tr/td/a").click();
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("SARoleManagement");
		
		int RowsCount = st.getRows();
		for (int i = 1; i <= RowsCount-1; i++) {
			
			String Rolename_Data = st.getCell(1, i).getContents();
			String AccessLevel_Data = st.getCell(2, i).getContents();
			
			GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_txtRoleName", 120).sendKeys(Rolename_Data);
			if (AccessLevel_Data.equals("Trial")) {
				GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_rbtnAccessLevels_0").click();
			} else {
				GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_rbtnAccessLevels_1").click();
			}
			Thread.sleep(1000);
			GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnSubmit", 120).click();
			Thread.sleep(1000);
		}
		Thread.sleep(2000);
		GWait.Wait_GetElementByLinkText("Logout").click();
		
	}

}
