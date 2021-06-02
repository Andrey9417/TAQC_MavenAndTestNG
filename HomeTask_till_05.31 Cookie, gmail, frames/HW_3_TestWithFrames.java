package AdvancedSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class HW_3_TestWithFrames {

    WebDriver driver;
    String initialUrl = "http://demo.guru99.com/test/guru99home/";
    Robot robot;
    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.manage().window().maximize();
        driver.get(initialUrl);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        int number = driver.findElements(By.tagName("iframe")).size();
        System.out.println(number);
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector(".ytp-large-play-button")).click();
        WebElement playButton = driver.findElement(By.cssSelector(".ytp-play-button"));
        Assert.assertTrue(playButton.isDisplayed());
        for(int i = 0; i<3; i++) {
            robot.mouseMove(500, 500);
            robot.delay(4000);
            Assert.assertFalse(playButton.isDisplayed());
            robot.mouseMove(800, 500);
            robot.delay(2000);
            Assert.assertTrue(playButton.isDisplayed());
        }
    }
}
