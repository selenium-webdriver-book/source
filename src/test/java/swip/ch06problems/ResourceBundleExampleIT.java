package swip.ch06problems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import swip.framework.Config;
import swip.framework.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;
import java.util.Locale;
import java.util.ResourceBundle;

@RunWith(WebDriverRunner.class)
@Config(exclude = "browserName=htmlunit")
public class ResourceBundleExampleIT {

    @Inject
    private WebDriver driver;
    @Inject
    private URI baseUrl;

    @Test
    public void englishTranslation() throws Exception {
        driver.get(baseUrl + "/login.html");

        ResourceBundle strings = ResourceBundle.getBundle("strings", Locale.ENGLISH);

        driver.findElement(By.linkText(strings.getString("forgotten.password")));
    }

    @Test
    public void spanishTranslation() throws Exception {
        driver.get(baseUrl + "/es/login.html");

        ResourceBundle strings = ResourceBundle.getBundle("strings", Locale.forLanguageTag("es"));

        driver.findElement(By.linkText(strings.getString("forgotten.password")));
    }

    @Test
    public void configuredTranslation() throws Exception {
        String language = Locale.getDefault().getLanguage();

        driver.get(baseUrl + "/" + language + "/login.html");

        ResourceBundle strings = ResourceBundle.getBundle("strings");

        driver.findElement(By.linkText(strings.getString("forgotten.password")));
    }
}
