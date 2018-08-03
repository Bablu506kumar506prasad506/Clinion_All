package Clinion_Execution;

import java.io.FileInputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Clinion_GlobalMethod.GlobelMethods;
import Clinion_StudyWorkflow.StudyAdminTasksRolesXFeatureMNGMT;
import jxl.Sheet;
import jxl.Workbook;

public class Clinion_StudyWorkflowExecution {
	
	@BeforeMethod
	public void BraoserInit() throws Exception{
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");
		
		String url_link = st.getCell(0, 2).getContents();
		String BrowaerName_Data = st.getCell(1, 2).getContents();
		
		GlobelMethods.LauncherBrowser(BrowaerName_Data, url_link);
		
	}
	
	/*@Test
	public void SARoleManagement() throws Exception{
		StudyAdminTasksRolesManagement SARM = new StudyAdminTasksRolesManagement();
		SARM.StudyAdmin_RoleManagement();
	}*/
	
	@Test
	public void SARoleXFeatureMngmt() throws Exception{
		StudyAdminTasksRolesXFeatureMNGMT SARXFM = new StudyAdminTasksRolesXFeatureMNGMT();
		SARXFM.StudyAdmin_RoleXFeatureManagmt();
	}
	
	
	
	@AfterMethod
	public void QuitBrowser(){
		GlobelMethods.driver.quit();
	}

}
