package parameters.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import parameters.Parameterization.JsonToHashMapExample;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class DemoTest {

    WebDriver driver;
    Map<String, Map<String, Map<String, Object>>> jsonMap = JsonToHashMapExample. dataReader();

    @BeforeTest
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

        driver.get("https://app.hubspot.com/login");
    }
    @Test
    public void testCaseDemo()
    {
//        Map<String, Map<String, Map<String, Object>>> jsonMap = JsonToHashMapExample.dataReader();

        // Access values from the jsonMap
        Map<String, Object> testCase1Step1 = jsonMap.get("TestCase1").get("testStep1");
        String email = (String) testCase1Step1.get("email");
        String name = (String) testCase1Step1.get("name");
        Double age = (Double) testCase1Step1.get("age");

        // Perform assertions
        Assert.assertEquals(email, "john@gmail.com");
        Assert.assertEquals(name, "john");
        Assert.assertEquals(age, 31.0);
    }

    @Test
    public void hubSpotEmailTest() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys((CharSequence) jsonMap.get("TestCase1").get("testStep1").get("email"));
        Thread.sleep(3000);
    }

    @Test
    public void hubSpotPasswordTest() throws InterruptedException {
        driver.findElement(By.id("password")).sendKeys((CharSequence) jsonMap.get("TestCase2").get("testStep1").get("name"));
        Thread.sleep(3000);
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }

}
