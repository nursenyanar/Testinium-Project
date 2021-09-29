package AutomationBase.base;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    String baseUrl = "https://www.gittigidiyor.com/";
    public WebDriver driver;


    @Before
    public void baslangic(){
        System.setProperty("webdriver.chrome.driver","properties/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(("--start-maximized"));
        options.addArguments("--disable-notifications");
        options.addArguments("disable-popup-blocking");
        this.driver = new ChromeDriver(options);
        driver.get(baseUrl);
    }

    public void setElementByCssSelector(String cssSelector, String value) {
        WebElement search = driver.findElement(By.cssSelector(cssSelector));
        search.sendKeys(value);
    }

    public void setElementById(String id, String value) throws InterruptedException {
        WebElement search = driver.findElement(By.id(id));
        search.sendKeys(value);
    }

    public void clickElementByCssSelector(String cssSelector) {
        WebElement click = driver.findElement(By.cssSelector(cssSelector));
        click.click();
    }

    public void clickElementById(String id) {
        WebElement click = driver.findElement(By.id(id));
        click.click();
    }

    public void clickElementByXpath(String xpath) {
        WebElement click = driver.findElement(By.xpath(xpath));
        click.click();
    }

    public String scroll() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,5000)");
        Thread.sleep(1000);
        return null;
    }

    public void mouseHover(String deneme, String deneme2) throws InterruptedException {
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(deneme));
        builder.moveToElement(element).build().perform();
        Thread.sleep(2000);
        clickElementByCssSelector(deneme2);
    }

    public void assertion(String actual, String excepted){
        Assert.assertEquals(excepted,actual);
    }

    @After
    public void bitis() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
