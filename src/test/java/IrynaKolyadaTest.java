import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

public class IrynaKolyadaTest extends BaseTest {
    final static By H2_CITY_COUNTRY_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");
    final static By SEARCH_CITY_FIELD = By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']");
    final static By SEARCH_BUTTON = By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']");
    final static By SEARCH_DROPDOWN_MENU = By.className("search-dropdown-menu");
    final static By PARIS_FR_CHOICE_IN_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']");
    final static By PLACEHOLDER_FIELD = By.xpath("//div[@id='desktop-menu']//form[@role='search']");
    final static By PLACEHOLDER_TEXT = By.xpath("//div[@id='desktop-menu']//input[@type='text']");

    @Test
    public void testH2TagText_WhenSearchingCityCountry()  {
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        String oldH2Header = getText(H2_CITY_COUNTRY_HEADER);
        click(SEARCH_CITY_FIELD);
        input(cityName, SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        waitElementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_IN_DROPDOWN_MENU);
        waitTextToBeChanged(H2_CITY_COUNTRY_HEADER, oldH2Header);
        String actualResult = getText(H2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTextOfPlaceholderNavBar_homePage()  {
        final String expectedResult = "Weather in your city";

        openBaseURL();

        Assert.assertTrue(isDisplayed(PLACEHOLDER_FIELD));

        Assert.assertEquals(getTextByAttribute(PLACEHOLDER_TEXT, "placeholder"), expectedResult);
    }

}
