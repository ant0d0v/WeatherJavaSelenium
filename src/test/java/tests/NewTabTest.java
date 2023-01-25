package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class NewTabTest extends BaseTest {
    final static By DESKTOP_MENU_FOR_BUSINESS = By.xpath("//div[@id='desktop-menu']//ul/li[10]/a[@href='https://openweather.co.uk']");
    final static By MOBILE_PADDING = By.xpath("//div[@class = 'mobile-padding']/h1");
    final static By MAIN_MOBILE_PADDING = By.xpath("//div[@class='mobile-padding']//h2/span");
//    private void waitForGrayDisappeared() {
//        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-container")));


//    }
    private void clickWait(By by, WebDriverWait wait) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    private void switchToWindow(String windowDescriptor, WebDriverWait wait, WebDriver driver, By by){
        driver.switchTo().window(windowDescriptor);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Test
    public void testLogoPropertyOnMainAreNotEqualsLogoPropertyOnCoUk() {

        openBaseURL();
        waitForGrayDisappeared();

        String window1 = getDriver().getWindowHandle();

        clickWait(DESKTOP_MENU_FOR_BUSINESS,getWait());

        String window2 = "";
        Set<String> currentWindows = getDriver().getWindowHandles();
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }

            switchToWindow(window2, getWait(), getDriver(), MOBILE_PADDING);

            String logoPropertyOnCoUk = getDriver().findElement(
                            By.xpath("//li[@class = 'logo']/a/img"))
                    .getDomProperty("src");

        getDriver().close();
        switchToWindow(window1,getWait(),getDriver(),MAIN_MOBILE_PADDING);

        String logoPropertyOnCoUkMain = getDriver().findElement(
                        By.xpath("//li[@class = 'logo']/a/img"))
                .getDomProperty("src");

        Assert.assertFalse(logoPropertyOnCoUkMain.equals(logoPropertyOnCoUk));

    }
}
