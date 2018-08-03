package Clinion_StudyConfiguration;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class CentralAdminTasksUserMngmtPermission {

	public CentralAdminTasksUserMngmtPermission() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	public void CAUserMNGMTPRMSON() throws Exception {

		GlobelMethods.Superadmin_Login();

		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[7]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Users Management Permissions").click();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("CAUserManagementPermission");
		int rowcount = st.getRows();
		System.out.println("No. of rows count: " + rowcount);
		for (int i = 1; i < rowcount; i++) {
			int colmncount = st.getColumns();
			for (int j = 1; j < colmncount - 1; j++) {
				String Rolename_Data = st.getCell(j, i).getContents();
				String UMP_Data = st.getCell(j + 1, i).getContents();

				String[] text = UMP_Data.split(",");

				String UPM_Data1 = text[0];
				String UPM_Data2 = text[1];
				String UPM_Data3 = text[2];

				Select roleselct = new Select(GWait.Wait_GetElementById("ctl18_ddlRoles"));
				roleselct.selectByVisibleText(Rolename_Data);
				int k = 2;
				ArrayList<WebElement> cells = (ArrayList<WebElement>) GlobelMethods.driver
						.findElements(By.cssSelector("#ctl18_gvRoles>tbody>tr>td"));
				for (WebElement cell : cells) {
					System.out.println(cell.getText());
					if (cell.getText().isEmpty()) {
						System.out.println("do nothing");
					} else {
						for (int l = 0; l < text.length; l++) {
							System.out.println(text[l]);
							if (cell.getText().equalsIgnoreCase(text[l])) {
								if (k <= 9) {
									GWait.Wait_GetElementById("ctl18_gvRoles_ctl0" + k + "_chkSelect1").click();
									break;
								} else if (k >= 10) {
									GWait.Wait_GetElementById("ctl18_gvRoles_ctl" + k + "_chkSelect1").click();
									break;
								}

							}
						}
						k++;
					}

				}

			}
			Thread.sleep(500);
			GWait.Wait_GetElementById("ctl18_btnAssign").click();
		}
		Thread.sleep(1000);
		GWait.Wait_GetElementByLinkText("Logout").click();

	}

}
