package Clinion_Execution;

import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Clinion_GlobalMethod.Clinion_WaitMethod;
import Clinion_GlobalMethod.GlobelMethods;


public class reloadcrfDataExtraction {
	public static WebDriver driver;
	/*public reloadcrfDataExtraction() {
		PageFactory.initElements(GlobelMethods.driver, this);
	}

	Clinion_WaitMethod GWait = new Clinion_WaitMethod(driver);
	Actions action = new Actions(driver);*/
	
	
	@Test
	public void runCRFDataExtraction() throws Exception {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://clintest1.jssresearch.com/PT104prod/Default.aspx");
		
		Datamanager_Login();
		Thread.sleep(20000);
		Thread.sleep(20000);
		
		for (int i = 0; i <= 100; i++) {
			
			Thread.sleep(20000);
			/*WebElement element = driver.findElement(By.xpath("//div[5]/table/tbody/tr/td[18]/table/tbody/tr/td[1]/a"));
			action.moveToElement(element).build().perform();*/
			
			driver.findElement(By.id("btnSaveSubmit")).click();
			Thread.sleep(2000);
			System.out.println(i);
			
		}
		
	}
	
	public static void Datamanager_Login() throws Exception {
		
		driver.findElement(By.id("txtUserName")).sendKeys("girish");
		driver.findElement(By.id("txtPassword")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

}
