package swb.ch02locatingelements;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByChained;
import swb.framework.WebDriverRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(WebDriverRunner.class)
public class LocatorCompositionIT {

    @Inject
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver.get("/registration-form.html");
    }

    @Test
    public void byIdOrName() throws Exception {
        driver.findElement(new ByIdOrName("password"));
    }

    @Test
    public void byChained() throws Exception {
        driver.findElement(
                new ByChained(
                        By.id("registration-form"),
                        By.xpath("//label[contains(.,'Email')]"),
                        By.tagName("input")
                )
        );
    }

    @Test
    public void byAnd() throws Exception {
        assertEquals("password",
                driver.findElement(AllBy.all(By.tagName("input"), By.name("password")))
                        .getAttribute("name"));
    }

    @Test
    public void byAny() throws Exception {
        assertEquals("email",
                driver.findElement(AnyBy.any(By.id("email"), By.name("email")))
                        .getAttribute("name"));
    }

    @Test
    public void byExcludes() throws Exception {
        assertEquals("phone",
                driver.findElements(
                        AllBy.all(
                                By.name("contact"),
                                NotBy.not(By.cssSelector("*[value='email']"))
                        )
                )
                        .get(0).getAttribute("value"));
    }
}
