package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
//		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		
		driver.get("https://opensource-demo.orangehrmlive.com");
	}
	
	@Test(dataProvider = "test1data")
	public void test1(String username, String password) throws InterruptedException 
	{
		System.out.println(username +" | "+ password);
		
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(3000);
	}
	
	@DataProvider(name = "test1data")
	public Object[][] getData() 
	{
		String excelPath = "C:\\Users\\fatim\\eclipse-workspace\\Parameterization\\excel\\data.xlsx";
		Object data[][] = testData(excelPath, "Sheet1");
		return data;
	}
	
	public Object[][] testData(String excelPath, String sheetName) 
	{
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
		
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		Object data[][] = new Object[rowCount-1][colCount];
		
		for (int i = 1; i < rowCount; i++) 
		{
			for (int j = 0; j < colCount; j++) 
			{
				String cellData = excel.getCellDataString(i, j);
				System.out.println(cellData);
				data[i-1][j] = cellData;
			}
		}
		return data;
	}
	
	@AfterTest
	public void tearDown() 
	{
		driver.quit();
	}

}
