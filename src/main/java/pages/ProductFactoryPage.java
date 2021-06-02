package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductFactoryPage {
    private static int count = 0;
    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//button[@aria-label='Списки сравнения']")
    private WebElement compareListIcon;
    @FindBy(css = "div.modal__holder a")
    private WebElement linkOnModalWindow;
    @FindBy(css = "span.counter")
    private WebElement compareListCounter;
    @FindBy(css = "button.compare-button")
    private WebElement compareButton;
    @FindBy(css = "p.product-prices__big")
    private WebElement priceOfProduct;
    @FindBy(className = "product__title")
    private WebElement nameOfProduct;

    public ProductFactoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    public void moveBack(){
        webDriver.navigate().back();
    }

    public void moveToCompareList(){
        compareListIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(linkOnModalWindow)).click();
    }

    public String getPrice() {
        return priceOfProduct.getText().replaceAll("[ ₴]", "");
    }

    public String getName() {
        return nameOfProduct.getText();
    }

    private void addToEmptyCompareList() throws Exception {
        wait.until(ExpectedConditions.visibilityOf(compareButton)).click();
        count++;
        if (!compareListCounter.getText().equals("1")){
            throw new Exception("Product wasn't added to compare list");
        }
    }

    public void addToCompareList() throws Exception {
        if(count ==0){
            addToEmptyCompareList();
            return;
        }
        wait.until(ExpectedConditions.visibilityOf(compareButton)).click();
        count++;
        if (!compareListCounter.getText().equals(count+"")){
            throw new Exception("Product wasn't added to compare list");
        }
    }
}
