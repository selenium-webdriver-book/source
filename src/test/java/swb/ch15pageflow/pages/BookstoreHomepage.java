package swb.ch15pageflow.pages;

import swb.framework.Browser;

import static swb.locators.ClassName.SEARCH_BUTTON;
import static swb.locators.Id.SEARCH_INPUT;
import static swb.locators.Id.SECOND_NAVBAR;

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