import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {
    final static By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city' ]");
    final static By SEARCH_BUTTON = By.xpath("//button[@type = 'submit']");
    final  static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");


    private void waitForGrayDisappeared() {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-container")));


    }
    private String getText(By by, WebDriver driver){
        return driver.findElement(by).getText();
    }
    private void click (By by, WebDriverWait wait){

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    private void input (String text, By by, WebDriver driver){
        driver.findElement(by).sendKeys(text);

    }

    private void elementToBeVisible(By by, WebDriverWait wait){
        getWait().until(ExpectedConditions.visibilityOfElementLocated((by)));

    }
    private void waitTextChanged (By by,WebDriverWait wait, WebDriver driver, String text ){
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));

    }

    @Test
    public void testH2Text_WhenSearchingCityCountry()  {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayDisappeared();

        String old = getText(H_2_CITY_COUNTRY_HEADER,getDriver());

        click(SEARCH_CITY_FIELD,getWait());
        input(cityName,SEARCH_CITY_FIELD,getDriver());
        click(SEARCH_BUTTON,getWait());
        elementToBeVisible(SEARCH_DROPDOWN_MENU, getWait());
        click(PARIS_FR_CHOICE_DROPDOWN_MENU,getWait());
        waitTextChanged(H_2_CITY_COUNTRY_HEADER,getWait(),getDriver(),old);

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }


}

