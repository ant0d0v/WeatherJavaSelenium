import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class DimaZadrutsiyTest extends BaseTest {

    final static String BASE_URL = "https://openweathermap.org/";

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }

    private void click(String whereToClick, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(whereToClick))));
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(whereToClick)))).click();
    }

    private void seeElement(String element) {
        getDriver().findElement(By.xpath(element));
    }

    private String getText(String where, String attribute) {

        return getDriver().findElement(By.xpath(where)).getAttribute(attribute);
    }

    private String getText(String where) {

        return getDriver().findElement(By.xpath(where)).getText();
    }

    private void input(String where, String what) {
        getDriver().findElement(By.xpath(where)).sendKeys(what, Keys.ENTER);
        getWait5();
    }

    private void chooseFromDropDownMenu(String whatChoose, WebDriverWait wait) {
        getDriver().findElement(By.xpath(whatChoose));
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(whatChoose)))).click();
    }

    private void pressButton(String whichButton, WebDriverWait wait) {
        getDriver().findElement(By.xpath(whichButton));
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(whichButton)))).click();
    }

    @Test
    public void testUpdatePage() {
        String expectedResult = "Loading";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//li[@class='logo']", getWait5());

        seeElement("//div[@aria-label='Loading']");

        String actualResult = getText("//div[@aria-label='Loading']", "aria-label");

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testIconCurrentLocation() {
        String cityName = "Norway";

        String expectedResult = "London, GB";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click("//div//input[@placeholder='Search city']", getWait5());
        input("//div//input[@placeholder='Search city']", cityName);
        chooseFromDropDownMenu("//ul//span[text()='45.787, -87.904']", getWait5());
        pressButton("//div[@class='control-el']", getWait10());

        String actualResult = getText("//h2[@data-v-3e6e9f12]");

        Assert.assertEquals(actualResult, expectedResult);
    }
}
