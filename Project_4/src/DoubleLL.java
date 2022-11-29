
public class DoubleLL {

    private Node head;
    private Node tail;
    private int size;

    /**
     * Constructor for the DoubleLL
     */
    public DoubleLL() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a node to the end of the DoubleLL
     * 
     * @param n
     *       The node being added to the DoubleLL
     */
    public void append(Node n) {
        // DLL is empty
    	if (head == null) {
            head = n;
            tail = n;
            head.prev = null;
            tail.next = null;
        }
    	// DLL is not empty
        else {
        	Node oldTail = tail;
            tail.setNext(n); ;
            // n.prev = tail;
            tail = n;
            tail.setPrev(oldTail);
            tail.next = null;
        }
    }

    /**
     * Removes a node from the DoubleLL with a specific item
     * 
     * @param str
     *        The item of the node to be removed
     */
    public void remove(String str) {
        Node target = head;
        // Loop through the DoubleLL to find the node
        // to remove
        if (head == null) {
        	System.out.println("DoubleLL is empty; there's nothing to remove");
        }
        while (!target.item.equals(str)) {
        	target = target.next;
        }
        // At this point, target is the node to remove
        
        // If head is the node to remove and it's
        // the only thing in the DoubleLL
        if (target == head && target.next == null) {
            head = null;
            tail = null;
        }
        // If head is the node to remove, but it's
        // not the only thing in the DoubleLL
        else if (target == head && target.next == null) {
            head.setNext(head.next);
        }
        // The node to find is in the middle of the array
        else if (target.next != null && target.prev != null) {
        	target.getPrev().setNext(target.next);
        }
        // Tail is the node to remove
        else if (target == tail) {
        	target.setPrev(tail);
        }
        size--;
    }
    
    /**
     * 
     */
    public void addToFront(Node newFront) {
        // DoubleLL is empty
    	if (head == null) {
        	append(newFront);
        }
    	// DoubleLL only has one element
        else if (head == tail) {
            Node oldFront = head;
            head = newFront;
            newFront.setNext(oldFront);
            tail = oldFront;
            oldFront.setPrev(newFront);
        }
    	// DoubleLL has more than one element
        else {
            Node oldFront = head;
            head = newFront;
            newFront.setNext(oldFront);
        }
    }

    /**
     * Prints out all the nodes in the DoubleLL
     */
    public void printAll() {
        if (head == null) {
        	System.out.println("No Elements in DoubleLL");
            return;
        }
        // DoubleLL is not empty
        Node current = head;
        System.out.println("Nodes in the DoubleLL:");
        while (current != null) {
        	System.out.println(current.item + " ");
        	current = current.next;
        }
        System.out.println("Total number of nodes in DoubleLL: " + size);
    }

}























