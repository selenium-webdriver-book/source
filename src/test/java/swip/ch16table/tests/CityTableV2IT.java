package swip.ch16table.tests;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import swip.ch15pageflow.framework.Browser;
import swip.ch15pageflow.framework.BrowserRunner;
import swip.ch16table.domain.City;
import swip.ch16table.v2.Table;
import swip.ch16table.v2.TableContents;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static swip.ch15pageflow.locators.TagName.TABLE;
import static swip.ch16table.mapper.CityMapper.MAPPER_LAMBDA;

@RunWith(BrowserRunner.class)
public class CityTableV2IT {

    @Inject private Browser browser;

    private static final TableContents<City> EXPECTED = new TableContents<>(
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

    private static final TableContents<City> EXPECTED_FAILURE = new TableContents<>(
        Arrays.asList("Id", "City Name", "State Name"),
        Arrays.asList(
            new City(1, "Xian", "Shanxi")
            , new City(2, "Guangzhou", "Guangdong")
            , new City(3, "Shaoguan", "Guangdong")
            , new City(4, "Dallas", "Texas")
        )
    );

    @Test public void testReadFromTableJava8() {

        browser.get("/city-table.html");

        Table<City> table = new Table<>(browser.untilFound(TABLE),
            cells ->
                new City(Integer.parseInt(cells.get(0).getText()),
                    cells.get(1).getText(),
                    cells.get(2).getText())
        );

        TableContents<City> actual = table.getContents();

        assertEquals(EXPECTED.describeDiff(actual), EXPECTED, actual);
    }

    @Test
    @Ignore("You can remove this to run it and check the output")
    public void testReadFromTableButFailed() {

        browser.get("/city-table.html");

        Table<City> table = new Table<>(
            browser.untilFound(TABLE), MAPPER_LAMBDA
        );

        TableContents<City> actual = table.getContents();

        assertEquals(EXPECTED_FAILURE.describeDiff(actual), EXPECTED_FAILURE, actual);
    }

}
