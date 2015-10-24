package swip.ch10javascript.webnotification;

import org.openqa.selenium.JavascriptExecutor;


public class Notification {

    private final JavascriptExecutor javascriptExecutor;

    public Notification(JavascriptExecutor javascriptExecutor) {
        this.javascriptExecutor = javascriptExecutor;
        this.javascriptExecutor.executeScript(
                "Notification = function(title, options) { \n" +
                        "    var n = this; \n" +
                        "    notificationTitle = title; \n" +
                        "    notificationOptions = options;\n" +
                        "    setTimeout(function() { \n" +
                        "        (n.onshow || function() {}) (); \n" +
                        "    }, 50);\n" +
                        "}");
    }

    public String getTitle() {
        return (String) javascriptExecutor.executeScript("return notificationTitle");
    }
}
