/**
 * Handles the methods in the Node Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-11-29
 * @param <T>
 *            The generic item that node carries
 */
public class Node<T> {

    /**
     * The item of the Node
     */
    private T item;
    /**
     * The node behind the current node
     */
    private Node<T> prev;
    /**
     * The node in front of the current node
     */
    private Node<T> next;

    /**
     * Constructor
     * 
     * @param thing
     *            The item of the Node
     */
    public Node(T thing) {
        item = thing;
        prev = null;
        next = null;
    }


    /**
     * Returns the item in the Node
     * 
     * @return
     *         The item of the node
     */
    public Object getItem() {
        return item;
    }


    /**
     * The node behind the current
     * node
     * 
     * @return
     *         The previous of the node of
     *         the node
     */
    public Node<T> getPrev() {
        return prev;
    }


    /**
     * The node in front the
     * current node
     * 
     * @return
     *         The next node of the node
     *         of the node
     */
    public Node<T> getNext() {
        return next;
    }


    /**
     * Sets the item of the node into
     * another node
     * 
     * @param r
     *            The item that the node
     *            will not contain
     */
    public void setItem(T r) {
        item = r;
    }


    /**
     * Sets the previous node of the current
     * node equal to the previous node
     * 
     * @param n
     *            The node that will be the new
     *            previous node of the current
     *            node
     */
    public void setPrev(Node<T> n) {
        prev = n;
    }


    /**
     * Sets the next node of the current
     * node equal to the next node
     * 
     * @param n
     *            The node that will be the new
     *            next node of the current
     *            node
     */
    public void setNext(Node<T> n) {
        next = n;
    }

}
