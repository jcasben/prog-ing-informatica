package prettyprint.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks this class as viable to be processed by {@link prettyprint.PrettyPrintProcessor}.
 *
 * @author jcasben
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PrettyPrintable {
}
