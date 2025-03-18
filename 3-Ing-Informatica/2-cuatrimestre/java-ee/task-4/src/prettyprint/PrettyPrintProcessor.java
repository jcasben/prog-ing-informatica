package prettyprint;

import prettyprint.annotations.CustomPrettyPrint;
import prettyprint.annotations.HowToPrettyPrint;
import prettyprint.annotations.NotPrettyPrinted;
import prettyprint.annotations.PrettyPrintable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Processes the {@link PrettyPrintable} annotations.
 *
 * @author jcasben
 */
public class PrettyPrintProcessor {
    /**
     * Takes an object, checks if it is correctly annotated with @PrettyPrintable and prints the object.
     *
     * @param object to be analyzed.
     * @throws PrettyPrintableException if the object is not annotated with @PrettyPrintable.
     */
    public void processPrettyPrint(Object object) throws PrettyPrintableException {
        try {
            checkIfPrettyPrintable(object);
            prettyPrintFields(object);
        } catch (Exception e) {
            throw new PrettyPrintableException(e.getMessage());
        }
    }

    /**
     * Checks if an object is annotated with @PrettyPrintable
     *
     * @param object to be checked
     */
    private void checkIfPrettyPrintable(Object object) {
        if (Objects.isNull(object)) {
            throw new PrettyPrintableException("The object to print is null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(PrettyPrintable.class)) {
            throw new PrettyPrintableException(
                    "The class " + clazz.getSimpleName() + " is not annotated with @PrettyPrintable"
            );
        }
    }

    /**
     * Processes the object's annotations and executes the necessary methods.
     *
     * @param object where we will search the annotations.
     * @throws Exception if it can't access one of the fields/methods.
     */
    private void prettyPrintFields(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<Integer, Method> methods = new HashMap<>();
        for (Method method : clazz.getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(HowToPrettyPrint.class)) {
                HowToPrettyPrint htpp = method.getAnnotation(HowToPrettyPrint.class);
                methods.put(htpp.type(), method);
            }
        }
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(CustomPrettyPrint.class)) {
                CustomPrettyPrint cpp = field.getAnnotation(CustomPrettyPrint.class);
                Method m = methods.get(cpp.type());
                Object value = field.get(object);

                System.out.println(">>>>>>> " + field.getName());
                m.invoke(object, value);
                System.out.println("<<<<<<< " + field.getName());
            } else if (!field.isAnnotationPresent(NotPrettyPrinted.class)) {
                Object value = field.get(object);
                System.out.println(field.getName() + ": " + value.toString());
            }
        }
    }
}
