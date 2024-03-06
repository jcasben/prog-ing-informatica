import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Person [] persons = new Person[] {
                new Person("John", 30),
                new Person("Alice", 25),
                new Person("Bob", 55),
                new Person("John", 18),
                new Person("Paul", 20),
        };

        PriorityQueue<Person> heap = new PriorityQueue<>();
        for (Person person : persons) {
            heap.offer(person);
        }

        while (!heap.isEmpty()) {
            System.out.print(heap.poll().toString() + " ");
        }
    }

}
