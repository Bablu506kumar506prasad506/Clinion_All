package Clinion_StudyConfiguration;

import java.io.FileInputStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class CentralAdminTasksCreateUsersClass {
	
	public CentralAdminTasksCreateUsersClass(){
		PageFactory.initElements(GlobelMethods.driver, this);
	}
	
	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);
	
	public void CACreateUsers() throws Exception {
		
		GlobelMethods.Superadmin_Login();

		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[7]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Create Users").click();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("CACreateUsers");
		int rowcount = st.getRows();
		System.out.println("No. of Row count: "+rowcount);
		
		for (int i = 1; i < rowcount; i++) {
			
			int ColmCount = st.getColumns();
			System.out.println("no. of Coloumn count: "+ColmCount);
			for (int j = 1; j < ColmCount-1; j++) {
				
				String Rolename_Data = st.getCell(j, i).getContents();
				String Title_Data = st.getCell(j+1, i).getContents();
				String Name_Data = st.getCell(j+2, i).getContents();
				String Email_Data = st.getCell(j+3, i).getContents();
				String Username_Data = st.getCell(j+4, i).getContents();
				String Mobile_Data = st.getCell(j+6, i).getContents();
				
				
				Select roleslct = new Select(GWait.Wait_GetElementById("ctl18_ddlRoles"));
				roleslct.selectByVisibleText(Rolename_Data);
				
				Select Titleslct = new Select(GWait.Wait_GetElementById("ctl18_ddlTitle"));
				Titleslct.selectByVisibleText(Title_Data);
				
				GWait.Wait_GetElementById("ctl18_txtName").sendKeys(Name_Data);
				GWait.Wait_GetElementById("ctl18_txtEmailId").sendKeys(Email_Data);
				GWait.Wait_GetElementById("ctl18_txtUserName").sendKeys(Username_Data);
				GWait.Wait_GetElementById("ctl18_txtMobile").sendKeys(Mobile_Data);
				
				//---Reset details----//
//				GWait.Wait_GetElementById("ctl18_btnCancel").click();
				//---Submit Button----//
				GWait.Wait_GetElementById("ctl18_btnSubmit").click();
				Thread.sleep(3000);
				GlobelMethods.UserCreationMailFunctionality();
				break;
			}
		}
		Thread.sleep(1000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

}
