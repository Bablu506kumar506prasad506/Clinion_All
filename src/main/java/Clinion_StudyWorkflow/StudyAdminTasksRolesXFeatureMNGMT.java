package Clinion_StudyWorkflow;

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

public class StudyAdminTasksRolesXFeatureMNGMT {
	
	public StudyAdminTasksRolesXFeatureMNGMT() {
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
		System.out.println("No. Of Rows count: "+RowsCount);
		for (int i = 1; i <= RowsCount-1; i++) {
			Thread.sleep(1500);
			String RoleName_Data = st.getCell(1, i).getContents();
			Select rolenameslt = new Select(GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_ddlRoles"));
			rolenameslt.selectByVisibleText(RoleName_Data);
			
			int k = 2;
			int Colmcount = st.getColumns();
				Thread.sleep(3000);
				ArrayList<WebElement> cells = (ArrayList<WebElement>) GlobelMethods.driver.findElements(By.cssSelector("#ctl00_ContentPlaceHolder1_gvFeatures>tbody>tr"));
				System.out.println("Soze of rows: "+cells.size());
				int test = cells.size()-1;
				System.out.println(test);
				for (int j = 0; j < cells.size()-1; j++) {
					String FeatureName_Data = st.getCell(2, i).getContents();
					String[] FeatureNameSplit = FeatureName_Data.split(",");
					for (String cell : FeatureNameSplit) {
							System.out.println("Excel Value: "+cell);
							WebElement checktext = GWait.Wait_GetElementByXpath("//tr[2]/td/div/table/tbody/tr["+k+"]/td[2]", 120);
							if (checktext.getText().equalsIgnoreCase(cell)) {
								if (k<=9) {
									GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_gvFeatures_ctl0"+k+"_chkSelect").click();
									break;
								}else if (k>=10) {
									GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_gvFeatures_ctl"+k+"_chkSelect").click();
									break;
								}
						}
				}
				
				k++;
			}
			
			Thread.sleep(1500);
			GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnAssign", 120).click();
			Thread.sleep(1500);
			GWait.Wait_GetElementById("ctl00_ContentPlaceHolder1_btnPublish", 120).click();
			
		}
		
		Thread.sleep(1000);
		GWait.Wait_GetElementByLinkText("Logout").click();
		
	}

}
