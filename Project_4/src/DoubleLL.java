/**
 * Handles the method of the DoubleLL
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-02
 * @param <T>
 *            The generic item the nodes carry
 */
public class DoubleLL<T> {

    /**
     * The head node in the DoubleLL
     */
    private Node<T> head;
    /**
     * The tail node in the DoubleLL
     */
    private Node<T> tail;

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
     * @param obj
     *            THe object being inserted into the DoubleLL
     */
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
        }
        else {
            Node<T> oldLast = tail.getPrev();
            oldLast.setNext(addNode);
            addNode.setPrev(oldLast);
            addNode.setNext(tail);
            tail.setPrev(addNode);

// Node newNext = head.getNext().getNext();
// head.setNext(addNode);
// addNode.setPrev(head);
// addNode.setNext(newNext);
// newNext.setPrev(addNode);
        }
        size++;
    }


    /**
     * Inserts an object into a specific position
     * 
     * @param obj
     *            The object to insert into the DoubleLL
     * @param ind
     *            The location where the object will
     *            be placed
     */
    public void insert(T obj, int ind) {
        if (obj == null || ind < 0 || ind > size) {
            return;
        }
        Node<T> addNode = new Node<T>(obj);
        if (size == 0) {
            head.setNext(addNode);
            addNode.setPrev(head);
            tail.setPrev(addNode);
            addNode.setNext(tail);
        }
        else {
            Node<T> curr = head.getNext();
            for (int i = 0; i < ind; i++) {
                curr = curr.getNext();
            }
            curr.getPrev().setNext(addNode);
            addNode.setPrev(curr.getPrev());
            addNode.setNext(curr);
            curr.setPrev(addNode);
        }
        size++;
    }


    /**
     * Deletes the object
     * 
     * @param obj
     *            The object to be removed
     *            from the DoubleLL
     */
    @SuppressWarnings("unchecked")
    public void delete(T obj) {
        if (obj == null || size == 0) {
            return;
        }
        Node<T> current = null;
        for (int i = 0; i < size; i++) {
            Node<T> nd = this.getNode(i);
            if (nd.getItem() == obj) {
                current = nd;
                break;
            }
        }

        if (current == null) {
            return;
        }
        else {
            if (current.getPrev() == head) {
                Node<T> newFirst = current.getNext();
                head.setNext(newFirst);
                newFirst.setPrev(head);
            }
            else if (current.getNext() == tail) {
                Node<T> newLast = current.getPrev();
                newLast.setNext(tail);
                tail.setPrev(newLast);
            }
            else {
                Node<T> p = current.getPrev();
                Node<T> n = current.getNext();
                p.setNext(n);
                n.setPrev(p);
            }
            size--;
        }

// Node<T> current = head.getNext();
// while (current != null && current.getItem() != obj) {
// current = current.getNext();
// }
// if (current == null) {
// return;
// }
// current.getPrev().setNext(current.next);
// current.getNext().setPrev(current.getPrev());
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
        if (location >= size) {
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
     *
     * @param first
     *            to check if this is the first thing printed
     */
    public void print(boolean first) {
        if (!first) {
            System.out.print("\n");
        }
        if (size == 0) {
            System.out.print("No FreeBlocks:");
        }
        else {
            Node curr = head.getNext();
            for (int i = 0; i < size; i++) {
                System.out.print(curr.getItem().toString());
                if (i < size - 1) {
                    System.out.print(" -> ");
                }
                curr = curr.getNext();
            }
        }
    }

}
