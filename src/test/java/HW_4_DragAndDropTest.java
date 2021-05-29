import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HW_4_DragAndDropTest {
    WebDriver driver;
    WebDriverWait wait;
    String initialUrl = "http://demo.guru99.com/test/drag_drop.html";

    @BeforeClass
    public void setupBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
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

    }
}