package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaHomeFactoryPage {
    WebDriver webDriver;
    WebDriverWait wait;
    Actions actions;

    @FindBy(xpath = "//sidebar-fat-menu//a[contains (@href, 'computers-notebooks')]")
    private WebElement menuElementComputers;
    @FindBy(xpath = "//div[@class='menu__main-cats']//a[contains (@href, 'monitors')]")
    private WebElement submenuElementMonitors;


    public RozetkaHomeFactoryPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void moveToMonitorSearchPage() {
        actions.moveToElement(menuElementComputers).perform();
        wait.until(ExpectedConditions.visibilityOf(submenuElementMonitors)).click();
    }
}
