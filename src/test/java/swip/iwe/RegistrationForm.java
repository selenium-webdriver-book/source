package swip.iwe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import swip.le.InputBy;

public class RegistrationForm {

    private final WebDriver driver;
    private final Select hearAbout;
    private final Select interests;
    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(tagName = "button")
    private WebElement submit;

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
        hearAbout = new Select(driver.findElement(By.name("hearAbout")));
        interests = new Select(driver.findElement(By.name("interest")));
    }

    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    public void submit() {
        submit.click();
    }

    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    public void setHearAbout(String hearAbout) {
        this.hearAbout.selectByVisibleText(hearAbout);
    }

    public void setContact(Contact contact) {
        driver.findElement(InputBy.label(contact.name().toLowerCase())).click();
    }

    public void selectInterests(String... interests) {
        for (String interest : interests) {
            this.interests.selectByVisibleText(interest);
        }
    }

    public enum Contact {EMAIL, PHONE}
}
