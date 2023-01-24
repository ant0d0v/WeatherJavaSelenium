import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AlertTest extends BaseTest {
    final static String BASE_URL_99_BOTTLES = "https://www.99-bottles-of-beer.net/";
    final static By MENU_GUESTBOOK = By.linkText("Guestbook");
    final static By SUB_MENU_SIGN_GUESTBOOK = By.linkText("Sign Guestbook");
    final static By ALERT_SITE = By.cssSelector("a[onclick*= 'url']");
    final static By DIFFERENT_WEATHER_SWITCH = By.xpath("//span[@class = 'control-el owm-switch']");
    final static By HEADER_POPUP_DIFFERENT_WEATHER = By.xpath("//h3[@id = 'dialogTitle']");
    final static By OWN_WEATHER_ICON = By.xpath("//div[@id='dialogDesc']/div/ul[@class= 'icons']/li/*[local-name() = 'svg']");
    final static By POPUP_ICON_SPAN_TEXT = By.xpath("//div[@id='dialogDesc']/div/ul[@class= 'icons']//span");

    private void openBaseUrl() {
        getDriver().get(BASE_URL_99_BOTTLES);
    }

    private void click(By by, WebDriver driver) {
        driver.findElement(by).click();
    }

    private void waitForGrayDisappeared() {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-container")));


    }

    private void clickWait(By by, WebDriverWait wait) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    private String getText(By by, WebDriver driver) {
        return driver.findElement(by).getText();
    }

    private List<WebElement> getWebElementList(By by, WebDriver driver) {
        List<WebElement> WebElements = driver.findElements(by);
        return WebElements;
    }

    private String getStringFromList(List<String> list) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != (list.size() - 1)) {
                sb.append(", ");

            }
        }
        return sb.toString();
    }





    @Test
    public void testPopupSignGuestBook() {
        String expectedResult = "Enter the URL for the link you want to add.";

        openBaseUrl();
        click(MENU_GUESTBOOK, getDriver());
        click(SUB_MENU_SIGN_GUESTBOOK, getDriver());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        click(ALERT_SITE, getDriver());
        Alert alertUrl = wait.until(ExpectedConditions.alertIsPresent());
        String actualTextFromPromptURL = alertUrl.getText();
        alertUrl.sendKeys("mySite.com");
        //Нажать на кнопку OK
        alertUrl.accept();
        alertUrl.accept();

        // Нажать на кнопку Cancel
        // alert.dismiss();

        Assert.assertEquals(actualTextFromPromptURL, expectedResult);

    }

        @Test
        public void testPopUpHeader() {
            String expectedPopUpHeader = "Different weather";
            openBaseURL();
            waitForGrayDisappeared();
            clickWait(DIFFERENT_WEATHER_SWITCH,getWait());
            String actualPopUpHeader = getText(HEADER_POPUP_DIFFERENT_WEATHER,getDriver());
            Assert.assertEquals(actualPopUpHeader,expectedPopUpHeader);


        }

        @Test
        public void testCountIconInPopUp(){

            int expectedCountIconInPopUp = 9;

            openBaseURL();
            waitForGrayDisappeared();
            clickWait(DIFFERENT_WEATHER_SWITCH,getWait());

            int actualCountIconInPopUp = getWebElementList(OWN_WEATHER_ICON,getDriver()).size();

            Assert.assertTrue(actualCountIconInPopUp != 0);
            Assert.assertEquals(actualCountIconInPopUp,expectedCountIconInPopUp);

    }
        @Test
        public void testDescriptionOfIconInPopUp() {
           String DescriptionOfIconInPopUp =
                   "clear sky, few clouds, overcast clouds, drizzle, rain, shower rain, thunderstorm, snow, mist";

           openBaseURL();
           waitForGrayDisappeared();
           clickWait(DIFFERENT_WEATHER_SWITCH,getWait());

           List<String> iconTexts = new ArrayList<>();

           for(WebElement span : getWebElementList(POPUP_ICON_SPAN_TEXT,getDriver())){
               iconTexts.add(span.getText());
           }
           Assert.assertFalse(iconTexts.size() == 0);

           String actualPopUpIconTexts = getStringFromList(iconTexts);

           Assert.assertEquals(actualPopUpIconTexts,DescriptionOfIconInPopUp);


        }


}
