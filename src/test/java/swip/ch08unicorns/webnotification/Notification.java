package swip.ch08unicorns.webnotification;

import com.google.common.base.Predicate;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Notification {

    private final JavascriptExecutor javascriptExecutor;

    public Notification(JavascriptExecutor javascriptExecutor) {
        this.javascriptExecutor = javascriptExecutor;
        this.javascriptExecutor.executeScript(
                "Notification = function(title, options) { \n" +
                        "    var n = this; \n" +
                        "    window.notificationTitle = title; \n" +
                        "    notificationOptions = options;\n" +
                        "    setTimeout(function() { \n" +
                        "        (n.onshow || function() {}) (); \n" +
                        "    }, 50);\n" +
                        "}");
    }

    public String getTitle() {
        return String.valueOf(javascriptExecutor.executeScript("window.notificationTitle"));
    }

    public Predicate<WebDriver> isDisplayed() {
        return (driver) -> !"null".equals(getTitle());
    }
}
