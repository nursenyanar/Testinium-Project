package AutomationBase.steps;

import AutomationBase.base.BaseTest;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class BaseSteps extends BaseTest {

    public  String productPagePrice;
    public  String basketPagePrice;
    @Step("www.gittigidiyor.com sitesi acilir")
    public void homePage() throws InterruptedException {
        baslangic();
        Thread.sleep(2000);
    }

    @Step("Ana sayfa acildigi kontrol edilir")
    public void homeControl() {
        assertion(driver.getTitle(), "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi");
        System.out.println("Ana sayfa acildi");
    }

    @Step("Siteye login olunur")
    public void login() throws InterruptedException {
        mouseHover("[name='profile']", "[data-cy='header-login-button']");
        setElementById("L-UserNameField","voledop989@bio123.net");
        setElementById("L-PasswordField","Aa12345");
        Thread.sleep(2000);
        clickElementById("gg-login-enter");
    }

    @Step("Login islemi kontrol edilir")
    public void loginCheck() {
        WebElement login = driver.findElement(By.xpath("//div[@title='Hesabım']"));
        assertion(login.getCssValue("Hesabım"), login.getCssValue("Hesabım"));
        System.out.println("Giris yapildi");
    }

    @Step("Arama kutucuguna <urunAdi> kelimesi girilir")
    public void productSearch(String urunAdi) throws InterruptedException {
        Thread.sleep(2000);
        setElementByCssSelector("[name='k']", urunAdi);
        Thread.sleep(2000);
        clickElementByCssSelector("[data-cy='search-find-button']");
        Thread.sleep(2000);
    }

    @Step("Arama sonuclari sayfasindan ikinci sayfa acilir")
    public void SecondPage() throws InterruptedException {
        scroll();
        clickElementByXpath("(//a[@title='2. sayfa'])");
    }

    @Step("Ikinci sayfanin acildigi kontrol edilir")
    public void SecondPageCheck() {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2" );
        System.out.println("Ikinci sayfa acildi");
    }


    @Step("Rastgele bir urun secilir")
    public void productSelect() throws InterruptedException {
        List<WebElement> links = driver.findElements(By.cssSelector("[class='sc-1nx8ums-0 dyekHG']"));
        System.out.println(" Size : "+links.size());
        Random productSize = new Random();
        int rndmno = productSize.nextInt(links.size());
        System.out.println("Random Choose Number:"+ rndmno);
        clickElementByXpath("//li[@class='sc-1nx8ums-0 dyekHG']["+rndmno+"]");
    }

    @Step("Secilen urun sepete eklenir")
    public void addBasket() throws InterruptedException {
         WebElement productNum = driver.findElement(By.xpath("//div[@id='sp-price-lowPrice'][1]"));
         productPagePrice = productNum.getText();

        clickElementByCssSelector("[class='gg-ui-button gg-ui-btn-secondary policy-alert-v2-buttons']");
        Thread.sleep(2000);
        clickElementById("add-to-basket");
        Thread.sleep(1000);
        clickElementByCssSelector("[class='header-cart-hidden-link']");

        WebElement productNum1 = driver.findElement(By.cssSelector("[class='new-price']"));
        basketPagePrice = productNum1.getText();
    }

    @Step("Urun sayfasindaki fiyat ile sepette yer alan urun fiyatinin dogrulugu karsilastirilir")
    public void productPriceCheck() throws InterruptedException {
        Assert.assertEquals(basketPagePrice,productPagePrice);
        System.out.println("Urun fiyati sepetteki ile aynidir");
    }

    @Step("Adet arttirilarak urun adedinin 2 oldugu dogrulanir")
    public void productNumberCheck() throws InterruptedException {
        clickElementByCssSelector("[class='amount']");
        Thread.sleep(1000);
        clickElementByXpath("//select[@class='amount']//option[2]");
        WebElement productnumber = driver.findElement(By.xpath("//div[@class='gg-d-16 gg-m-14 detail-text'][('Ürün Toplamı (2 Adet)')]"));
        assertion(productnumber.getCssValue("Ürün Toplamı (2 Adet)"), productnumber.getCssValue("Ürün Toplamı (2 Adet)"));
        System.out.println("Sepetteki urun adedi ikidir");
        Thread.sleep(2000);
    }

    @Step("Urun sepetten silinerek sepetin bos oldugu kontrol edilir")
    public void basketCheck() throws InterruptedException {
        clickElementByCssSelector("[class='gg-icon gg-icon-bin-medium']");
        Thread.sleep(2000);
        String emptyBasket ="//h2[contains(.,'Sepetinizde ürün bulunmamaktadır.')]";
        Assert.assertTrue(emptyBasket.contains("Sepetinizde ürün bulunmamaktadır."));
        System.out.println("Urun sepetten silindi");
        bitis();
    }
}
