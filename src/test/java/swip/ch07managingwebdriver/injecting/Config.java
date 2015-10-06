package swip.ch07managingwebdriver.injecting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // # make sure we can look for this at runtime
@Target(ElementType.TYPE) // # make sure we can only apply this to a type
public @interface Config {
    /**
     * @return Capabilities to exclude, e.g "browserName=safari"
     */
    String[] exclude() default "";
}