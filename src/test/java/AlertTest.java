import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PopupTest extends BaseTest {
    final static String BASE_URL_99_BOTTLES = "https://www.99-bottles-of-beer.net/";
    final static By MENU_GUESTBOOK = By.linkText("Guestbook");
    final static By SUB_MENU_SIGN_GUESTBOOK = By.linkText("Sign Guestbook");
    final static By ALERT_SITE = By.cssSelector("a[onclick*= 'url']");

    private void openBaseUrl(){
        getDriver().get(BASE_URL_99_BOTTLES);
    }
    private void click(By by, WebDriver driver){
        driver.findElement(by).click();
    }



    @Test
    public void testPopupSignGuestBook(){
        String expectedResult = "Enter the URL for the link you want to add.";

        openBaseUrl();
        click(MENU_GUESTBOOK, getDriver());
        click(SUB_MENU_SIGN_GUESTBOOK,getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        click(ALERT_SITE,getDriver());
        Alert alertUrl = wait.until(ExpectedConditions.alertIsPresent());
        String actualTextFromPromptURL = alertUrl.getText();
        alertUrl.sendKeys("mySite.com");
        //Нажать на кнопку OK
        alertUrl.accept();
        alertUrl.accept();

        // Нажать на кнопку Cancel
        // alert.dismiss();

        Assert.assertEquals(actualTextFromPromptURL,expectedResult);



    }

}
