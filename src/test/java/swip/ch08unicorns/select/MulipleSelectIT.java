package swip.ch08unicorns.select;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SeleniumWebDriverRunner.class)
public class MulipleSelectIT {

    @Inject
    private WebDriver driver;

    @Test
    public void singleSelect() throws Exception {
        driver.get("/select-boxes.html");

        Select single = new Select(driver.findElement(By.name("single")));
        single.selectByVisibleText("Ginger");

        assertEquals("Ginger", single.getFirstSelectedOption().getText());
    }

    @Test
    public void multipleSelect() throws Exception {
        driver.get("/select-boxes.html");

        Select multiple = new Select(driver.findElement(By.name("multiple")));

        multiple.deselectAll(); // if need be, make sure none are selected
        multiple.selectByVisibleText("Labrador");
        multiple.selectByVisibleText("Sausage Dog");

        assertEquals(
                Arrays.asList("Labrador", "Sausage Dog"),
                multiple // use a stream to extract the text values
                        .getAllSelectedOptions()
                        .stream()
                        .map(e -> e.getText())
                        .collect(Collectors.toList())
        );
    }
}
