# Task 4 - Custom annotations

In this task, we had to extend the functionality of a class using custom Java annotations that
will be of the type "RUNTIME", allowing us to use them during the program run using Java Reflection.
The objective was to create the following annotations:

- @PrettyPrintable: will allow the PrettyPrintProcessor to process this class. All fields with
no annotations will be printed with the following format: `field: value`.
- @NotPrettyPrintable: if a field is marked with this annotation, it won't be printed.
- @CustomPrettyPrint: if a field is marked with this annotation, it means that we want to print
it using a custom way. This annotation has a "type" field that will allow us to identify
the corresponding that the user has defined to print this field.
- @HowToPrettyPrint: if a method is marked with this annotation, it means that it has been defined
to print a field in a custom way. This annotation has a "type" field that we will use to
identify which fields have to be printed using that method.