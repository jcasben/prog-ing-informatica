public class Pila {
    private final int [] elements;
    private int top;

    public Pila(int length) {
        this.elements = new int[length];
        this.top = -1;
    }

    public void push(int element) throws FullStackException {
        if (top == elements.length - 1) {
            throw new FullStackException();
        }
        top++;
        elements[top] = element;
    }

    public void pop() throws EmptyStackException {
        if (top == -1) {
            throw new EmptyStackException();
        }
        top--;
    }

    public int top() throws EmptyStackException {
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
