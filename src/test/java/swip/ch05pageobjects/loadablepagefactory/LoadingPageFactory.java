package swip.ch05pageobjects.loadablepagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoadingPageFactory {
    public static <T> T get(WebDriver driver, Class<T> pageObjectClass) {

        driver.get(pageObjectClass.getAnnotation(Path.class).value()); // get the page

        Verify verify = pageObjectClass.getAnnotation(Verify.class); // we assume that @Verify will be present

        String expectedPageTitle = verify.title();
        if (!expectedPageTitle.equals(Verify.INVALID_TITLE)) { // if the page title is defined
            String actualPageTitle = driver.getTitle();
            if (!expectedPageTitle.equals(actualPageTitle)) {
                throw new IllegalStateException(
                        String.format("expected page title %s but was %s", expectedPageTitle, actualPageTitle));
            }
        }

        return PageFactory.initElements(driver, pageObjectClass); // delegate the populating of elements to PageFactory
    }
}
