package Clinion_StudyWorkflow;

import java.io.FileInputStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class StudyAdminTasksRolesXActionMNGMT {

	public StudyAdminTasksRolesXActionMNGMT() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	public void StudyAdmin_RoleXFeatureManagmt() throws Exception {

		GlobelMethods.StudyAdmin_Login();

		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[9]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Roles X Features Management").click();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("SAROleXFeatureAndActionMngmt");
		int RowsCount = st.getRows();
		System.out.println("No. Of Rows count: " + RowsCount);
		for (int i = 1; i <= RowsCount - 1; i++) {
			Thread.sleep(1500);
			String RoleName_Data = st.getCell(1, i).getContents();
			Select rolenameslt = new Select(GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_ddlRoles"));
			rolenameslt.selectByVisibleText(RoleName_Data);

		}
	}

}
