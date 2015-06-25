package swip.ch06pageobjects.loadablepagefactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Verify {
    String INVALID_TITLE = "\0";

    String title() default INVALID_TITLE;
}
