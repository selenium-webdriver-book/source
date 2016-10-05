package swb.ch02locatingelements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AnyBy extends By {
    private final By[] bys;

    private AnyBy(By... bys) {
        this.bys = bys;
    }

    public static By any(By... bys) {
        return new AnyBy(bys);
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        List<WebElement> elements = new ArrayList<>();
        for (By by : bys) {
            elements.addAll(context.findElements(by)); // # add all the elements we find
        }
        return elements;
    }
}
