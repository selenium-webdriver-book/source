package swip.ch03interacting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.junit.SeleniumWebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(SeleniumWebDriverRunner.class)
public class FormPageObjectIT<W extends JavascriptExecutor & WebDriver & HasCapabilities> {
    @Inject
    private W driver;
    @Inject
    private URI baseUrl;

    @Test
    public void completingAForm() throws Exception {
        driver.get(baseUrl + "/registration-form.html");
        RegistrationForm registrationForm = PageFactory.initElements(driver, RegistrationForm.class);

        registrationForm.setEmail("john@doe.com");
        registrationForm.setPassword("secret");
        registrationForm.setHearAbout("Friend");
        registrationForm.setContact(RegistrationForm.Contact.EMAIL);
        registrationForm.selectInterests("Movies", "Music");
        registrationForm.submit();

        new WebDriverWait(driver, 1000).until((WebDriver driver) -> driver.getTitle().contains("Thank You"));
    }
}
