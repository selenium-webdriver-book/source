package swip.ch15table.city;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.ch14elements.framework.Browser;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class CityTableIT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private Browser browser;

    @Inject
    public void setWebDriver(WebDriver webDriver) {
        browser = new Browser(webDriver);
    }

    @Test
    public void testReadFromTable() {

        browser.get("http://localhost:8080/city-table.html");

        CityTable table = new CityTable(
            browser.findElement(By.tagName("table"))
        );

        CityTableContents actual = table.getContents();

        CityTableContents expected = new CityTableContents(
            Arrays.asList("Id", "City Name", "State Name"),
            Arrays.asList(
                new City(1, "Xian", "Shanxi")
                , new City(2, "Guangzhou", "Guangdong")
                , new City(3, "Shaoguan", "Guangdong")
                , new City(4, "Tianjin", "Tianjin")
                , new City(5, "Changsha", "Huana")
                , new City(6, "Shenzhen", "Guangzhou")
                , new City(7, "Hong Kong", "Hong Kong")
                , new City(8, "Hangzhou", "Zhejiang")
                , new City(9, "Singapore", "Singapore")
                , new City(9, "New York", "New York")
                , new City(10, "Sydney", "New South Wales")
                , new City(11, "Dallas", "Texas")
            )
        );

        assertEquals(expected.describeDiff(actual), expected, actual);
    }

}
