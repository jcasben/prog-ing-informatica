public class Pila {
    private final int [] elements;
    private int top;

    public Pila(int length) {
        this.elements = new int[length];
        //Como iniciamos la cola vacia, lo indicaremos con un -1 en la posicion del top de la pila
        this.top = -1;
    }

    public void push(int element) throws FullStackException {
        //Si la posicion del top ya esta en el limite de elementos de la pila y realizamos una operacion de push,
        // salta una excepcion.
        if (top == elements.length - 1) {
            throw new FullStackException();
        }
        top++;
        elements[top] = element;
    }

    public void pop() throws EmptyStackException {
        //Si la pila esta vacia e intentamos realizar una operacion de pop, nos salta una excepcion.
        if (top == -1) {
            throw new EmptyStackException();
        }
        top--;
    }

    public int top() throws EmptyStackException {
        //Si la cola esta vacia e intentamos consultar el elemento que esta en el top, salta una excepcion
        if (top == -1) {
            throw new EmptyStackException();
        }
        return elements[top];
    }

    private static class EmptyStackException extends Exception {
        public EmptyStackException() {
            super("La cola aún no contiene elementos.");
        }
    }

    private static class FullStackException extends Exception {
        public FullStackException() {
            super("La cola ha llegado al máximo de su capacidad.");
        }
    }
}
