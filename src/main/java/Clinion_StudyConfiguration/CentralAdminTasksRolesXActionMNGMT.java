package Clinion_StudyConfiguration;

import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class CentralAdminTasksRolesXActionMNGMT {

	public CentralAdminTasksRolesXActionMNGMT() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	public void CARoleXAction() throws Exception {

		GlobelMethods.Superadmin_Login();

		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[7]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Roles X Actions Management").click();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("CARoleXFeatureAction");
		int rowcount = st.getRows();
		System.out.println("No. of rows: " + rowcount);
		for (int i = 2; i < rowcount - 2; i++) {
			String RoleName_Data = st.getCell(3, i).getContents();
			Select rolenameslt = new Select(GWait.Wait_GetElementById("ctl18_ddlRoles"));
			rolenameslt.selectByVisibleText(RoleName_Data);
			int colcount = st.getColumns();
			// ---need to take the value 28 as per feature X 2---//
			for (int j = 2; j <= 28;) {
				WebElement imgclick = GWait.Wait_GetElementByXpath("//div/table/tbody/tr[" + j + "]/td[1]/a/img");
				imgclick.click();
					List<WebElement> dsbllink = GlobelMethods.driver.findElements(By.xpath("//a[text()='Disabled']"));
					for (WebElement webElement1 : dsbllink) {
						if (webElement1.getText().equals("Disabled")) {
						webElement1.click();
						}
						break;
					}

				j = j + 2;
			}

		}
		Thread.sleep(1000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}

}
