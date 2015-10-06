package swip.ch04examiningapage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import swip.ch02locatingelements.InputBy;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(WebDriverRunner.class)
public class VerifyingFormIT {

    @Inject
    private WebDriver driver;

    private static List<String> selectedText(WebElement interestsElement) {

        List<String> selectedText = new ArrayList<>();

        for (WebElement option : new Select(interestsElement).getAllSelectedOptions()) {
            selectedText.add(option.getText());
        }

        Collections.sort(selectedText);

        return selectedText;
    }

    @Test
    public void verifyAForm() throws Exception {
        driver.get("/pre-filled-form.html");

        WebElement emailInput = driver.findElement(By.name("email"));
        assertEquals("john.doe@swip.com", emailInput.getAttribute("value"));
        //assertTrue(Boolean.parseBoolean(emailInput.getAttribute("readonly")));

        WebElement passwordInput = driver.findElement(By.name("password"));
        assertEquals("secret", passwordInput.getAttribute("value"));
        assertTrue(Boolean.parseBoolean(passwordInput.getAttribute("disabled")));

        assertTrue(driver.findElement(InputBy.label("phone")).isSelected());

        Select hearAboutSelect = new Select(driver.findElement(By.name("hearAbout")));
        assertEquals("Friend", hearAboutSelect.getFirstSelectedOption().getText());


        WebElement interestsElement = driver.findElement(By.name("interest"));
        assertEquals(
                Arrays.asList("Movies", "Music"),
                selectedText(interestsElement)
        );

        assertEquals("Tell us what you think.", driver.findElement(By.name("comments")).getText());

        assertTrue(driver.findElement(By.name("terms")).isSelected());

    }
}
