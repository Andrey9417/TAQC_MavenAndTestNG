package AdvancedSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class HW_2_TestingCookiesGuru {
    WebDriver driver;
    WebDriverWait wait;
    String initialUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String userName = "1303";
    String userPassword = "Guru99";
    //String incorrectCredsMessage = "User or Password is not valid";

    @BeforeClass
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite(){
        driver.get(initialUrl);
    }

    @Test
    public void test(){
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword+ Keys.ENTER);
        //driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        wait.until(presenceOfElementLocated(By.linkText("Log out")));
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);

        driver.manage().deleteAllCookies();
        cookies.clear();
        cookies = driver.manage().getCookies();
        Assert.assertTrue(cookies.isEmpty());

        driver.navigate().refresh();
        wait.until(presenceOfElementLocated(By.linkText("Log out")));
    }
}
