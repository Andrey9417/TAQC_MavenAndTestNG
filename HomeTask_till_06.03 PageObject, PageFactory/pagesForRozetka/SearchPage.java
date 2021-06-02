package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {
    WebDriver webDriver;

    By productOnSearchPage = By.xpath("//div[@class='goods-tile__inner']");
    By priceOfProduct = By.xpath(".//span[@class='goods-tile__price-value']");
    By linkToProductPage = By.xpath(".//a");
    //By categoryName = By.cssSelector("li>a[href*=mobile-phones]");

    public SearchPage (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void findProductWithPriceLessThan(String maxPrice) throws Exception {
        List<WebElement> listOfElements = webDriver.findElements(productOnSearchPage);
        boolean found = false;
        for (WebElement webElem : listOfElements) {
            String price = webElem.findElement(priceOfProduct).getText().replace(" ", "");
            if (Integer.parseInt(price) < Integer.parseInt(maxPrice)) {
                moveToProductsPage(webElem);
//                webElem.findElement(linkToProductPage).click();
                found = true;
                break;
            }
        }
        if(!found){
            throw new Exception("product with price less than "+maxPrice+" wasn't found");
        }
    }
    private void moveToProductsPage(WebElement product){
        product.findElement(linkToProductPage).click();
    }

    public void chooseProductCategory(String category){
        webDriver.findElement(By.cssSelector("li>a[href*="+category+"]")).click();
    }
}
