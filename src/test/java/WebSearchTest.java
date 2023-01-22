import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebSearchTest extends BaseTest {

    @Test
    public void testSearchForLanguageByName_HappyPath() {
        final String BASE_URL = "https://www.99-bottles-of-beer.net";
        final String LANGUAGE_PYTHON = "python";

        getDriver().get(BASE_URL);

        WebElement searchLanguagesMenu = getDriver().findElement(
                By.xpath("//ul[@id = 'menu']/li/a[@href = '/search.html']"));
        searchLanguagesMenu.click();

        WebElement searchForField = getDriver().findElement(By.name("search"));

        searchForField.click();
        searchForField.sendKeys(LANGUAGE_PYTHON);

        WebElement goButton = getDriver().findElement(By.name("submitsearch"));
        goButton.click();

        // весь список результатов поиска помищаем в список
        List<WebElement> languagesNamesList = getDriver().findElements(
                By.xpath("//table[@id = 'category']//tr/td[1]/a"));
        //подтверждаем что поиск выдал какие-то результаты
        Assert.assertTrue(languagesNamesList.size() > 0);


        //идем по каждому результату из списка и смотрим содержаться ли в нем контент "python"
        for ( int i = 0; i < languagesNamesList.size(); i++) {
            Assert.assertTrue(languagesNamesList.get(i).getText().toLowerCase().contains(LANGUAGE_PYTHON));
        }



    }
}
