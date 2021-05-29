package Rozetka_Google_Guru_Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestGoogleSearch {

    WebDriver driver;
    WebDriverWait wait;
    String initialUrl = "https://www.google.com/";
    String searchText = "iphone kyiv buy";
    //String partialLink = "stylus.ua";
    boolean elementFound = false;
    List<WebElement> listOfElements;


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
    public void test(){
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchText + Keys.ENTER);

        for(int i = 0; i<5; i++){
            listOfElements = driver.findElements(By.xpath("//cite[contains(text(), 'stylus.ua')]"));
            if(!listOfElements.isEmpty()) {
                System.out.println("STYLUS.UA found on "+(i+1)+" page");
                elementFound = true;
                break;
            }
            driver.findElement(By.id("pnnext")).click();
        }
        assertTrue(elementFound, "STYLUS.UA not found on first 5 pages");
        // //cite[contains(text(), 'stylus.ua')]
    }
}
