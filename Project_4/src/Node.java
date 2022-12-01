
public class Node<T> {

    T item;
    Node<T> prev;
    Node<T> next;

    public Node(T thing) {
        item = thing;
        prev = null;
        next = null;
    }


    public Object getItem() {
        return item;
    }


	public Node<T> getPrev() {
        return prev;
    }


    public Node<T> getNext() {
        return next;
    }


    public void setItem(T r) {
        item = r;
    }


    public void setPrev(Node<T> n) {
        prev = n;
    }


    public void setNext(Node<T> n) {
        next = n;
    }

}
