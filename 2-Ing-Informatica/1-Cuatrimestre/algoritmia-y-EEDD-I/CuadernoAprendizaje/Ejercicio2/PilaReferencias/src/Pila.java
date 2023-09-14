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

    public E top() {
        return top.getElement();
    }
}
