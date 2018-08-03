package Clinion_EDC_Study;

import java.io.FileInputStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;
import jxl.Sheet;
import jxl.Workbook;

public class AnnotationCRFBookExtraction {

	public AnnotationCRFBookExtraction() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(GlobelMethods.driver);
	Actions action = new Actions(GlobelMethods.driver);

	// ----Annotation CRF Book Extraction With Filtered Data---//
	public void AnnotationCRFBookExtraction_WithFilter() throws Exception {
		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Annotation CRF Book Extraction").click();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("AnnotationCRFBookExtraction");

		int RowsCount = st.getRows();

		for (int i = 1; i < RowsCount; i++) {

			String SiteId_Data = st.getCell(1, i).getContents();
			String Subject_Data = st.getCell(2, i).getContents();

			// ----Select Site---//
			Select dropSiteval = new Select(GWait.Wait_GetElementById("ctl07_ddlAnnotatedSite", 120));
			dropSiteval.selectByVisibleText(SiteId_Data);

			// ----Select Subject----//
			Select dropdnSubjctval = new Select(GWait.Wait_GetElementById("ctl07_ddlAnnotatedSubject", 120));
			dropdnSubjctval.selectByVisibleText(Subject_Data);

			// ---Click Generate CRF Book extraction---//
			GWait.Wait_GetElementById("ctl07_btnAnnotatedCRF", 120).click();

		}
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();

	}

	// ----Annotation CRF Book extraction with all data---//
	public void AnnotationCRFBookExtraction_All() throws Exception {

		GlobelMethods.Datamanager_Login();
		Thread.sleep(3000);
		WebElement element = GWait.Wait_GetElementByXpath("//tr/td[18]/table/tbody/tr/td[1]/a", 120);
		action.moveToElement(element).build().perform();
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Annotation CRF Book Extraction").click();

		// ----Select Site---//
		Select dropSiteval = new Select(GWait.Wait_GetElementById("ctl07_ddlAnnotatedSite", 120));
		dropSiteval.selectByVisibleText("ALL");

		// ----Select Subject----//
		Select dropdnSubjctval = new Select(GWait.Wait_GetElementById("ctl07_ddlAnnotatedSubject", 120));
		dropdnSubjctval.selectByVisibleText("ALL");

		// ---Click Generate CRF Book extraction---//
		GWait.Wait_GetElementById("ctl07_btnAnnotatedCRF", 120).click();
		Thread.sleep(3000);
		GWait.Wait_GetElementByLinkText("Logout").click();
	}
}
