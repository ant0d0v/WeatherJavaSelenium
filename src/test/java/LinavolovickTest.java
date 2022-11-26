import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinavolovickTest extends BaseTest {
    private final String BASE_URL = "https://openweathermap.org/";
    private final String WEATHER_API_URL = "https://openweathermap.org/api#current";
    private final By H_2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    private final By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    private final By LOGO = By.xpath("//li[@class='logo']/descendant::img");
    private final By CURRENT_AND_FORECAST_APIS_LINK = By.xpath("//div[@id='footer-website']//a[@href='/api#current']");

    public void openBaseURL(){
        getDriver().get(BASE_URL);
    }

    private void waitForGreyFrameDisapearred() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container"))
        );
    }

    private String getText(By by, WebDriver driver) {

        return driver.findElement(by).getText();
    }

    private String getCurrentUrl() {

        return getDriver().getCurrentUrl();
    }

    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void input(String text, By by, WebDriver driver) {
        driver.findElement(by).sendKeys(text);
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

   private void waitTextChanged(By by, String text, WebDriver driver, WebDriverWait wait) {
        wait.until(ExpectedConditions
                .not(ExpectedConditions.textToBePresentInElement(driver.findElement(by), text)));
    }

    private void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGreyFrameDisapearred();

        final String oldH2Header = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        click(SEARCH_CITY_FIELD, getWait10());
        input(cityName, SEARCH_CITY_FIELD, getDriver());
        click(SEARCH_BUTTON, getWait10());
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU, getWait10());
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU, getWait10());
        waitTextChanged(H_2_CITY_COUNTRY_HEADER, oldH2Header, getDriver(), getWait10());

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER, getDriver());

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testVerifyLogoExistsAndRedirectsToStartPage() {
        final String expectedResult = BASE_URL;

        openBaseURL();
        waitForGreyFrameDisapearred();
        click(LOGO, getWait10());

        Assert.assertEquals(getCurrentUrl(), expectedResult);
    }

    @Test
    public void testCurrentAndForecastAPIsLink() {
        final String expectedResult = WEATHER_API_URL;

        openBaseURL();
        waitForGreyFrameDisapearred();
        scrollDown();
        click(CURRENT_AND_FORECAST_APIS_LINK, getWait10());

        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }
}