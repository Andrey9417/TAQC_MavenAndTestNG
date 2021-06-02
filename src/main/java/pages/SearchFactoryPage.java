package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchFactoryPage {
    WebDriver webDriver;

    @FindBy(xpath = "//div[@class='goods-tile__inner']")
    private List<WebElement> productsOnSearchPage;
    @FindBy(xpath = ".//span[@class='goods-tile__price-value']")
    private WebElement priceOfProduct;
    @FindBy(xpath = ".//a")
    private WebElement linkToProductPage;
//    By priceOfProduct = By.xpath(".//span[@class='goods-tile__price-value']");
//    By linkToProductPage = By.xpath(".//a");

    public SearchFactoryPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void findProductWithPriceLessThan(String maxPrice) throws Exception {

        boolean found = false;
        for (WebElement webElem : productsOnSearchPage) {

            String price = priceOfProduct.getText().replace(" ", "");
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
        linkToProductPage.click();
    }
}
