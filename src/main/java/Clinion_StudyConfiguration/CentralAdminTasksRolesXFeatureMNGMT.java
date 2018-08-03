package Clinion_StudyConfiguration;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class CentralAdminTasksRolesXFeatureMNGMT {

	public CentralAdminTasksRolesXFeatureMNGMT() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	public void RoleXFeature() throws Exception {
		GlobelMethods.Superadmin_Login();

		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[7]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Roles X Features Management").click(); 
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("CARoleXFeatureAction");

		String ByRoleAndFeatur_Data = st.getCell(1, 2).getContents();
		

		if (ByRoleAndFeatur_Data.equals("By Role")) {
			int rowcount = st.getRows();
			System.out.println(rowcount);
			// ---Select the Role Name---//
			for (int i = 2; i < rowcount-2; i++) {
				String RoleName_Data = st.getCell(3, i).getContents();
				Select rolenameslt = new Select(GWait.Wait_GetElementById("ctl18_ddlRoles"));
				rolenameslt.selectByVisibleText(RoleName_Data);
				
				int k = 2;
				int Colmcount = st.getColumns();
				for (int j = 4; j <= Colmcount-1; j++) {
						ArrayList<WebElement> cells = (ArrayList<WebElement>) GlobelMethods.driver.findElements(By.cssSelector("#ctl18_gvFeatures>tbody>tr>td"));
						for (WebElement cell : cells) {
							System.out.println(cell.getText());
							String FeatureName_Data = st.getCell(j, i).getContents();
							if (cell.getText().equalsIgnoreCase(FeatureName_Data)) {
								if (k<=9) {
									GWait.Wait_GetElementById("ctl18_gvFeatures_ctl0"+k+"_chkSelect").click();
									break;
								}else if (k>=10) {
									GWait.Wait_GetElementById("ctl18_gvFeatures_ctl"+k+"_chkSelect").click();
									break;
								}
								
							}
						}
					k++;
				}
				GWait.Wait_GetElementById("ctl18_gvFeatures_ctl02_chkSelect").click();
				Thread.sleep(500);
				GWait.Wait_GetElementById("ctl18_btnAssign", 120).click();
			}
			Thread.sleep(1000);
			GWait.Wait_GetElementByLinkText("Logout").click();
		}else {
			// ---Select the Feature---//
			int Colmcount = st.getColumns();
			GWait.Wait_GetElementById("ctl18_rdbtnFeatureName", 120).click();
			int j=2;
			for (int i = 4; i < Colmcount; i++) {
				String FeatureName_Data = st.getCell(i, j).getContents();
				Select rolenameslt = new Select(GWait.Wait_GetElementById("ctl18_ddlFeatures"));
				rolenameslt.selectByVisibleText(FeatureName_Data);
			}
			j++;
		}
		Thread.sleep(1000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

}
