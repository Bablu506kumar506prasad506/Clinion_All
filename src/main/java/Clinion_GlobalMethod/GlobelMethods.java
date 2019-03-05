package Clinion_GlobalMethod;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import Clinion_EDC_Study.CRFDataExtraction;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import Clinion_Execution.Clinion_EDCStudyExecutionClass;
import jxl.Sheet;
import jxl.Workbook;

public class GlobelMethods {
	public static WebDriver driver;

	public GlobelMethods() {
		PageFactory.initElements(driver, this);
	}

	static Clinion_WaitMethod GWait = new Clinion_WaitMethod(driver);
	Actions action = new Actions(GlobelMethods.driver);
	
	// --------Browser Initialization--------------//
	public static void LauncherBrowser(String BrowserName, String url) {
		if (BrowserName.equals("firefox")) {
			/*System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/geckodriver.exe");*/
			driver = new FirefoxDriver();
		} else if (BrowserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (BrowserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.get(url);
	}

	// ------Accept Alert ------//

	public static void alertaccept() throws Exception {

		Alert al = driver.switchTo().alert();
		String msgalert = al.getText();
		al.accept();

	}

	public static void isAlertPresent() throws Exception {
		Thread.sleep(1000);
		try {
			driver.switchTo().alert();
			System.out.println(" Alert Present");
			alertaccept();
		} catch (NoAlertPresentException e) {
			System.out.println("No Alert Present");
		}
	}

	public static void AcceptDoenloadPopup() throws Exception {

		Thread.sleep(2000);

		Robot r = new Robot();

		// A short pause, just to be sure that OK is selected
		Thread.sleep(3000);

		r.keyPress(KeyEvent.VK_ALT);
		r.keyPress(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_S);
		r.keyRelease(KeyEvent.VK_ALT);

		System.out.println("Select Save As Option");

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

		System.out.println("Enter Key is pressed");
	}

	// ---Open New Tab----//
	public static void openNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");
	}

	public static void CloseNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		// Switch first tab
		driver.switchTo().defaultContent();
		driver.close();
	}

	// ------------Login-------------//

	public static void Superadmin_Login() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");

		String Username_Data = st.getCell(2, 1).getContents();
		String Password_Data = st.getCell(3, 1).getContents();

		GWait.Wait_GetElementById("txtUserName", 120).sendKeys(Username_Data);
		GWait.Wait_GetElementById("txtPassword", 120).sendKeys(Password_Data);
		GWait.Wait_GetElementByXpath("//input[@type='submit']", 120).click();
	}
	
	public static void StudyAdmin_Login() throws Exception {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");

		String Username_Data = st.getCell(2, 2).getContents();
		String Password_Data = st.getCell(3, 2).getContents();

		GWait.Wait_GetElementById("txtUserName", 120).sendKeys(Username_Data);
		GWait.Wait_GetElementById("txtPassword", 120).sendKeys(Password_Data);
		GWait.Wait_GetElementByXpath("//input[@type='submit']", 120).click();
		Thread.sleep(3000);
	}

	public static void Datamanager_Login() throws Exception {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");

		String Username_Data = st.getCell(2, 2).getContents();
		String Password_Data = st.getCell(3, 2).getContents();
		GWait.Wait_GetElementById("txtUserName", 120).sendKeys(Username_Data);
		GWait.Wait_GetElementById("txtPassword", 120).sendKeys(Password_Data);
		GWait.Wait_GetElementByXpath("//input[@type='submit']", 120).click();
	}
	
	public static void Datamanager_Login3point1() throws Exception {
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("Login_Data");

		String Username_Data = st.getCell(2, 2).getContents();
		String Password_Data = st.getCell(3, 2).getContents();
		GWait.Wait_GetElementById("txtUserName", 120).sendKeys(Username_Data);
		GWait.Wait_GetElementById("txtPassword", 120).sendKeys(Password_Data);
		GWait.Wait_GetElementByXpath("//input[@type='submit']", 120).click();
	}
	
	

	// ------------Forgot Password------------//
	public static void ForgotPassword() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet st = wb.getSheet("ForgotPassword_Data");
		Sheet st1 = wb.getSheet("Login_Data");

		String forgotpassUserName_data = st.getCell(1, 1).getContents();
		String SetNewPassword = st.getCell(2, 1).getContents();
		String Emaillink_data = st.getCell(3, 1).getContents();
		String emailPass_data = st.getCell(4, 1).getContents();

		String Clinion_url = st1.getCell(0, 1).getContents();

		GWait.Wait_GetElementById("ForgotPassword1_UserName", 120).sendKeys(forgotpassUserName_data);
		GWait.Wait_GetElementById("ForgotPassword1_SubmitButton", 120).click();

		String emailtxt = GWait.Wait_GetElementByXpath(".//*[@id='ForgotPassword1_lblErrmsg']/u", 120).getText();
		System.out.println("email" + emailtxt);
		if (emailtxt.equalsIgnoreCase(Emaillink_data)) {

			String URL = "https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
			GWait.Wait_GetElementByCSS("body").sendKeys(Keys.CONTROL + "t");
			String newTabInstance = driver.getWindowHandle().toString();
			driver.switchTo().window(newTabInstance);
			driver.get(URL);
			WebElement email_field1 = GWait.Wait_GetElementById("identifierId");
			email_field1.sendKeys(Emaillink_data);

			WebElement nextbutton = GWait.Wait_GetElementByXpath("//div[@id='identifierNext']/content/span");
			nextbutton.click();
			WebElement pwd_field1 = GWait.Wait_GetElementByName("password");
			pwd_field1.sendKeys(emailPass_data);
			WebElement nextbutton1 = GWait.Wait_GetElementByXpath("//div[2]/div/div/content/span");
			nextbutton1.click();
			Thread.sleep(4500);
			WebElement link1 = GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
			link1.click();
			List<WebElement> a = driver.findElements(By.xpath("//span/b[text()='Forgot Password Details']"));
			System.out.println(a.size());
			if (a.get(0).getText().equalsIgnoreCase("Forgot Password Details")) {
				a.get(0).click();
				WebElement pass1 = GWait.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div[1]/table/tbody/tr[7]/td");
				System.out.println(pass1.getText());
				String pass2 = pass1.getText();
				String[] passwordSplit = pass2.split(" ");
				System.out.println("1st: " + passwordSplit[0]);
				System.out.println("2nd: " + passwordSplit[1]);
				System.out.println("3rd: " + passwordSplit[2]);

				String finalPass = passwordSplit[2];
				Thread.sleep(2000);
				WebElement emaillogout = GWait.Wait_GetElementByXpath("//div[1]/div/div[5]/div[1]/a/span", 120);
				emaillogout.click();
				WebElement emailsingoutBTN = GWait.Wait_GetElementByXpath("//div/div[5]/div[2]/div[4]/div[2]/a", 120);
				emailsingoutBTN.click();
				Thread.sleep(1000);
				driver.get(Clinion_url);
				// -----Change Password-----//
				GWait.Wait_GetElementById("txtUserName", 120).sendKeys(forgotpassUserName_data);
				GWait.Wait_GetElementById("txtPassword", 120).sendKeys(finalPass);
				GWait.Wait_GetElementById("LoginButton", 120).click();

				// ----Reset Password----//
				GWait.Wait_GetElementById("ChangePassword1_CurrentPassword", 120).sendKeys(finalPass);
				GWait.Wait_GetElementById("ChangePassword1_NewPassword", 120).sendKeys(SetNewPassword);
				GWait.Wait_GetElementById("ChangePassword1_ConfirmNewPassword", 120).sendKeys(SetNewPassword);
				GWait.Wait_GetElementById("ChangePassword1_ChangePasswordPushButton", 120).click();
			}
		}

	}

	public static void ChangePassword() {

	}

	public static String GmailURL = "https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
	public static String ClinionURL = "http://edc-demo.eclinion.com/Default.aspx";
	
	public static void openNewTabWithGmailURL() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(GmailURL);
		/*
		 * String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		 * driver.findElement(By.linkText(GmailURL)).sendKeys(
		 * selectLinkOpeninNewTab);
		 */
	}

	public static void openNewTabWithClinionURL() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");

		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(1));
		driver.navigate().to(ClinionURL);

	}

	public static void openOldTabL() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));

	}

	public static void VerificationCode() throws Exception {
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		
		driver.switchTo().window(tabs.get(2));
		driver.get(GmailURL);
		
		
		Thread.sleep(5000);
		WebElement link1 = GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
		link1.click();
		driver.navigate().refresh();
		List<WebElement> a = driver
				.findElements(By.xpath("//span/b[text()='Send Verification Code']"));
		System.out.println(a.size());

		if (a.get(0).getText().equalsIgnoreCase("Send Verification Code")) {
			a.get(0).click();
			
			WebElement VerifictonCode1 = GWait
					.Wait_GetElementByXpath("//div[3]/div[3]/div[1]/table/tbody/tr[4]/td");
			System.out.println(VerifictonCode1.getText());
			String VerifictonCode2 = VerifictonCode1.getText();
			String[] VerifictonCodeSplit = VerifictonCode2.split(" ");
			System.out.println("1st: " + VerifictonCodeSplit[0]);
			System.out.println("2nd: " + VerifictonCodeSplit[1]);
			System.out.println("3rd: " + VerifictonCodeSplit[2]);
			System.out.println("3rd: " + VerifictonCodeSplit[3]);
			System.out.println("3rd: " + VerifictonCodeSplit[4]);
			System.out.println("3rd: " + VerifictonCodeSplit[5]);
			// System.out.println("1st: " + UsernameSplit[3]);

			finalVerifictonCode = VerifictonCodeSplit[5];

			System.out.println("Final String: " + finalVerifictonCode);
			
			Thread.sleep(2000);
			driver.switchTo().window(tabs.get(2)).close();
			
			ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs1.get(1));
			
			GWait.Wait_GetElementById("txtVerificationCode", 120).sendKeys(finalVerifictonCode);
			GWait.Wait_GetElementById("btnSubmitVerify", 120).click();
			
			Thread.sleep(2000);
			
		}
		
	}
	
	JavascriptExecutor js = (JavascriptExecutor)GlobelMethods.driver;
//	public static String PNL_Data;
	public void PanelWiseMainDataVerification_M() throws Exception {
		//------Data verification from excel and eCRF page------//
		String DNLDfilename = CRFDataExtraction.PNL_Data.replaceAll("\\s+", "");
		
		System.out.println("Donloaded page data: "+DNLDfilename);
		File fs = new File("C:\\Users\\bablu.p\\Downloads\\"+DNLDfilename+".xls");
		FileInputStream fis = new FileInputStream(fs);
		XSSFWorkbook wb1 = new XSSFWorkbook(fis);
		XSSFSheet st1 = wb1.getSheetAt(0);
		
		XSSFRow rows1 = st1.getRow(1);
		XSSFCell SiteNameV = rows1.getCell(1);
		String SiteName = SiteNameV.toString();
		XSSFCell PatIDV = rows1.getCell(2);
		String PatID = PatIDV.toString();
		
		js.executeScript("window.scrollBy(1000,0)");
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Data Capture").click();
		Select eleSite = new Select(GWait.Wait_GetElementById("ctl07_ddlSites", 120));
		eleSite.selectByVisibleText(SiteName);
		Thread.sleep(500);
		Select elePTID = new Select(GWait.Wait_GetElementById("ctl07_ddlSubjectId", 120));
		elePTID.selectByVisibleText(PatID);
		Thread.sleep(500);
		WebElement eleGo = GWait.Wait_GetElementById("ctl07_btnFilter", 120);
		js.executeScript("arguments[0].click();",eleGo);
		
		GWait.Wait_GetElementByLinkText(PatID).click();
		
		XSSFCell VISTNAMEV = rows1.getCell(5);
		String VISTNAME = VISTNAMEV.toString();
		String TrimVisitname = " "+VISTNAME;
		Thread.sleep(3000);
		int sizeOfTable = GlobelMethods.driver.findElements(By.xpath("//*[@id='lstAudit']/table")).size();
		System.out.println(sizeOfTable);
		for (int k = 1; k < sizeOfTable; k++) {
			WebElement visit1Name = GWait.Wait_GetElementByXpath("//*[@id=\"lstAudit\"]/table["+k+"]/tbody/tr[1]/td/div[1]", 120);
			
			String regex = "&nbsp;";
			String removeStartingspaceVSTNAM = visit1Name.getText().replace(regex, "");
			
			System.out.println(visit1Name.getText());
			System.out.println(TrimVisitname);
			if (visit1Name.getText().equals(TrimVisitname)) {
				Thread.sleep(3000);
				GWait.Wait_GetElementById("imgdivScrrenslist"+k+".00v", 120).click();
				break;
			}
		}
		
		XSSFCell PAGENAMEV = rows1.getCell(7);
		String PAGENAME = PAGENAMEV.toString();
		GWait.Wait_GetElementByLinkText(PAGENAME).click();
		
		eCRFDataVerification();
	}
	String newString;
	public void PageWiseMainDataVerification_M() throws Exception {
		//------Data verification from excel and eCRF page------//
		String DNLDfilename = CRFDataExtraction.PNL_Data.replaceAll("\\s+", "");
		String pagename = DNLDfilename.replaceAll("/", "");
		if (pagename.length() > 29)
		{
			newString = pagename.substring(0, 29);
			System.out.println(newString);
		}
		else
		{
			newString = pagename;
//			System.out.println(newString);
		}
		System.out.println("Donloaded page data: "+newString);
		File fs = new File("C:\\Users\\bablu.p\\Downloads\\"+newString+".xlsx");
		FileInputStream fis = new FileInputStream(fs);
		XSSFWorkbook wb1 = new XSSFWorkbook(fis);
		XSSFSheet st1 = wb1.getSheetAt(0);
		
		XSSFRow rows1 = st1.getRow(1);
		XSSFCell SiteNameV = rows1.getCell(1);
		String SiteName = SiteNameV.toString();
		XSSFCell PatIDV = rows1.getCell(2);
		String PatID = PatIDV.toString();
		
		js.executeScript("window.scrollBy(1000,0)");
		Thread.sleep(500);
		GWait.Wait_GetElementByLinkText("Data Capture").click();
		Select eleSite = new Select(GWait.Wait_GetElementById("ctl07_ddlSites", 120));
		eleSite.selectByVisibleText(SiteName);
		Thread.sleep(500);
		Select elePTID = new Select(GWait.Wait_GetElementById("ctl07_ddlSubjectId", 120));
		elePTID.selectByVisibleText(PatID);
		Thread.sleep(500);
		WebElement eleGo = GWait.Wait_GetElementById("ctl07_btnFilter", 120);
		js.executeScript("arguments[0].click();",eleGo);
		
		GWait.Wait_GetElementByLinkText(PatID).click();
		
		XSSFCell VISTNAMEV = rows1.getCell(6);
		String VISTNAME = VISTNAMEV.toString();
		String TrimVisitname = " "+VISTNAME;
		Thread.sleep(3000);
		int sizeOfTable = GlobelMethods.driver.findElements(By.xpath("//*[@id='lstAudit']/table")).size();
		System.out.println(sizeOfTable);
		for (int k = 1; k < sizeOfTable; k++) {
			WebElement visit1Name = GWait.Wait_GetElementByXpath("//*[@id=\"lstAudit\"]/table["+k+"]/tbody/tr[1]/td/div[1]", 120);
			
			String regex = "&nbsp;";
			String removeStartingspaceVSTNAM = visit1Name.getText().replace(regex, "");
			
			System.out.println(visit1Name.getText());
			System.out.println(TrimVisitname);
			if (visit1Name.getText().equals(TrimVisitname)) {
				Thread.sleep(3000);
				GWait.Wait_GetElementById("imgdivScrrenslist"+k+".00v", 120).click();
				break;
			}
		}
		
		/*XSSFCell PAGENAMEV = rows1.getCell(7);
		String PAGENAME = PAGENAMEV.toString();*/
		System.out.println(CRFDataExtraction.PNL_Data);
		GWait.Wait_GetElementByLinkText(CRFDataExtraction.PNL_Data).click();
		//----Main Verification method-----//
		/*String DNLDfilename1 = CRFDataExtraction.PNL_Data.replaceAll("\\s+", "");
		System.out.println("Donloaded page data: "+DNLDfilename1);
		File fs1 = new File("C:\\Users\\bablu.p\\Downloads\\"+DNLDfilename1+".xls");
		FileInputStream fis1 = new FileInputStream(fs1);
		XSSFWorkbook wb11 = new XSSFWorkbook(fis1);
		XSSFSheet st11 = wb11.getSheetAt(0);*/
		
		List<String> PageControlId = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='divforPrint']//*"));
		for (WebElement item : elements) {

			String controlType = item.getAttribute("type");
			String tagname = item.getTagName();
			if (controlType == null || controlType.trim() == "") {
				continue;
			}
			String controlId = item.getAttribute("id");

			if (controlType.equalsIgnoreCase("text") || controlType.equalsIgnoreCase("radio")
					|| controlType.equalsIgnoreCase("select-one") || controlType.equalsIgnoreCase("checkbox")||tagname.equalsIgnoreCase("textarea")) {
				// System.out.println(controlId);
				// System.out.println(controlType);
				if (controlType.equalsIgnoreCase("radio")) {
					WebElement parent = item.findElement(By.xpath("../../../../.."));
					String controlIdAndType = parent.getAttribute("id") + "~" + controlType;
					boolean controlExists = false;
					for (String existingControlId : PageControlId) {
						if (existingControlId.equalsIgnoreCase(controlIdAndType)) {
							controlExists = true;
						}
					}
					if (!controlExists) {

						PageControlId.add(controlIdAndType);
					}
				} else if (controlType.equalsIgnoreCase("checkbox")) {
					WebElement parentcheckbox = item.findElement(By.xpath("../../../../.."));
					String controlIdAndTypecheck = parentcheckbox.getAttribute("id") + "~" + controlType;
					boolean controlExistscheck = false;
					for (String existingControlIdcheck : PageControlId) {

						if (existingControlIdcheck.equalsIgnoreCase(controlIdAndTypecheck)) {
							controlExistscheck = true;
						}
					}
					if (!controlExistscheck) {

						PageControlId.add(controlIdAndTypecheck);
					}
				} else {
					PageControlId.add(controlId + "~" + controlType);
				}
			}
		}
		int numberOfRow = st1.getLastRowNum();
		int j = 0;
		int count = 0;
		System.out.println("Num Of Rows in Excel: " + numberOfRow);
		for (int i = 1; i <= PageControlId.size(); i++) {

			XSSFRow rows11 = st1.getRow(i);
			int numOfCol = rows11.getLastCellNum();
			
			System.out.println("Num Of Columns in Excel: " + numOfCol);
			for (j = 0; j <= numOfCol; j++) {
				
				XSSFCell controlDataV = rows11.getCell(9);
				if (controlDataV==null) {
					controlData = "";
				}else {
					controlData = controlDataV.toString();
				}
				
//				controlData.replace("null", "");
				try {
					String controlIdAndType = PageControlId.get(count);
					String controlId = controlIdAndType.split("~")[0];
					String controlType = controlIdAndType.split("~")[1];
					WebElement element = driver.findElement(By.id(controlId));

					// System.out.println("controlId: "+controlId);
					// System.out.println("controlType: "+controlType);
					System.out.println("ExcelCellData: " + controlData);

					switch (controlType) {
					case "text":
						try {
							try {
								System.out.println("controlData: " + element.getAttribute("value"));
								assertEquals(controlData, element.getAttribute("value"));
								System.out.println("Successfully done!!!");
							} catch (Error e) {
								System.out.println("Un-Successfully done Please Verify !!!");
								verificationErrors.append(e.toString());
							}

						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "radio":
						try {
							List<WebElement> RadioButtonList = element.findElements(By.xpath(".//*"));
							for (int radio = 0; radio < RadioButtonList.size(); radio++) {
								if (RadioButtonList.get(radio).getAttribute("type") != null
										&& RadioButtonList.get(radio).getAttribute("type").equalsIgnoreCase("radio")) {

									if (RadioButtonList.get(radio).isSelected() && RadioButtonList.get(radio)
											.getAttribute("value").equalsIgnoreCase(controlData.toString())) {
										System.out.println(
												"controlData: " + RadioButtonList.get(radio).getAttribute("value"));
										System.out.println("Successfully done!!!");
									} else if (RadioButtonList.get(radio).isSelected() && !RadioButtonList.get(radio)
											.getAttribute("value").equalsIgnoreCase(controlData.toString())) {
										System.out.println(
												"controlData: " + RadioButtonList.get(radio).getAttribute("value"));
										System.out.println("Un-Successfully done Please Verify !!!");
									}
								}
							}

						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "checkbox":
						try {
							List<WebElement> CheckBoxsList = driver.findElements(By.xpath(
									"//table[@id='" + controlId + "']/tbody/tr/td/span/input[@type='checkbox']"));
							List<String> list = Lists.newArrayList(Splitter.on(",").trimResults().split(controlData.toString()));
							for (int checkBox = 0; checkBox < CheckBoxsList.size(); checkBox++) {
								String xpathForInput = generateXPATH(CheckBoxsList.get(checkBox), "")
										.replaceAll("input", "label");
								WebElement labelElement = driver.findElement(By.xpath(xpathForInput));
								if (CheckBoxsList.get(checkBox).isSelected() && list.contains(labelElement.getText().toLowerCase())) {
									System.out.println("controlData: " + labelElement.getText().toLowerCase());
									System.out.println("Successfully done!!!");
								} else if (CheckBoxsList.get(checkBox).isSelected()
										&& !list.contains(labelElement.getText().toLowerCase())) {
									System.out.println("controlData: " + labelElement.getText());
									System.out.println("Un-Successfully done Please Verify !!!");
								}
							}
						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "select-one":
						try {
							if (element.getAttribute("type").equalsIgnoreCase("select-one")) {
								Select se = new Select(driver.findElement(By.id(controlId)));
								WebElement option = se.getFirstSelectedOption();
								System.out.println("controlData: " + option.getText());
								if (controlData.toString().equalsIgnoreCase(option.getText())) {
									System.out.println("Successfully done!!!");
								} else {
									System.out.println("Un-Successfully done Please Verify !!!");
								}

							} else {
								String checkOtherTextBox = element.getAttribute("style");
								if (!checkOtherTextBox.equalsIgnoreCase("display: none;")) {
									element.sendKeys(controlData.toString());
								}
							}

						} catch (Exception e) {
							e.getStackTrace();
						}
						break;
					}

				} catch (Exception e) {
					e.getMessage();
				}
				count++;
				break;
			}

			Thread.sleep(3000);
//			driver.findElement(By.id("btnNext")).click();
		
		}
//		eCRFDataVerification();
	}
	
	static PrintStream verificationErrors;
	static String controlData;
	public static void eCRFDataVerification() throws Exception {
		String DNLDfilename = CRFDataExtraction.PNL_Data.replaceAll("\\s+", "");
		System.out.println("Donloaded page data: "+DNLDfilename);
		File fs = new File("C:\\Users\\bablu.p\\Downloads\\"+DNLDfilename+".xls");
		FileInputStream fis = new FileInputStream(fs);
		XSSFWorkbook wb1 = new XSSFWorkbook(fis);
		XSSFSheet st1 = wb1.getSheetAt(0);
		
		int numberOfRow = st1.getLastRowNum();
		System.out.println("Num Of Rows in Excel: " + numberOfRow);
		for (int i = 1; i <= numberOfRow; ) {

			List<String> PageControlId = new ArrayList<String>();
			List<WebElement> elements = driver.findElements(By.xpath("//*[@id='divforPrint']//*"));
			for (WebElement item : elements) {

				String controlType = item.getAttribute("type");
				String tagname = item.getTagName();
				if (controlType == null || controlType.trim() == "") {
					continue;
				}
				String controlId = item.getAttribute("id");

				if (controlType.equalsIgnoreCase("text") || controlType.equalsIgnoreCase("radio")
						|| controlType.equalsIgnoreCase("select-one") || controlType.equalsIgnoreCase("checkbox")||tagname.equalsIgnoreCase("textarea")) {
					// System.out.println(controlId);
					// System.out.println(controlType);
					if (controlType.equalsIgnoreCase("radio")) {
						WebElement parent = item.findElement(By.xpath("../../../../.."));
						String controlIdAndType = parent.getAttribute("id") + "~" + controlType;
						boolean controlExists = false;
						for (String existingControlId : PageControlId) {
							if (existingControlId.equalsIgnoreCase(controlIdAndType)) {
								controlExists = true;
							}
						}
						if (!controlExists) {

							PageControlId.add(controlIdAndType);
						}
					} else if (controlType.equalsIgnoreCase("checkbox")) {
						WebElement parentcheckbox = item.findElement(By.xpath("../../../../.."));
						String controlIdAndTypecheck = parentcheckbox.getAttribute("id") + "~" + controlType;
						boolean controlExistscheck = false;
						for (String existingControlIdcheck : PageControlId) {

							if (existingControlIdcheck.equalsIgnoreCase(controlIdAndTypecheck)) {
								controlExistscheck = true;
							}
						}
						if (!controlExistscheck) {

							PageControlId.add(controlIdAndTypecheck);
						}
					} else {
						PageControlId.add(controlId + "~" + controlType);
					}
				}
			}
			XSSFRow rows1 = st1.getRow(i);
			int numOfCol = rows1.getLastCellNum();
			int count = 0;
			System.out.println("Num Of Columns in Excel: " + numOfCol);
			for (int j = 0; j <= numOfCol; j++) {
				
				XSSFCell controlDataV = rows1.getCell(j+11);
				if (controlDataV==null) {
					controlData = "";
				}else {
					controlData = controlDataV.toString();
				}
				
//				controlData.replace("null", "");
				try {
					String controlIdAndType = PageControlId.get(j);
					String controlId = controlIdAndType.split("~")[0];
					String controlType = controlIdAndType.split("~")[1];
					WebElement element = driver.findElement(By.id(controlId));

					// System.out.println("controlId: "+controlId);
					// System.out.println("controlType: "+controlType);
					System.out.println("ExcelCellData: " + controlData);

					switch (controlType) {
					case "text":
						try {
							try {
								System.out.println("controlData: " + element.getAttribute("value"));
								assertEquals(controlData, element.getAttribute("value"));
								System.out.println("Successfully done!!!");
							} catch (Error e) {
								System.out.println("Un-Successfully done Please Verify !!!");
								verificationErrors.append(e.toString());
							}

						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "radio":
						try {
							List<WebElement> RadioButtonList = element.findElements(By.xpath(".//*"));
							for (int radio = 0; radio < RadioButtonList.size(); radio++) {
								if (RadioButtonList.get(radio).getAttribute("type") != null
										&& RadioButtonList.get(radio).getAttribute("type").equalsIgnoreCase("radio")) {

									if (RadioButtonList.get(radio).isSelected() && RadioButtonList.get(radio)
											.getAttribute("value").equalsIgnoreCase(controlData.toString())) {
										System.out.println(
												"controlData: " + RadioButtonList.get(radio).getAttribute("value"));
										System.out.println("Successfully done!!!");
									} else if (RadioButtonList.get(radio).isSelected() && !RadioButtonList.get(radio)
											.getAttribute("value").equalsIgnoreCase(controlData.toString())) {
										System.out.println(
												"controlData: " + RadioButtonList.get(radio).getAttribute("value"));
										System.out.println("Un-Successfully done Please Verify !!!");
									}
								}
							}

						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "checkbox":
						try {
							List<WebElement> CheckBoxsList = driver.findElements(By.xpath(
									"//table[@id='" + controlId + "']/tbody/tr/td/span/input[@type='checkbox']"));
							List<String> list = Lists.newArrayList(Splitter.on(",").trimResults().split(controlData.toString()));
							for (int checkBox = 0; checkBox < CheckBoxsList.size(); checkBox++) {
								String xpathForInput = generateXPATH(CheckBoxsList.get(checkBox), "")
										.replaceAll("input", "label");
								WebElement labelElement = driver.findElement(By.xpath(xpathForInput));
								if (CheckBoxsList.get(checkBox).isSelected() && list.contains(labelElement.getText().toLowerCase())) {
									System.out.println("controlData: " + labelElement.getText().toLowerCase());
									System.out.println("Successfully done!!!");
								} else if (CheckBoxsList.get(checkBox).isSelected()
										&& !list.contains(labelElement.getText().toLowerCase())) {
									System.out.println("controlData: " + labelElement.getText());
									System.out.println("Un-Successfully done Please Verify !!!");
								}
							}
						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "select-one":
						try {
							if (element.getAttribute("type").equalsIgnoreCase("select-one")) {
								Select se = new Select(driver.findElement(By.id(controlId)));
								WebElement option = se.getFirstSelectedOption();
								System.out.println("controlData: " + option.getText());
								if (controlData.toString().equalsIgnoreCase(option.getText())) {
									System.out.println("Successfully done!!!");
								} else {
									System.out.println("Un-Successfully done Please Verify !!!");
								}

							} else {
								String checkOtherTextBox = element.getAttribute("style");
								if (!checkOtherTextBox.equalsIgnoreCase("display: none;")) {
									element.sendKeys(controlData.toString());
								}
							}

						} catch (Exception e) {
							e.getStackTrace();
						}
						break;
					}

				} catch (Exception e) {
					e.getMessage();
				}
				count++;
			}

			Thread.sleep(3000);
			driver.findElement(By.id("btnNext")).click();
			break;
		}
	}
	
	private static String generateXPATH(WebElement childElement, String current) {
		String childTag = childElement.getTagName();
		if (childTag.equals("html")) {
			return "/html[1]" + current;
		}
		WebElement parentElement = childElement.findElement(By.xpath(".."));
		List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
		int count = 0;
		for (int i = 0; i < childrenElements.size(); i++) {
			WebElement childrenElement = childrenElements.get(i);
			String childrenElementTag = childrenElement.getTagName();
			if (childTag.equals(childrenElementTag)) {
				count++;
			}
			if (childElement.equals(childrenElement)) {
				return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
			}
		}
		return null;
	}
	
	public static String finalVerifictonCode;
	public static String finalUsrNAM;
	public static String finalPass;

	public static void UserCreationMailFunctionality() throws Exception {

		openNewTabWithGmailURL();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/Clinion.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("CACreateUsers");

		String Emaillink_data = r1.getCell(4, 1).getContents();

		try {

			if (driver.findElement(By.id("identifierId")).isDisplayed()) {

				WebElement email_field11 = GWait.Wait_GetElementById("identifierId");
				email_field11.sendKeys(Emaillink_data);
				Thread.sleep(2500);
				WebElement nextbutton = GWait.Wait_GetElementByXpath("//div[@id='identifierNext']/content/span");
				nextbutton.click();
				WebElement pwd_field1 = GWait.Wait_GetElementByName("password");
				pwd_field1.sendKeys("qa@123456");
				Thread.sleep(2500);
				WebElement nextbutton1 = GWait.Wait_GetElementByXpath("//div[2]/div/div/content/span");
				nextbutton1.click();
				Thread.sleep(4500);
				WebElement link1 = GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
				link1.click();
				
				List<WebElement> a = driver
						.findElements(By.xpath("//span/b[text()='User Creation Details with Clinion']"));
				System.out.println(a.size());

				if (a.get(0).getText().equalsIgnoreCase("User Creation Details with Clinion")) {
					a.get(0).click();

					WebElement Username1 = GWait
							.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div[1]/table/tbody/tr[6]/td");
					System.out.println(Username1.getText());
					String Username2 = Username1.getText();
					String[] UsernameSplit = Username2.split(" ");
					System.out.println("1st: " + UsernameSplit[0]);
					System.out.println("2nd: " + UsernameSplit[1]);
					System.out.println("3rd: " + UsernameSplit[2]);
					// System.out.println("1st: " + UsernameSplit[3]);

					finalUsrNAM = UsernameSplit[2];

					System.out.println("Final String: " + finalUsrNAM);

					WebElement pass1 = GWait
							.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div[1]/table/tbody/tr[7]/td");
					System.out.println(pass1.getText());
					String pass2 = pass1.getText();
					String[] passwordSplit = pass2.split(" ");
					System.out.println("1st: " + passwordSplit[0]);
					System.out.println("2nd: " + passwordSplit[1]);
					System.out.println("3rd: " + passwordSplit[2]);

					finalPass = passwordSplit[2];

					System.out.println("Final String: " + finalPass);

					WebElement link11 = GWait.Wait_GetElementByCSS(".ar6.T-I-J3.J-J5-Ji");
					link11.click();
					/*
					 * Thread.sleep(3000);
					 * driver.findElement(By.cssSelector(".gb_8a.gbii")).click()
					 * ; WebElement signout=driver.findElement(By.id("gb_71"));
					 * Thread.sleep(1000); signout.click();
					 */

					Thread.sleep(2000);
					driver.close();

					// CloseNewTab();
					Thread.sleep(1000);

					openNewTabWithClinionURL();

					// -----Login-----//

					GWait.Wait_GetElementById("txtUserName").sendKeys(finalUsrNAM);
					WebElement sas = GWait.Wait_GetElementById("txtPassword");
					sas.sendKeys(finalPass);
					driver.findElement(By.id("LoginButton")).click();
					Thread.sleep(3000);
					alertaccept();
					VerificationCode();
					
					// ----Reset Password----//
					Sheet r = wb.getSheet("Login_Data");

					String SetNewPassword = r.getCell(3, 1).getContents();

					GWait.Wait_GetElementById("ChangePassword1_CurrentPassword").sendKeys(finalPass);
					GWait.Wait_GetElementById("ChangePassword1_NewPassword").sendKeys(SetNewPassword);
					GWait.Wait_GetElementById("ChangePassword1_ConfirmNewPassword").sendKeys(SetNewPassword);
					driver.findElement(By.id("ChangePassword1_ChangePasswordPushButton")).click();
					Thread.sleep(10000);
					WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//tr/td[2]/table/tbody/tr[1]/td/div[3]/a");
					Logout_BTN.click();
					
					Thread.sleep(1500);
					driver.close();
					openOldTabL();
					driver.navigate().refresh();
					Superadmin_Login();
					Thread.sleep(3000);
					Actions action = new Actions(GlobelMethods.driver);
					WebElement element = GWait.Wait_GetElementByXpath("//tr/td[7]/table/tbody/tr/td[1]/a", 120);
					action.moveToElement(element).build().perform();
					Thread.sleep(500);
					GWait.Wait_GetElementByLinkText("Create Users").click();
				}
			} // else {

			// }

		} catch (Exception ex) {

			Thread.sleep(4500);
			WebElement link11 = GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
			link11.click();
			driver.navigate().refresh();
			
			List<WebElement> a111 = driver
					.findElements(By.xpath("//span/b[text()='User Creation Details with Clinion']"));
			System.out.println(a111.size());

			if (a111.get(0).getText().equalsIgnoreCase("User Creation Details with Clinion")) {
				a111.get(0).click();
				WebElement Username1 = GWait
						.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div[1]/table/tbody/tr[6]/td");
				System.out.println(Username1.getText());
				String Username2 = Username1.getText();
				String[] UsernameSplit = Username2.split(" ");
				System.out.println("1st: " + UsernameSplit[0]);
				System.out.println("2nd: " + UsernameSplit[1]);
				System.out.println("3rd: " + UsernameSplit[2]);
				// System.out.println("1st: " + UsernameSplit[3]);

				finalUsrNAM = UsernameSplit[2];

				System.out.println("Final String: " + finalUsrNAM);

				WebElement pass1 = GWait.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div[1]/table/tbody/tr[7]/td");
				System.out.println(pass1.getText());
				String pass2 = pass1.getText();
				String[] passwordSplit = pass2.split(" ");
				System.out.println("1st: " + passwordSplit[0]);
				System.out.println("2nd: " + passwordSplit[1]);
				System.out.println("3rd: " + passwordSplit[2]);

				finalPass = passwordSplit[2];

				System.out.println("Final String: " + finalPass);

				Thread.sleep(2000);
				driver.close();

				// CloseNewTab();
				Thread.sleep(1000);

				openNewTabWithClinionURL();

				// -----Login-----//

				GWait.Wait_GetElementById("txtUserName").sendKeys(finalUsrNAM);
				WebElement sas = GWait.Wait_GetElementById("txtPassword");
				sas.sendKeys(finalPass);
				driver.findElement(By.id("LoginButton")).click();
				alertaccept();
				VerificationCode();
				
				// ----Reset Password----//
				Sheet r = wb.getSheet("Login_Data");

				String SetNewPassword = r.getCell(3, 1).getContents();

				GWait.Wait_GetElementById("ChangePassword1_CurrentPassword").sendKeys(finalPass);
				GWait.Wait_GetElementById("ChangePassword1_NewPassword").sendKeys(SetNewPassword);
				GWait.Wait_GetElementById("ChangePassword1_ConfirmNewPassword").sendKeys(SetNewPassword);
				driver.findElement(By.id("ChangePassword1_ChangePasswordPushButton")).click();
				Thread.sleep(10000);
				WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//tr/td[2]/table/tbody/tr[1]/td/div[3]/a");
				Logout_BTN.click();
				
				Thread.sleep(1500);
				driver.close();
				openOldTabL();
				driver.navigate().refresh();
				Superadmin_Login();
				Thread.sleep(3000);
				Actions action = new Actions(GlobelMethods.driver);
				WebElement element = GWait.Wait_GetElementByXpath("//tr/td[7]/table/tbody/tr/td[1]/a", 120);
				action.moveToElement(element).build().perform();
				Thread.sleep(500);
				GWait.Wait_GetElementByLinkText("Create Users").click();

				Thread.sleep(3000);

			}

		}

	}

}
