package swip.ch15pageflow.pages;

import org.openqa.selenium.By;
import swip.framework.Browser;

public class BookListPage {

    private Browser browser;

    public BookListPage(Browser browser) {
        this.browser = browser;
    }

    public void chooseBook(String bookname) {
        browser.click(() -> By.partialLinkText(bookname));
    }
}