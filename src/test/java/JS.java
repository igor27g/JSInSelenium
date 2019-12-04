import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class JS {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeEach
    public void driverSetup()
    {
        String fakeStoreProduct = "https://fakestore.testelka.pl/product/wakacje-z-yoga-w-kraju-kwitnacej-wisni/";
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(fakeStoreProduct);

        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().setScriptTimeout(1000,TimeUnit.MILLISECONDS);
        js = (JavascriptExecutor)driver;
    }

    @AfterEach
    public void driverClose()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void exampleTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('Wlasnie cos wpisalem w konsole');");
        String domainName = (String)js.executeScript("return document.domain");
    }

    @Test
    public void scrollViewTest() {
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,900)");
        WebElement scrollBar = driver.findElement(By.cssSelector("div[class='storefront-sticky-add-to-cart__content-product-info']>span"));
        Assertions.assertEquals("Oglądasz: Wakacje z yogą w Kraju Kwitnącej Wiśni",scrollBar.getText(), "Scrollbar doesn't appear" );
    }

    //    @Test
////    public void headerCardTest() {
////        WebElement description = driver.findElement(By.cssSelector("div#tab-description"));
////        ((JavascriptExecutor)driver).executeScript("arguments[0.scrollIntoView]",description);
////        List<WebElement> headerCard = driver.findElement(By.cssSelector("section.storefront-sticky-add-to-cart--slideDown"));
////        Assertions.assertTrue(headerCard.size()==1, "Header Card is not displayed after scrolling to description");
////    }
//


}
