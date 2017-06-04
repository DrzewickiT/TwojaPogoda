package twojapogoda.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class Homepage {

    WebDriver driver;

    WebDriverWait wait;

    public int mapTopIndex;

    By city = By.cssSelector(".top .float .title");

    By infoBoxTemperatures = By.cssSelector(".float .day span");

    By infoBoxTemperaturesUnit = By.cssSelector(".float .day sup");

    By infoBoxImages = By.cssSelector("#box-info-tab3 img");

    By mapTopDayDropdown = By.cssSelector(".map-top div:last-child");

    By mapTopDayDropdownItems = By.cssSelector((".map-top div:last-child li"));

    By mapTopCities = By.cssSelector(".map-city .name");

    By mapTopTemperatures = By.cssSelector(".map-city .value");

    By mapTopImages = By.cssSelector(".map-city img");

    By mapLoader = By.cssSelector("div.map-loader");

    public Homepage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    private String getCity() {
        return driver.findElement(city).getText();
    }

    public int assignMapTopIndex() {
        int index = -1;
        String city = getCity();
        List<WebElement> cities = driver.findElements(mapTopCities);
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getText().equals(city)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void selectDay(int index) {
        wait.until(elementToBeClickable(driver.findElement(mapTopDayDropdown)));
        driver.findElement(mapTopDayDropdown).click();
        driver.findElements(mapTopDayDropdownItems).get(index).click();
        wait.until(invisibilityOf(driver.findElement(mapLoader)));
    }

    public String getTopMapTemperature(int indexOfCity) {
        return driver.findElements(mapTopTemperatures).get(indexOfCity).getText();
    }

    public String getTopMapImageSource(int indexOfCity) {
        return driver.findElements(mapTopImages).get(indexOfCity).getAttribute("src").toLowerCase();
    }

    public String getTopMapImageTitle(int indexOfCity) {
        return driver.findElements(mapTopImages).get(indexOfCity).getAttribute("title").toLowerCase();
    }

    public String getInfoBoxTemperature(int dayIndex) {
        return driver.findElements(infoBoxTemperatures).get(dayIndex).getText();
    }

    public String getInfoBoxTemperatureUnit(int dayIndex) {
        return driver.findElements(infoBoxTemperaturesUnit).get(dayIndex).getText();
    }

    public String getInfoBoxImageSource(int dayIndex) {
        return driver.findElements(infoBoxImages).get(dayIndex).getAttribute("src").toLowerCase();
    }

    public String getInfoBoxImageTitle(int dayIndex) {
        return driver.findElements(infoBoxImages).get(dayIndex).getAttribute("title").toLowerCase();
    }
}
