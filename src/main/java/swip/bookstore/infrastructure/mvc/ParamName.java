package swip.bookstore.infrastructure.mvc;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamName {
    String value();
}