import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import twojapogoda.objects.Homepage;

import java.util.concurrent.TimeUnit;

public class TestHomepage {

    WebDriver driver;

    Homepage homepage;

    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.firefox.marionette", "./geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://www.twojapogoda.pl/");
        homepage = new Homepage(driver);
        homepage.mapTopIndex = homepage.assignMapTopIndex();
    }

    @DataProvider(name = "DaysIndexProvider")
    public Object[][] daysIndexData() {
        return new Object[][] {{0}, {1}, {2}, {3}};
    }

    @Test(dataProvider = "DaysIndexProvider")
    public void test_temperature_match(int daysIndex) {
        homepage.selectDay(daysIndex);
        String infoBoxTemperature = homepage.getInfoBoxTemperature(daysIndex) + ' ' + homepage.getInfoBoxTemperatureUnit(daysIndex);
        softAssert.assertEquals(infoBoxTemperature, homepage.getTopMapTemperature(homepage.mapTopIndex));
        softAssert.assertEquals(homepage.getInfoBoxImageSource(daysIndex), homepage.getTopMapImageSource(homepage.mapTopIndex));
        softAssert.assertEquals(homepage.getInfoBoxImageTitle(daysIndex), homepage.getTopMapImageTitle(homepage.mapTopIndex));
        softAssert.assertAll();
    }

    @AfterTest
    public void unset() {
        driver.close();
        driver.quit();
    }
}
