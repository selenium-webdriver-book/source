package swip.ch08windows.modals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.security.Credentials;

public class BootstrapModal implements Alert { // implement alert to ensure the interface is familiar
    private final SearchContext searchContext;

    public BootstrapModal(SearchContext searchContext) { // accept just the part of the page we are interested in
        this.searchContext = searchContext;
    }

    @Override
    public void dismiss() {
        searchContext.findElement(By.cssSelector("button.btn-default")).click(); // the cancel button
    }

    @Override
    public void accept() {
        searchContext.findElement(By.cssSelector("button.btn-primary")).click(); // the ok button
    }

    @Override
    public String getText() {
        return searchContext.findElement(By.cssSelector("h4.modal-title")).getText(); // the input
    }

    @Override
    public void sendKeys(String keysToSend) {
        searchContext.findElement(By.cssSelector("input[type='text']")).sendKeys(keysToSend);
    }

    @Override
    public void setCredentials(Credentials credentials) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void authenticateUsing(Credentials credentials) {
        throw new UnsupportedOperationException();
    }
}
