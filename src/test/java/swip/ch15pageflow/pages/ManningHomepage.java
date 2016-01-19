package swip.ch15pageflow.pages;

import org.openqa.selenium.By;
import swip.ch15pageflow.framework.Browser;

import static swip.ch15pageflow.locators.BookStoreId.SEARCH_INPUT;
import static swip.ch15pageflow.locators.Xpath.SEARCH_BUTTON;

public class ManningHomepage {

    private Browser browser;

    public ManningHomepage(Browser browser) {
        browser.get("/bookstore/");
        this.browser = browser;
    }

    public void searchBook(String bookname) {
        browser.setInputText(SEARCH_INPUT, bookname);
        browser.untilFound(SEARCH_BUTTON).click();
        browser.untilFound(() -> By.partialLinkText(bookname))
            .click();
    }

    public void searchBook2(String bookname) {
        browser.setInputText(SEARCH_INPUT, bookname);
        browser.untilFound2(SEARCH_BUTTON).click();
        browser.untilFound2(() -> By.partialLinkText(bookname))
            .click2();
    }
}