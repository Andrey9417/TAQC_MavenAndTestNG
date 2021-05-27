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

import java.util.List;

public class RozetkaTestFilters {

    WebDriver driver;
    WebDriverWait wait;
    String initialUrl="https://rozetka.com.ua/";
    List<WebElement> listOfElements;
    boolean check;

    @BeforeClass
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
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
    public void test1FilterByManufacturer(){

        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='Мобильные телефоны']")));
        driver.findElement(By.xpath("//a/span[text()='Мобильные телефоны']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for=Apple]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for=Huawei]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='goods-tile__inner']")));
        listOfElements = driver.findElements(By.cssSelector("span.goods-tile__title"));

        for(WebElement webElem : listOfElements){
            String nameOfProduct = webElem.getText();
            check = (nameOfProduct.contains("Samsung")) ||
                    (nameOfProduct.contains("Apple")) ||
                    (nameOfProduct.contains("Huawei"));

            Assert.assertTrue(check);
        }

    }

    @Test
    public void test2FilterByPrice(){

        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='Мобильные телефоны']")));
        driver.findElement(By.xpath("//a/span[text()='Мобильные телефоны']")).click();

        WebElement minPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[formcontrolname=min]")));
        minPrice.sendKeys(Keys.chord(Keys.CONTROL, "a"), "5000");

        WebElement maxPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[formcontrolname=max]")));
        maxPrice.sendKeys(Keys.CONTROL, "a");
        maxPrice.sendKeys("15000", Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.goods-tile__inner>a")));
        listOfElements = driver.findElements(By.cssSelector("span.goods-tile__price-value"));

        for(WebElement webElem : listOfElements){
            String priceOfProduct = webElem.getText().replaceAll(" ", "");
            //System.out.println(priceOfProduct);
            check = Integer.parseInt(priceOfProduct)<15000 && Integer.parseInt(priceOfProduct)>5000;

            Assert.assertTrue(check);
        }
    }

    @Test
    public void test3FilterByAvailability(){

        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='Мобильные телефоны']")));
        driver.findElement(By.xpath("//a/span[text()='Мобильные телефоны']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Готов к отправке']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.goods-tile__inner>a")));

        listOfElements = driver.findElements(By.cssSelector("div.goods-tile__availability"));

        for(WebElement webElem : listOfElements){
            Assert.assertTrue(webElem.getText().contains("Готов к отправке"));
        }

    }

}
