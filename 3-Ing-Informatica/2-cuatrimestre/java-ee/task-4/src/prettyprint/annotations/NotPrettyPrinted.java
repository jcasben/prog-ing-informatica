package prettyprint.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks this field to be ignored by the {@link prettyprint.PrettyPrintProcessor}.
 *
 * @author jcasben
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotPrettyPrinted {
}
