public class Ejercicio2a {
    public static void main(String[] args) {
        Pila pila = new Pila(10);
        try {
            pila.push(10);
            pila.push(24);
            System.out.println(pila.top());
            pila.pop();
            System.out.println(pila.top());
        } catch (Pila.FullStackException e) {
            System.err.println(e.getMessage());
        } catch (Pila.EmptyStackException e) {
            throw new RuntimeException(e);
        }
    }
}
