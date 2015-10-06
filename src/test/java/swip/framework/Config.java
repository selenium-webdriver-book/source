package swip.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated This class is now no longer used, is only being kept as I'd like to delete in a single commit.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Deprecated
public @interface Config {
    String[] exclude() default "";
}