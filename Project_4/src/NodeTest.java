import student.TestCase;

/**
 * Tests the methods in the Node Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-11-29
 */

public class NodeTest extends TestCase {

    private Node<String> testNode;
    
    /**
     * Sets up for the test method
     */
    public void setUp() {
        testNode = new Node<String>("Test");
    }
    
    /**
     * Tests the getItem() method
     */
    public void testGetItem() {
        assertTrue(testNode.getItem().equals("Test"));
    }
    
    /**
     * Tests the getPrev() method
     */
    public void testGetPrev() {
        Node<String> previous = new Node<String>("Prev"); 
        testNode.setPrev(previous);
        Node<String> testPrev = testNode.getPrev();
        assertTrue(testPrev.getItem().equals("Prev"));
    }
    
    /**
     * Tests the getNext() method
     */
    public void testGetNext() {
        Node<String> nextNode = new Node<String>("Next"); 
        testNode.setNext(nextNode);
        Node<String> testNext = testNode.getNext();
        assertTrue(testNext.getItem().equals("Next"));
    }
    
    /**
     * Tests the setItem() method
     */
    public void testsetItem() {
        testNode.setItem("New Item");
        assertTrue(testNode.getItem().equals("New Item"));
    }
    
    /**
     * Tests the setPrev() method
     */
    public void testSetPrev() {
        Node<String> previous = new Node<String>("Prev"); 
        testNode.setPrev(previous);
        Node<String> testPrev = testNode.getPrev();
        assertTrue(testPrev.getItem().equals("Prev"));
    }
    
    /**
     * Tests the setNext() method
     */
    public void testSetNext() {
        Node<String> nextNode = new Node<String>("Next"); 
        testNode.setNext(nextNode);
        Node<String> testNext = testNode.getNext();
        assertTrue(testNext.getItem().equals("Next"));
    }
}
