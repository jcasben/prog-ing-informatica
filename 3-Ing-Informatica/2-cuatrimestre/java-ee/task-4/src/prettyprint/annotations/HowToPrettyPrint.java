package prettyprint.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines one of the methods that will be used to print the field marked with
 * the same type number.
 *
 * @author jcasben
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HowToPrettyPrint {
    int type();
}
