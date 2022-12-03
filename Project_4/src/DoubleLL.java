
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
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Inserts an object into the DoubleLL
     * 
     * @param pos
     *       Where the object is being inserted into
     * @param obj
     *       THe object being inserted into the DoubleLL
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
  		    return;
  	    }
  	    else {
  	        Node newNext = head.getNext().getNext();
  	    	head.setNext(addNode);
  	        addNode.setPrev(head);
  	        addNode.setNext(newNext);
  	        newNext.setPrev(addNode);
  	    }
        size++;
    	
    }
    
    /**
     * Updates the DoubleLL to get rid of 
     * any nodes with value of 0
     * 
     * @param pos
     *       
     * @param obj
     *       
     */
    public void update() {
        Node curr = head.next;
        int count = 0;
        while (curr != null) {
            if ((int)curr.getItem() == 0) {
                delete(count);
            }
            count++;
            curr = curr.getNext();
        }
    }

    /**
     * Deletes the object at the pos
     * 
     * @param pos
     *       The position that is having
     *       their object removed
     */
    public void delete(int pos) {
        if (pos < 0 || pos >= size) {
            return;
        }
        Node<T> current = head.getNext();
        for (int i = 0; i < pos; i++) {
            current = current.getNext();
        }
        if (current == tail) {
            return;
        }
        current.getPrev().setNext(current.next);
        current.getNext().setPrev(current.getPrev());
    }
    
    
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
     * Returns the size of the DoubleLL
     * 
     * @return int
     *        The size of the DoubleLL
     */
    public int getSize() {
        return size;
    }
    
//    /**
//     * Prints out all the nodes in the DoubleLL
//     */
//    public void printAll() {
//        System.out.println("FreeBlock List:");
//        Node curr = head.getNext();
//        while (curr != null) {
//            System.out.println(curr.getItem());
//        }
//    }
    

}























