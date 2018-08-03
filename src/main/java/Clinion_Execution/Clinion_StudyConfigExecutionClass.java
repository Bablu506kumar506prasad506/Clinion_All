package Clinion_Execution;

import java.io.FileInputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Clinion_GlobalMethod.GlobelMethods;
import Clinion_StudyConfiguration.CentralAdminTasksCreateUsersClass;
import Clinion_StudyConfiguration.CentralAdminTasksRolesManagement;
import Clinion_StudyConfiguration.CentralAdminTasksRolesXActionMNGMT;
import Clinion_StudyConfiguration.CentralAdminTasksRolesXFeatureMNGMT;
import Clinion_StudyConfiguration.CentralAdminTasksUserMngmtPermission;
import Clinion_StudyConfiguration.StudyConfiguration_ConditionalBranching;
import Clinion_StudyConfiguration.forgotpasswordClass;
import jxl.Sheet;
import jxl.Workbook;

public class Clinion_StudyConfigExecutionClass {
	
	@BeforeMethod
	public void BraoserInit() throws Exception{
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");
		
		String url_link = st.getCell(0, 1).getContents();
		String BrowaerName_Data = st.getCell(1, 2).getContents();
		
		GlobelMethods.LauncherBrowser(BrowaerName_Data, url_link);
		
	}
	
	/*@Test (priority=0)
	public static void forgotpassword_Methd() throws Exception{
		forgotpasswordClass fr = new forgotpasswordClass();
		fr.forgotpasswordM();
	}
	
	@Test
	public void CAAdminTask_Methd() throws Exception{
		
		CentralAdminTasksRolesManagement CATRM = new CentralAdminTasksRolesManagement();
		CATRM.CARoleManagement();
	}
	
	@Test
	public void RoleXFeatureMNGMT_Mthd() throws Exception{
		CentralAdminTasksRolesXFeatureMNGMT RXFM = new CentralAdminTasksRolesXFeatureMNGMT();
		RXFM.RoleXFeature();
	}
	
	@Test
	public void RoleXActionMNGMT_Mthd() throws Exception{
		CentralAdminTasksRolesXActionMNGMT RXAM = new CentralAdminTasksRolesXActionMNGMT();
		RXAM.CARoleXAction();
	}
	
	@Test
	public void CAUserManagementPermson() throws Exception{
		CentralAdminTasksUserMngmtPermission CAUMP = new CentralAdminTasksUserMngmtPermission();
		CAUMP.CAUserMNGMTPRMSON();
	}
	
	@Test
	public void CreateUser() throws Exception{
		CentralAdminTasksCreateUsersClass CACU = new CentralAdminTasksCreateUsersClass();
		CACU.CACreateUsers();
	}*/
	
	
	
	/*@Test
	public void SC_ConditionalBranching() throws Exception{
		StudyConfiguration_ConditionalBranching SCCB = new StudyConfiguration_ConditionalBranching();
		SCCB.Config_ConditionalBranching();
	}*/
	
	@AfterMethod
	public void quitToBrowser(){
		GlobelMethods.driver.quit();
	}

}
