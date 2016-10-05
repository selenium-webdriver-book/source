package swb.ch08windows.modals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.security.Credentials;

public class OtherModel implements Alert {
    public OtherModel(SearchContext searchContext) {
    }

    @Override
    public void dismiss() {

    }

    @Override
    public void accept() {

    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void sendKeys(String keysToSend) {

    }

    @Override
    public void setCredentials(Credentials credentials) {

    }

    @Override
    public void authenticateUsing(Credentials credentials) {

    }
}
