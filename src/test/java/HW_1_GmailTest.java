//- Login to mail.google.com with existing account
//- Create new email (with TO=*the same email*, subject, email text),
//        attach file from your local machine, click send
//- Go to inbox, verify email came, verify subject, content of email,
//        verify file is attached

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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
    public void test() {

        WebElement webElem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#identifierId")));
        webElem.sendKeys(testEmail +Keys.ENTER);

        webElem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=password]")));
        webElem.sendKeys(testPassword+ Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[gh=cm]"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role=dialog]")));
        actions.click(driver.findElement(By.cssSelector("form[method=POST]>div"))).sendKeys(testEmail).build().perform();
        driver.findElement(By.name("subjectbox")).sendKeys(subjectOfLetter);
        actions.click(driver.findElement(By.cssSelector("table[role=presentation] td>form+div+table"))).sendKeys(bodyOfLetter).build().perform();

        driver.findElement(By.cssSelector("[command=Files]>div")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            StringSelection ss = new StringSelection("D:\\movies.txt");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }).start();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[role=progressbar]")));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("[role=progressbar]"))));
        driver.findElement(By.cssSelector("[role=group] td")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#link_vsm")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[role=link]+span")));
        driver.findElement(By.cssSelector("span.bog")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='"+subjectOfLetter+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+testEmail+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[@id][text()='movies.txt']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='"+bodyOfLetter+"']")).isDisplayed());

    }
}
