package swip.ch15pageflow;

import org.openqa.selenium.By;
import swip.ch15pageflow.framework.Browser;

public class ManningHomepage {

    private Browser browser;

    public ManningHomepage(Browser browser) {
        this.browser = browser;
    }

    public void searchBook(String bookname) {
        browser.setInputText(By.id("navbar-search"), bookname);
        browser.untilFound(By.xpath("//*[@id=\"desktop-search-form\"]/div/div/span/button")).click();
        browser.untilFound(By.partialLinkText(bookname)).click();
    }

}