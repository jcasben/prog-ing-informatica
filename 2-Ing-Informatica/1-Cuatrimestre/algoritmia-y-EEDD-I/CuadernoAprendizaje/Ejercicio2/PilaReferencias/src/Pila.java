public class Pila<E> {
    private Node<E> top;

    public Pila() {
        top = null;
    }

    public void push(E elemento) {
        Node<E> tmp = new Node<>(elemento);
        tmp.setNext(top);
        top = tmp;
    }

    public void pop() {
        top = top.getNext();
    }

    public E top() throws EmptyStackException {
        if (top == null) throw new EmptyStackException();
        return top.getElement();
    }

    public static class EmptyStackException extends Exception {
        public EmptyStackException() {
            super("La cola aún no contiene elementos.");
        }
    }
}
