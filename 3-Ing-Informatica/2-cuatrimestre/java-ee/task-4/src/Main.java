import prettyprint.PrettyPrintProcessor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrettyPrintProcessor processor = new PrettyPrintProcessor();
        List<Student> list = Student.exampleData();
        for (Student student : list) {
            processor.processPrettyPrint(student);
        }
    }
}
