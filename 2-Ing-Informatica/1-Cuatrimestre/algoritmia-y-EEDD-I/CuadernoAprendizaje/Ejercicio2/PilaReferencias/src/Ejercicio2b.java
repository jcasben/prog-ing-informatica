public class Ejercicio2b {
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>();
        pila.push(10);
        pila.push(20);
        try {
            System.out.println(pila.top());
            pila.pop();
            System.out.println(pila.top());
        } catch (Pila.EmptyStackException e) {
            System.err.println(e.getMessage());
        }
    }
}
