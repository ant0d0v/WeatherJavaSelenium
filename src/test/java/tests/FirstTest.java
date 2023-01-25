package tests;

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



    @Test
    public void testH2Text_WhenSearchingCityCountry()  {

        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        openBaseURL();
        waitForGrayDisappeared();

        String old = getText(H_2_CITY_COUNTRY_HEADER);

        click(SEARCH_CITY_FIELD);
        input(cityName,SEARCH_CITY_FIELD);
        click(SEARCH_BUTTON);
        elementToBeVisible(SEARCH_DROPDOWN_MENU);
        click(PARIS_FR_CHOICE_DROPDOWN_MENU);
        waitTextChanged(H_2_CITY_COUNTRY_HEADER,old);

        String actualResult = getText(H_2_CITY_COUNTRY_HEADER);

        Assert.assertEquals(actualResult, expectedResult);


    }


}

