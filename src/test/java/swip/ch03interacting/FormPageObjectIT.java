package swip.ch03interacting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import swip.ch07managingwebdriver.injecting.WebDriverRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(WebDriverRunner.class)
public class FormPageObjectIT {
    @Inject
    private WebDriver driver;
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
