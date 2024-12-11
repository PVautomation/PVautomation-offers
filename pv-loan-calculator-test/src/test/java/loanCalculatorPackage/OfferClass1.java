/*
package loanCalculatorPackage;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class OfferClass1 extends LoanCalculatorTestClass1
{
	
	
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  
	
  @Test
  public void OfferClass1() {
    driver.get("https://www-test.republicfinance.com/");
    driver.manage().window().setSize(new Dimension(1102, 824));
    driver.findElement(By.cssSelector(".button")).click();
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("78748");
    driver.findElement(By.id("next-button")).click();
    {
      WebElement element = driver.findElement(By.id("next-button"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    js.executeScript("window.scrollTo(0,458.3999938964844)");
    driver.findElement(By.cssSelector(".css-39onp8 .form-control")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector(".css-39onp8 .form-control"));
      dropdown.findElement(By.xpath("//option[. = '$2,000']")).click();
    }
    driver.findElement(By.cssSelector(".css-ofz9ok:nth-child(3) .form-control")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector(".css-ofz9ok:nth-child(3) .form-control"));
      dropdown.findElement(By.xpath("//option[. = 'Pay Bills']")).click();
    }
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
    driver.findElement(By.cssSelector(".css-1kjflk4 .chakra-checkbox__control")).click();
    driver.findElement(By.cssSelector(".css-hvif6j .chakra-checkbox__control")).click();
    driver.findElement(By.id("next-button")).click();
    driver.findElement(By.cssSelector(".radio:nth-child(3) > input")).click();
    driver.findElement(By.cssSelector(".css-jktbgb:nth-child(7) .form-control")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector(".css-jktbgb:nth-child(7) .form-control"));
      dropdown.findElement(By.xpath("//option[. = '0']")).click();
    }
    driver.findElement(By.id("incomeAmount")).click();
    driver.findElement(By.id("incomeAmount")).sendKeys("$99,999,999");
    driver.findElement(By.id("all")).click();
    driver.findElement(By.id("next-button")).click();
    driver.findElement(By.cssSelector(".chakra-checkbox__control")).click();
    driver.findElement(By.cssSelector(".css-14oket6")).click();
    driver.close();
  }
}

*/

