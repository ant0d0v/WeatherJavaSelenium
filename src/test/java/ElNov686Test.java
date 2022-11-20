import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class ElNov686Test extends BaseTest {
    private final By SEARCH_CITY_FIELD = By
            .xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    private final By SEARCH_BUTTON = By.xpath("//div[@id='weather-widget']//button[@type='submit']");
    private final By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    private final By PARIS_FR_FROM_DROPDOWN_MENU = By
            .xpath("//ul[@class = 'search-dropdown-menu']/li/span[text ()= 'Paris, FR ']");
    private final By H2_CITYNAME_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");


    @Test
    public void testH2TagTextWhenSearchingCityCountry() {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();

        String oldH2Header = getText(H2_CITYNAME_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_FROM_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITYNAME_HEADER, oldH2Header);

        String actualResult = getText(H2_CITYNAME_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTemperatureImperialFahrenheitVerify() {
        String expectedResult = "F";

        openBaseURL();

        click(By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"));
        String actualResult = getText(By.xpath("//span[@class='heading']"));

        actualResult = actualResult.substring(actualResult.length() - 1);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMenuAPI_Verify30orangeButtons() {
        int expectedResult = 30;

        openBaseURL();

        click(By.xpath("//div[@id='desktop-menu']//a[normalize-space()='API']"));
        int actualResult = countOrangeButtons(By.xpath("//a[contains(@class, 'btn_block orange round') " +
                "or contains(@class, 'ow-btn round btn-orange')]"));

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testMainNavBarMenuPartnersMenuisclickable() {
        String expectedResult = "https://openweathermap.org/examples";

        openBaseURL();

        click(By.xpath("//div[@id='desktop-menu']//a[normalize-space()='Partners']"));
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testStartWeatherLocationVerifyLocationButtonShowingDefaultLocation() {
        String expectedResult1 = "Location unavailable. Displaying default location: London";
        String expectedResult2 = "London, GB";

        openBaseURL();

        click(By.xpath("//div[@class='control-el']//*[name()='svg']"));

        String actualResult1 = getText(By
                .xpath("//span[contains(text(),'Location unavailable. Displaying default location:')]"));
        String actualResult2 = getText(By
                .xpath("//h2[normalize-space()='London, GB']"));

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }
}

