package Clinion_StudyConfiguration;

import java.io.FileInputStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class CentralAdminTasksRolesManagement {
	
	public CentralAdminTasksRolesManagement(){
		PageFactory.initElements(GlobelMethods.driver, this);
	}
	
	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);
	
	public void CARoleManagement() throws Exception {
		
		GlobelMethods.Superadmin_Login();
		
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[7]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Roles Management").click();
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("CARoleMangmt");
		
		int colmcount = st.getColumns();
		System.out.println(colmcount);
		for (int i = 2; i <= colmcount-1; i++) {
			
			String RoleName_data = st.getCell(i, 1).getContents();
			GWait.Wait_GetElementById("ctl18_txtRoleName", 120).clear();
			GWait.Wait_GetElementById("ctl18_txtRoleName", 120).sendKeys(RoleName_data);
			GWait.Wait_GetElementById("ctl18_btnSubmit", 120).click();
			
		}
		Thread.sleep(1000);
		GWait.Wait_GetElementByLinkText("Logout").click();
		
	}

}
