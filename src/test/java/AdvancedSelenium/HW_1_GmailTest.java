package AdvancedSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesForRozetka.RobotForGmail;

import java.util.concurrent.TimeUnit;

public class HW_1_GmailTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    String initialUrl = "http://mail.google.com";

    String testEmail = "testaccmail256@gmail.com";
    String testPassword = "ntcnntcn1";
    String subjectOfLetter = "asdasd";
    String bodyOfLetter = "qweqweqwewqe";


    @BeforeClass
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite(){ driver.get(initialUrl); }

    @Test
    public void test() throws InterruptedException {

        WebElement webElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#identifierId")));
        webElem.sendKeys(testEmail +Keys.ENTER);

        webElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name=password]")));
        webElem.sendKeys(testPassword+ Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[gh=cm]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role=dialog]")));
        actions.click(driver.findElement(By.cssSelector("form[method=POST]>div"))).sendKeys(testEmail).build().perform();
        driver.findElement(By.name("subjectbox")).sendKeys(subjectOfLetter);
        actions.click(driver.findElement(By.cssSelector("table[role=presentation] td>form+div+table"))).sendKeys(bodyOfLetter).build().perform();
        driver.findElement(By.cssSelector("[command=Files]>div")).click();

        RobotForGmail.uploadFileViaRobot();

        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("[role=progressbar]"))));
        driver.findElement(By.cssSelector("[role=group] td")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#link_vsm")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//td[@role='gridcell']")))).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h2[text()='"+subjectOfLetter+"']")).getText(), subjectOfLetter);
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='"+testEmail+"']")).getText().replaceAll("[<>]", ""), testEmail);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id]/div[@dir]")).getText(), bodyOfLetter);
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id][text()='qweqwe1.txt']")).isDisplayed());

    }
}
