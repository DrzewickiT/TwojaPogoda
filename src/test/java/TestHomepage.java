import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import twojapogoda.objects.Homepage;

import java.util.concurrent.TimeUnit;

public class TestHomepage {

    WebDriver driver;

    Homepage homepage;

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
    public void testTemperatureValuesMatch(int daysIndex) {
        homepage.selectDay(daysIndex);
        String infoBoxTemperature = homepage.getInfoBoxTemperature(daysIndex) + ' ' + homepage.getInfoBoxTemperatureUnit(daysIndex);
        Assert.assertEquals(infoBoxTemperature, homepage.getTopMapTemperature(homepage.mapTopIndex));
    }

    @Test(dataProvider = "DaysIndexProvider")
    public void testImageSourceValuesMatch(int daysIndex) {
        homepage.selectDay(daysIndex);
        Assert.assertEquals(homepage.getInfoBoxImageSource(daysIndex), homepage.getTopMapImageSource(homepage.mapTopIndex));
    }

    @Test(dataProvider = "DaysIndexProvider")
    public void testImageTitleValuesMatch(int daysIndex) {
        homepage.selectDay(daysIndex);
        Assert.assertEquals(homepage.getInfoBoxImageTitle(daysIndex), homepage.getTopMapImageTitle(homepage.mapTopIndex));
    }

    @AfterTest
    public void unset() {
        driver.close();
        driver.quit();
    }
}
