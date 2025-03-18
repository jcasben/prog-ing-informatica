package prettyprint;

/**
 * Custom exception for {@link PrettyPrintProcessor}.
 *
 * @author jcasben
 */
public class PrettyPrintableException extends RuntimeException {
    public PrettyPrintableException(String message) {
        super(message);
    }
}
