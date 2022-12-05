/**
 * Handles the method of the DoubleLL
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-02
 */

@SuppressWarnings("rawtypes")
public class DoubleLL<T> {

    /**
     * The head node in the DoubleLL
     */
    Node<T> head;
    /**
     * The tail node in the DoubleLL
     */
    Node<T> tail;

    private int size;

    /**
     * Constructor for the DoubleLL
     */
    public DoubleLL() {
        head = new Node<T>(null);
        tail = new Node<T>(null);
        size = 0;
    }


    /**
     * Inserts an object into the DoubleLL
     * 
     * @param pos
     *            Where the object is being inserted into
     * @param obj
     *            THe object being inserted into the DoubleLL
     */
    @SuppressWarnings("unchecked")
    public void insert(T obj) {
        if (obj == null) {
            return;
        }
        Node<T> addNode = new Node<T>(obj);
        if (size == 0) {
            head.setNext(addNode);
            addNode.setPrev(head);
            tail.setPrev(addNode);
            addNode.setNext(tail);
            System.out.println(size);
            size++;
        }
        else {
            Node newNext = head.getNext().getNext();
            head.setNext(addNode);
            addNode.setPrev(head);
            addNode.setNext(newNext);
            newNext.setPrev(addNode);
        }
        size++;
        System.out.println("here2");
        System.out.println(size);
    }


    /**
     * Deletes the object
     * 
     * @param obj
     *            The object to be removed
     *            from the DoubleLL
     */
    public void delete(T obj) {
        if (obj == null) {
            return;
        }
        if (head.getNext() != null) {
            Node<T> current = head.getNext();
            while (current.getItem() != obj) {
                current = current.getNext();
            }
            if (current == tail) {
                return;
            }
            current.getPrev().setNext(current.next);
            current.getNext().setPrev(current.getPrev());
        }
    }


    /**
     * Returns the size of the DoubleLL
     * 
     * @return int
     *         The size of the DoubleLL
     */
    public int getSize() {
        return size;
    }


    /**
     * Changes the size to a new value
     * ONLY FOR TESTING
     * 
     * @param s
     *            The new size
     */
    public void setSize(int s) {
        size = s;
    }

// /**
// * Updates the DoubleLL to get rid of
// * any nodes with value of 0
// */
// @SuppressWarnings("unchecked")
// public void update() {
// Node curr = head.next;
// while (curr != null) {
// if ((int)curr.getItem() == 0) {
// delete((T)curr.getItem());
// break;
// }
// curr = curr.getNext();
// }
// }


    /**
     * Gets the node
     * 
     * @param location
     *            The location we have
     * @return Node
     *         The node that exists at
     *         the location
     */
    public Node getNode(int location) {
        if (location > size) {
            return null;
        }
        Node current = head.getNext();
        for (int i = 0; i < location; i++) {
            current = current.getNext();
        }
        return current;
    }


    /**
     * Prints out all the nodes in the DoubleLL
     */
    public void print() {
        System.out.println("FreeBlock List:");
        Node curr = head.getNext();
        while (curr != null) {
            System.out.println(curr.getItem());
        }
    }

}
