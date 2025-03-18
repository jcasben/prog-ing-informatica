package prettyprint.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines that the field marked with this annotation will be printed using a method with
 * the same type number.
 *
 * @author jcasben
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomPrettyPrint {
    int type();
}
