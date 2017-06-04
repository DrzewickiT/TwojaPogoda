package twojapogoda.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Homepage {

    WebDriver driver;

    private int mapTopIndex;

    private String mainCity;

    By city = By.cssSelector(".top .float .title");

    By infoBoxTemperatures = By.cssSelector(".float .day span");

    By infoBoxImages = By.cssSelector("#box-info-tab3 img");

    By mapTopDayDropdown = By.cssSelector(".map-top div:last-child");

    By mapTopDayDropdownItems = By.cssSelector((".map-top div:last-child li"));

    By mapTopCities = By.cssSelector(".map-city .name");

    By mapTopTemperatures = By.cssSelector(".map-city .value");

    By mapTopImages = By.cssSelector(".map-city img");

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    private String getCity() {
        return this.mainCity = driver.findElement(city).getText();
    }

    public int assignMapTopIndex() {
        List<WebElement> cities = driver.findElements(mapTopCities);
        return cities.indexOf(getCity());
    }

    
}
