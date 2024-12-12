package loanCalculatorPackage;


import java.util.regex.Pattern;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
//import java.io.File;
import java.time.Duration;
//import com.microsoft.*;

//import org.apache.commons.io.FileUtils;

//@TestOwner("pvenkatarajan@republicfinance.com")
public class LoanCalculatorTestClass1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private JavascriptExecutor js;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	  
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		/*
		ops.addArguments("--headless"); // headless mode
		ops.addArguments("--disable-gpu"); // Disable GPU acceleration for headless mode
		ops.addArguments("--no-sandbox"); // for headless mode in CI/CD environments
		ops.addArguments("--window-size=1920x1080");
		ops.addArguments("--disable-dev-shm-usage");
		// System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
		*/
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\pvenkatarajan\\Downloads\\chromedriver-win64\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
		
		driver = new ChromeDriver(ops);
        ITestContext context = Reporter.getCurrentTestResult().getTestContext();
        context.setAttribute("driver", driver);
		//context.setAttribute("driver", driver);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
    js = (JavascriptExecutor) driver;

  }

  @Test
  @Parameters("ownerName")
  public void testLc1() throws Exception {
    driver.get("https://www-test.republicfinance.com/monthly-payment-calculator");
    Thread.sleep(15000);
	 js.executeScript("document.body.style.zoom='30%'");
	 js.executeScript("window.scroll(0,0);"); 
    driver.findElement(By.id("field-5")).click();
    Thread.sleep(15000);
    driver.findElement(By.id("field-5")).clear();
    Thread.sleep(15000);
    driver.findElement(By.id("field-5")).sendKeys("78748");
    Thread.sleep(15000);
    driver.findElement(By.xpath("//select[@id='field-5']")).click();
    Thread.sleep(5000);
    new Select(driver.findElement(By.xpath("//select[@id='field-5']"))).selectByVisibleText("$2001-$3000");
    Thread.sleep(15000);
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/div/div/div/form/div/div/div[3]/div/select")).click();
    Thread.sleep(5000);
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/div/div/div/form/div/div/div[3]/div/select"))).selectByVisibleText("24 months");
    Thread.sleep(15000);
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/div/div/div/form/div/div/div[4]/div/select")).click();
    Thread.sleep(5000);
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/div/div/div/form/div/div/div[4]/div/select"))).selectByVisibleText("500-599 (Fair)");
    Thread.sleep(15000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(15000);
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div/div/div[2]/div/div/p[3]")).click();
    //Warning: assertTextPresent may require manual changes
    Thread.sleep(15000);
    //assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*xpath=//div\\[@id='root'\\]/div/div/div/div/div/div\\[2\\]/div/div/p\\[3\\][\\s\\S]*$"));
    Thread.sleep(5000);
    System.out.println("complete1");
    //Assert.assertEquals(true, true, "Assertion Success: Expected value matched the actual value.");
    Reporter.log("LOAN CALCULATOR TEST GOT EXECUTED SUCCESSFULLY !!!");
  }
  
  
  
  @Test
  @Parameters("ownerName")
  public void OfferTest() throws Exception {
    driver.get("https://www-test.republicfinance.com/");
    Thread.sleep(15000);
	 js.executeScript("document.body.style.zoom='30%'");
	 Thread.sleep(15000);
	 js.executeScript("window.scroll(0,0);"); 
	 Thread.sleep(5000);
    //driver.manage().window().setSize(new Dimension(1102, 824));
    driver.findElement(By.cssSelector(".button")).click();
    Thread.sleep(15000);
	 js.executeScript("document.body.style.zoom='30%'");
	 Thread.sleep(15000);
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("78748");
    Thread.sleep(10000);
    driver.findElement(By.id("next-button")).click();
    {
      WebElement element = driver.findElement(By.id("next-button"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    Thread.sleep(15000);
    js.executeScript("window.scrollTo(0,458.3999938964844)");
    driver.findElement(By.cssSelector(".css-39onp8 .form-control")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector(".css-39onp8 .form-control"));
      dropdown.findElement(By.xpath("//option[. = '$2,000']")).click();
    }
    Thread.sleep(15000);
    driver.findElement(By.cssSelector(".css-ofz9ok:nth-child(3) .form-control")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector(".css-ofz9ok:nth-child(3) .form-control"));
      dropdown.findElement(By.xpath("//option[. = 'Pay Bills']")).click();
    }
    Thread.sleep(15000);
    driver.findElement(By.cssSelector(".css-ofz9ok:nth-child(4) .form-control")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector(".css-ofz9ok:nth-child(4) .form-control"));
      dropdown.findElement(By.xpath("//option[. = 'Radio']")).click();
    }
    driver.findElement(By.id("next-button")).click();
    driver.findElement(By.id("firstName")).click();
    driver.findElement(By.id("firstName")).sendKeys("test");
    driver.findElement(By.cssSelector(".css-18acosx > .css-0:nth-child(2) .form-control")).click();
    driver.findElement(By.cssSelector(".css-18acosx > .css-0:nth-child(2) .form-control")).sendKeys("test");
    driver.findElement(By.cssSelector(".css-11byn9x:nth-child(3) .form-control")).click();
    driver.findElement(By.cssSelector(".css-11byn9x:nth-child(3) .form-control")).sendKeys("test@test.com");
    driver.findElement(By.cssSelector(".css-39onp8 .form-control")).click();
    driver.findElement(By.cssSelector(".css-39onp8 .form-control")).sendKeys("(512) 501-1234");
    driver.findElement(By.cssSelector(".css-11byn9x:nth-child(6) .form-control")).click();
    driver.findElement(By.cssSelector(".css-11byn9x:nth-child(6) .form-control")).sendKeys("11/11/1999");
    driver.findElement(By.id("ssn")).click();
    driver.findElement(By.id("ssn")).sendKeys("111-11-1111");
    driver.findElement(By.cssSelector(".css-13y5u2s .form-control")).click();
    driver.findElement(By.cssSelector(".css-13y5u2s .form-control")).sendKeys("st1");
    driver.findElement(By.cssSelector(".css-1szp1zr > .css-0 .form-control")).click();
    driver.findElement(By.cssSelector(".css-1szp1zr > .css-0 .form-control")).sendKeys("st2");
    driver.findElement(By.cssSelector(".css-1ao1r8b .card")).click();
    driver.findElement(By.id("next-button")).click();
    {
      WebElement element = driver.findElement(By.id("next-button"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    Thread.sleep(10000);
    driver.findElement(By.cssSelector(".css-1kjflk4 .chakra-checkbox__control")).click();
    Thread.sleep(8000);
    driver.findElement(By.cssSelector(".css-hvif6j .chakra-checkbox__control")).click();
    Thread.sleep(8000);
	 //scroll  
    Long documentHeight = (Long) js.executeScript("return document.body.scrollHeight");
	 js.executeScript("window.scrollTo(0, arguments[0]);", documentHeight);
	 Thread.sleep(5000);
    driver.findElement(By.id("next-button")).click();
    Thread.sleep(10000);
    driver.findElement(By.cssSelector(".radio:nth-child(3) > input")).click();
    Thread.sleep(8000);
    driver.findElement(By.cssSelector(".css-jktbgb:nth-child(7) .form-control")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector(".css-jktbgb:nth-child(7) .form-control"));
      dropdown.findElement(By.xpath("//option[. = '0']")).click();
    }
    Thread.sleep(8000);
    driver.findElement(By.id("incomeAmount")).click();
    Thread.sleep(8000);
    driver.findElement(By.id("incomeAmount")).sendKeys("$99,999,999");
    Thread.sleep(8000);
    driver.findElement(By.id("all")).click();
    driver.findElement(By.id("next-button")).click();
    Thread.sleep(25000);
    driver.findElement(By.cssSelector(".chakra-checkbox__control")).click();
    Thread.sleep(8000);
    driver.findElement(By.cssSelector(".css-14oket6")).click();
    Thread.sleep(20000);
   // driver.close();
    //Assert.assertEquals(true, true, "Assertion Success: Expected value matched the actual value.");
    Reporter.log("OFFER TEST GOT EXECUTED SUCCESSFULLY !!!");
  }
  
  
  
  
  

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
/*
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  */
}

