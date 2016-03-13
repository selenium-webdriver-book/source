package swip.ch13framework.v6_nonjava8;

import org.openqa.selenium.By;
import com.google.common.base.Optional;

public interface SearchScope {

    Element findElement(By by);

    Optional<Element> optionalElement(By by) ;
}
