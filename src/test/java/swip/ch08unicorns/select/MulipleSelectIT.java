package swip.ch08unicorns.select;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import swip.ch07managingwebdriver.Config;
import swip.ch07managingwebdriver.WebDriverRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=chrome")
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
        driver.get("http://localhost:8080/select-boxes.html");

        Select multiple = new Select(driver.findElement(By.name("multiple")));

        multiple.deselectAll(); // if need be, make sure none are selected
        multiple.selectByVisibleText("Labrador");
        multiple.selectByVisibleText("Sausage Dog");

        List<String> selectedOptions = new ArrayList<>();
        for (WebElement option : multiple.getAllSelectedOptions()) {
            selectedOptions.add(option.getText());
        }

        assertEquals(Arrays.asList("Labrador", "Sausage Dog"), selectedOptions);
    }
}
