package swip.eap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SeleniumWebDriverRunner.class)
public class ElementIsPresentIT {
    @Inject
    private WebDriver driver;

    @Test
    public void checkingAnElementIsPresent() throws Exception {
        driver.get("http://localhost:8080/styled-elements.html");

        assertThat(driver.findElements(By.id("invisible")), hasSize(1)); // #A check the list Of elements will be size 1
    }
}
