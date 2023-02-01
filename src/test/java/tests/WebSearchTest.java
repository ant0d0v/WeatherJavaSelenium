package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WebSearchTest extends BaseTest {
    final String BASE_URL = "https://www.99-bottles-of-beer.net";
    final static By SEARCH_LANGUAGES_MENU = By.xpath("//ul[@id = 'menu']/li/a[@href = '/search.html']");
    final static By SEARCH_FOR_FIELD = By.name("search");
    final static By GO_BUTTON = By.name("submitsearch");
    final static By LANGUAGES_NAMES_LIST = By.xpath("//table[@id = 'category']//tr/td[1]/a");

    private void openBaseUrl(WebDriver driver) {
        driver.get(BASE_URL);
    }

    private WebElement getElement(By by, WebDriver driver) {

        return driver.findElement(by);

    }

    private void click(By by, WebDriver driver) {
        getElement(by, driver).click();
    }

 //   private void input(String text, By by, WebDriver driver) {
//        getElement(by, driver).sendKeys(text);
 //   }

    private List<WebElement> getListOfElements(By by, WebDriver driver) {

        return driver.findElements(by);
    }

    private int getListSize(By by, WebDriver driver) {
        return getListOfElements(by, driver).size();

    }

    private List<String> getElementsText(By by, WebDriver driver) {
        List<WebElement> elementsList = getListOfElements(by, driver);
        List<String> textList = new ArrayList<>();

        for (WebElement element : elementsList) {
            textList.add(element.getText().toLowerCase());
        }
        return textList;
    }


    @Test
    public void testSearchForLanguageByName_HappyPath() {
        final String LANGUAGE_NAME = "python";

        openBaseUrl(getDriver());
        click(SEARCH_LANGUAGES_MENU,getDriver());
        click(SEARCH_FOR_FIELD,getDriver());
        input(LANGUAGE_NAME,SEARCH_FOR_FIELD);
        click(GO_BUTTON,getDriver());
        // весь список результатов поиска помищаем в список
        getListOfElements(LANGUAGES_NAMES_LIST,getDriver());
        //подтверждаем что поиск выдал какие-то результаты
        List<String> languageNames = getElementsText(LANGUAGES_NAMES_LIST,getDriver());
        Assert.assertTrue(languageNames.size() > 0);
        //идем по каждому результату из списка и смотрим содержаться ли в нем контент "python"

        for (String languageName : languageNames){
            Assert.assertTrue(languageName.contains(LANGUAGE_NAME));
        }





    }
}
