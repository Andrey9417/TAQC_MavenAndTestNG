package Rozetka_Google_Guru_Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

public class RozetkaTestCompareMonitor {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    String initialUrl = "https://rozetka.com.ua/";
    List<WebElement> listOfElements;
    WebElement buttonAddToComparisonList;

    String priceOfFirstProduct;
    String nameOfFirstProduct;
    String priceOfSecondProduct;
    String nameOfSecondProduct;


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
    public void navigateToSite(){
        driver.get(initialUrl);
    }

    @Test
    public void test(){

        WebElement linkComputersAndNotebooks = driver.findElement(By.xpath("//sidebar-fat-menu//a[contains (@href, 'computers-notebooks')]"));
        actions.moveToElement(linkComputersAndNotebooks).perform();
        wait.until(visibilityOfElementLocated(By.xpath("//div[@class='menu__main-cats']//a[contains (@href, 'monitors')]"))).click();

        wait.until(visibilityOfElementLocated(By.xpath("//div[@class='goods-tile__inner']")));
        listOfElements=driver.findElements(By.xpath("//div[@class='goods-tile__inner']"));

        for(WebElement webElem : listOfElements){
            String price=webElem.findElement(By.xpath(".//span[@class='goods-tile__price-value']")).getText().replace(" ", "");
            if(Integer.parseInt(price)<4000){
                webElem.findElement(By.tagName("a")).click();
                buttonAddToComparisonList=wait.until(visibilityOfElementLocated(By.cssSelector("button.compare-button")));
                priceOfFirstProduct=price;
                nameOfFirstProduct=driver.findElement(By.className("product__title")).getText();
                break;
            }
        }

        Assert.assertNotNull(buttonAddToComparisonList, "product with price less that 4000 wasn't found");
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddToComparisonList)).click();
        // "span.counter" не находит через xpass, в чем ошибка?
        wait.until(visibilityOfElementLocated(By.cssSelector("span.counter")));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.counter")).getText(), "1");
        buttonAddToComparisonList=null;
        driver.navigate().back();
        wait.until(visibilityOfElementLocated(By.xpath("//div[@class='goods-tile__inner']")));
        listOfElements.clear();
        listOfElements=driver.findElements(By.xpath("//div[@class='goods-tile__inner']"));

        for(WebElement webElem : listOfElements){
            String price=webElem.findElement(By.xpath(".//span[@class='goods-tile__price-value']")).getText().replace(" ", "");
            if(Integer.parseInt(price)<Integer.parseInt(priceOfFirstProduct)){
                webElem.findElement(By.tagName("a")).click();
                buttonAddToComparisonList=wait.until(visibilityOfElementLocated(By.cssSelector("button.compare-button")));
                priceOfSecondProduct=price;
                nameOfSecondProduct=driver.findElement(By.className("product__title")).getText();
                break;
            }
        }

        Assert.assertNotNull(buttonAddToComparisonList, "product with price less that "+priceOfFirstProduct+" wasn't found");
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddToComparisonList)).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("span.counter")));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.counter")).getText(), "2");

        driver.findElement(By.xpath("//button[@aria-label='Списки сравнения']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.modal__holder a"))).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("li.products-grid__cell")));

        listOfElements.clear();
        listOfElements=driver.findElements(By.cssSelector("li.products-grid__cell"));
        Assert.assertEquals(listOfElements.size(), 2);
        Collections.reverse(listOfElements);

        String price1= listOfElements.get(0).findElement(By.xpath(".//div[@class='product__prices']/div")).getText();
        String oldPrice = listOfElements.get(0).findElement(By.xpath(".//div[@class='product__prices']/div/div")).getText();
        price1=(price1.substring(oldPrice.length())).replaceAll("[ ₴]", "").trim();

        String price2= listOfElements.get(1).findElement(By.xpath(".//div[@class='product__prices']/div")).getText();
        oldPrice = listOfElements.get(1).findElement(By.xpath(".//div[@class='product__prices']/div/div")).getText();
        price2=(price2.substring(oldPrice.length())).replaceAll("[ ₴]", "").trim();

        String name1=listOfElements.get(0).findElement(By.xpath(".//a")).getText();
        String name2=listOfElements.get(1).findElement(By.xpath(".//a")).getText();

        Assert.assertEquals(price1, priceOfFirstProduct);
        Assert.assertEquals(price2, priceOfSecondProduct);
        Assert.assertEquals(name1, nameOfFirstProduct);
        Assert.assertEquals(name2, nameOfSecondProduct);
    }
}
