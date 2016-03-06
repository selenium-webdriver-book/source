package swip.ch15pageflow.pages;

import org.openqa.selenium.By;
import swip.ch15pageflow.framework.Browser;

import static swip.ch15pageflow.locators.ClassName.SEARCH_BUTTON;
import static swip.ch15pageflow.locators.Id.SEARCH_INPUT;
import static swip.ch15pageflow.locators.Id.SECOND_NAVBAR;

public class BookstoreHomepage {

    private Browser browser;

    public BookstoreHomepage(Browser browser) {
        browser.get("/bookstore/");
        this.browser = browser;
    }

    public void searchBook(String bookname) {
        browser.setInputText(SEARCH_INPUT, bookname);
        browser.untilFound(SECOND_NAVBAR).untilFound(SEARCH_BUTTON).click();
        browser.untilFound(() -> By.partialLinkText(bookname))
            .click();
    }

    public void searchBook2(String bookname) {
        browser.setInputText(SEARCH_INPUT, bookname);
        browser.untilFound2(SECOND_NAVBAR).untilFound2(SEARCH_BUTTON).click();
        browser.untilFound2(() -> By.partialLinkText(bookname))
            .click2();
    }
}