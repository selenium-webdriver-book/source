package swip.ch15pageflow.pages;

import swip.framework.Browser;

import static swip.locators.ClassName.SEARCH_BUTTON;
import static swip.locators.Id.SEARCH_INPUT;
import static swip.locators.Id.SECOND_NAVBAR;

public class BookstoreHomepage {

    private Browser browser;

    public BookstoreHomepage(Browser browser) {
        this.browser = browser;
    }

    public void searchBook(String bookname) {
        browser.setInputText(SEARCH_INPUT, bookname);
        browser.await(SECOND_NAVBAR).click(SEARCH_BUTTON);
    }
}