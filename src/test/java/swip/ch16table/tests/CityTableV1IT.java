package swip.ch16table.tests;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch16table.domain.City;
import swip.ch16table.v1.CityTable;
import swip.ch16table.v1.CityTableContents;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static swip.ch15pageflow.locators.TagName.TABLE;

;

@RunWith(BrowserRunner.class)
public class CityTableV1IT {

    @Inject private Browser browser;

    private static final CityTableContents EXPECTED =
        new CityTableContents(
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

    @Test public void testReadFromTable() {

        browser.get("/city-table.html");

        CityTable table = new CityTable(browser.untilFound(TABLE));

        CityTableContents actual = table.getContents();

        assertEquals(EXPECTED.describeDiff(actual), EXPECTED, actual);
    }

    private static final CityTableContents OUTDATED_EXPECTATION =
        new CityTableContents(
            Arrays.asList("Id", "City Name", "State Name"),
            Arrays.asList(
                new City(1, "Xian", "Shanxi")
                , new City(2, "Guangzhou", "Guangdong")
                , new City(3, "Shaoguan", "Guangdong")
                , new City(11, "Dallas", "Texas")
            )
        );

    @Test
    @Ignore("You can remove this to run it and check the output")
    public void failedToReadFromTable() {

        browser.get("/city-table.html");

        CityTable table = new CityTable(browser.untilFound(TABLE));

        CityTableContents actual = table.getContents();

        assertEquals(OUTDATED_EXPECTATION.describeDiff(actual),
            OUTDATED_EXPECTATION, actual);
    }

}
