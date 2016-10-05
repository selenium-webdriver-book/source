package swb.ch02locatingelements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllBy extends By {

    private final By[] bys;

    private AllBy(By... bys) {
        this.bys = bys;
    }

    public static AllBy all(By... bys) { // # static factory method
        return new AllBy(bys);
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        List<WebElement> elements = null;
        for (By by : bys) {
            List<WebElement> newElements = context.findElements(by);
            if (elements == null) {
                elements = newElements; // # if we have the first set, initialize the list
            } else {
                elements.retainAll(newElements); // # otherwise, keep only the new elements
            }
        }
        return elements;
    }
}
