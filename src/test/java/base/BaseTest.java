package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.MainPage;
import utils.ReportUtils;
import utils.TestUtils;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    private final static String BASE_URL = "https://openweathermap.org/";

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public static String getBaseUrl() {

        return BASE_URL;
    }

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {

        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {
        driver = BaseUtils.createDriver();

        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log("TEST RUN", true);
        Reporter.log(ReportUtils.getClassNameTestName(method, result), true);
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {
        Reporter.log(ReportUtils.getTestStatistics(method, result), true);

        driver.quit();
        webDriverWait = null;
    }

    protected WebDriver getDriver() {

        return driver;
    }

    protected WebDriverWait getWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }

        return webDriverWait;
    }

    public void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public MainPage openBaseURL() {
        TestUtils.loadBaseUrlPage(getDriver(), getWait());

        if (TestUtils.isH2HeaderExists(getDriver())) {
            Reporter.log("BaseURL page was loaded successfully ", true);
        } else {
            TestUtils.reLoadBaseUrlPage(getDriver(), getWait());
        }

        return new MainPage(getDriver());
    }

    public String getExternalPageTitle() {
        if(getDriver().getTitle().isEmpty()) {
            TestUtils.waitForPageLoaded(getDriver());
        }

        return getDriver().getTitle();
    }

    public String getExternalPageURL() {

        return getDriver().getCurrentUrl();
    }
    /*-------------------------------------------------*/
    public void waitForGrayDisappeared() {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.className("own-loader-container")));


    }
    public String getText(By by){
        return getDriver().findElement(by).getText();
    }
    public void click (By by){

        getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait().until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    public void input (String text, By by){
        getDriver().findElement(by).sendKeys(text);

    }

    public void elementToBeVisible(By by){
        getWait().until(ExpectedConditions.visibilityOfElementLocated((by)));

    }
    public void waitTextChanged (By by, String text ){
        getWait().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(getDriver().findElement(by), text)));

    }

}
