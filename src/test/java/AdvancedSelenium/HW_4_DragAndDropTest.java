package AdvancedSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HW_4_DragAndDropTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    String initialUrl = "http://demo.guru99.com/test/drag_drop.html";

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        action = new Actions(driver);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(initialUrl);
    }

    @Test
    public void test() {

        WebElement item = driver.findElement(By.xpath("(//a[contains (text(), '5000')])[2]"));
        WebElement placeForItem = driver.findElement(By.cssSelector("ol#amt7 li"));
        action.dragAndDrop(item, placeForItem).perform();

        item = driver.findElement(By.xpath("(//a[contains (text(), '5000')])[2]"));
        placeForItem = driver.findElement(By.cssSelector("ol#amt8 li"));
        action.dragAndDrop(item, placeForItem).perform();

        item = driver.findElement(By.xpath("//a[contains(text(), 'BANK')]"));
        placeForItem = driver.findElement(By.cssSelector("ol#bank li"));
        action.dragAndDrop(item, placeForItem).perform();

        item = driver.findElement(By.xpath("//a[contains(text(), 'SALES')]"));
        placeForItem = driver.findElement(By.cssSelector("ol#loan li"));
        action.dragAndDrop(item, placeForItem).perform();

        Assert.assertTrue(driver.findElement(By.id("equal")).isDisplayed());
    }
}