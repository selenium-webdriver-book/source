package swb.ch16table.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import swb.framework.Browser;
import swb.framework.BrowserRunner;
import swb.ch16table.domain.City;
import swb.ch16table.v2.CityTable;
import swb.ch16table.v2.CityTableContents;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static swb.locators.TagName.TABLE;

@RunWith(BrowserRunner.class)
public class CityTable_v1_IT {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Inject
    private Browser browser;

    @Before
    public void gotoSite() {
        browser.get("/city-table.html");
    }

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

    @Test
    public void testReadFromTable() {

        CityTable table = new CityTable(browser.await(TABLE));

        CityTableContents actual = table.getContents();

        assertEquals(EXPECTED.describeDiff(actual), EXPECTED, actual);
    }

    private static final CityTableContents OUTDATED_EXPECTED =
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

        CityTable table = new CityTable(browser.await(TABLE));

        CityTableContents actual = table.getContents();

        assertEquals(OUTDATED_EXPECTED.describeDiff(actual), OUTDATED_EXPECTED, actual);
    }

}
