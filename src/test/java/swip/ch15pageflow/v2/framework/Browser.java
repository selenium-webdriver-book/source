package swip.ch15pageflow.v2.framework;


import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Browser extends DelegatingWebDriver implements ExplicitWait, SearchScope {

    public Browser(WebDriver driver) {
        super(driver);
    }

    @Override
    public Element findElement(Supplier<By> by) {
        Element element = new Element(super.findElement(by.get()));
        element.setSearchContext(this);
        element.setLocator((SearchScope e) -> this.untilFound(by));
        return element;
    }

    public Stream<Element> findElements(Supplier<By> by) {
        return super.findElements(by.get()).stream().map(Element::new);
    }

    public void setInputText(Supplier<By> by, String value) {
        Retry retry = new Retry(5, 1, TimeUnit.SECONDS);

        retry.attempt(
            new Attemptable() {
                @Override
                public void attempt() throws Exception {
                    Element element = findElement(by);
                    element.clear();
                    element.sendKeys(value);
                    assert value.equals(element.getAttribute("value"));
                }
            }
        );
    }

    public void setInputTextLambda(Supplier<By> by, String value) {
        Retry retry = new Retry(5, 1, TimeUnit.SECONDS);

        retry.attempt(
            () -> {
                Element element = findElement(by);
                element.clear();
                element.sendKeys(value);
                assert value.equals(element.getAttribute("value"));
            }
        );
    }

    public String getText(Supplier<By> by) {
        return untilFound(by).getText();
    }

    public String getInputText(Supplier<By> by) {
        return untilFound(by).getAttribute("value");
    }

    public void setCheckboxValue(Supplier<By> by, boolean value) {
        Element checkbox = untilFound(by);
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    public boolean isChecked(Supplier<By> by) {
        return untilFound(by).isSelected();
    }

    public void setRadio(Supplier<By> by, Object value) {
        List<WebElement> radiobuttons = findElements(by.get());

        assert radiobuttons.size() >= 2;

        for (WebElement e : radiobuttons) {
            if (e.getAttribute("value").equals(value.toString())) {
                e.click();
                return;
            }
        }
        throw new IllegalArgumentException(
            "unable to find element with value " + value);
    }

    public String getRadio(By by) {
        List<WebElement> radiobuttons = findElements(by);

        assert radiobuttons.size() >= 2;

        for (WebElement e : radiobuttons) {
            if (Boolean.valueOf(e.getAttribute("checked"))) {
                return e.getAttribute("value");
            }
        }
        return null;
    }

    public Select getSelect(Supplier<By> by) {
        final Element element = untilFound(by);
        new WebDriverWait(this, 3, 100)
            .until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver driver) {
                    element.click();
                    return !element.findElements(By.tagName("option")).isEmpty();
                }
            });
        return new Select(element);
    }

    public void select(Supplier<By> by, Object select) {
        getSelect(by).selectByVisibleText(select.toString());
        try {
            if (!getSelect(by)
                .getFirstSelectedOption()
                .getText()
                .equals(select.toString())) {
                getSelect(by)
                    .getOptions()
                    .stream()
                    .filter(
                        (WebElement e) ->
                            e.getText().equals(select.toString()))
                    .findFirst()
                    .get()
                    .click();
            }
        } catch (Exception e) {
            //Don't need to handle it.
        }
    }

    public Select getSelectLambda(Supplier<By> by) {
        Element element = untilFound(by);
        new WebDriverWait(this, 3, 100)
            .until((WebDriver driver) -> {
                element.click();
                return !element.findElements(By.tagName("option")).isEmpty();
            });
        return new Select(element);
    }
}