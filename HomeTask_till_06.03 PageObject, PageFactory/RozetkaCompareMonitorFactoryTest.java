package Rozetka_Google_Guru_Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.concurrent.TimeUnit;

public class RozetkaCompareMonitorFactoryTest {
    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";

    private String priceOfFirstProduct;
    private String nameOfFirstProduct;
    private String priceOfSecondProduct;
    private String nameOfSecondProduct;

    RozetkaHomeFactoryPage rozetkaHomePage;
    SearchFactoryPage searchPage;
    ProductFactoryPage productPage;
    CompareListFactoryPage compareListPage;


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
        driver.get(initialUrl);
        rozetkaHomePage = new RozetkaHomeFactoryPage(driver);
        searchPage = new SearchFactoryPage(driver);
        productPage = new ProductFactoryPage(driver);
        compareListPage = new CompareListFactoryPage(driver);
    }

    @Test
    public void test1() throws Exception {
        rozetkaHomePage.moveToMonitorSearchPage();
        searchPage.findProductWithPriceLessThan("4000");
        priceOfFirstProduct = productPage.getPrice();
        nameOfFirstProduct = productPage.getName();
        productPage.addToCompareList();
        productPage.moveBack();
        searchPage.findProductWithPriceLessThan(priceOfFirstProduct);
        priceOfSecondProduct = productPage.getPrice();
        nameOfSecondProduct = productPage.getName();
        productPage.addToCompareList();
        productPage.moveToCompareList();
        Assert.assertEquals(compareListPage.getNumberOfProducts(), 2);
        Assert.assertEquals(compareListPage.getPriceByNumber(1), priceOfFirstProduct);
        Assert.assertEquals(compareListPage.getNameByNumber(1), nameOfFirstProduct);
        Assert.assertEquals(compareListPage.getPriceByNumber(2), priceOfSecondProduct);
        Assert.assertEquals(compareListPage.getNameByNumber(2), nameOfSecondProduct);
    }
}
