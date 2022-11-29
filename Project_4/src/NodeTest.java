import junit.framework.TestCase;

public class NodeTest extends TestCase {

    private Node testNode;
    
    /**
     * Sets up for the test method
     */
    public void setUp() {
        testNode = new Node("Test");
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
        Node previous = new Node("Prev"); 
        testNode.setPrev(previous);
        Node testPrev = testNode.getPrev();
        assertTrue(testPrev.getItem().equals("Prev"));
    }
    
    /**
     * Tests the getNext() method
     */
    public void testGetNext() {
        Node nextNode = new Node("Next"); 
        testNode.setNext(nextNode);
        Node testNext = testNode.getNext();
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
        Node previous = new Node("Prev"); 
        testNode.setPrev(previous);
        Node testPrev = testNode.getPrev();
        assertTrue(testPrev.getItem().equals("Prev"));
    }
    
    /**
     * Tests the setNext() method
     */
    public void testSetNext() {
        Node nextNode = new Node("Next"); 
        testNode.setNext(nextNode);
        Node testNext = testNode.getNext();
        assertTrue(testNext.getItem().equals("Next"));
    }
}













