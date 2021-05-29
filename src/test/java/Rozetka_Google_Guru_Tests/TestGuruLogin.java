package Rozetka_Google_Guru_Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class TestGuruLogin {

    WebDriver driver;
    WebDriverWait wait;
    String initialUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String userName = "1303";
    String userPassword = "Guru99";
    String incorrectCredsMessage = "User or Password is not valid";

    @BeforeClass
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }

    @BeforeMethod
    public void navigateToSite(){
        driver.get(initialUrl);
    }

    @Test
    public void positiveTest_LOGIN(){
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword+ Keys.ENTER);
        //driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        WebElement logoutLink = wait.until(presenceOfElementLocated(By.linkText("Log out")));
        logoutLink.click();

        driver.switchTo().alert().accept();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, initialUrl);
    }

    @Test
    public void negativeTest1_LOGIN(){
        driver.findElement(By.name("uid")).sendKeys(userName + Keys.ENTER);

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        assertEquals(driver.getCurrentUrl(), initialUrl);
    }

    @Test
    public void negativeTest2_LOGIN(){
        driver.findElement(By.name("password")).sendKeys(userPassword + Keys.ENTER);

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        assertEquals(driver.getCurrentUrl(), initialUrl);
    }

    @Test
    public void negativeTest3_LOGIN(){
        driver.findElement(By.name("uid")).sendKeys(userName + userName);
        driver.findElement(By.name("password")).sendKeys(userPassword);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        assertEquals(driver.getCurrentUrl(), initialUrl);
    }

    @Test
    public void negativeTest4_LOGIN(){
        driver.findElement(By.name("uid")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(userPassword+userPassword);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        assertEquals(driver.switchTo().alert().getText(), incorrectCredsMessage);
        driver.switchTo().alert().accept();

        assertEquals(driver.getCurrentUrl(), initialUrl);
    }


}
