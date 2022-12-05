import student.TestCase;

/**
 * Tests the methods in the DoubleLL Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-01
 */

public class DoubleLLTest extends TestCase {

    private DoubleLL<Integer> dll;

    /**
     * Sets up the DoubleLL for the test methods
     */
    public void setUp() {
    	dll = new DoubleLL<Integer>();
    }
    
    /**
     * Tests the insert() method
     */
    public void testInsert() {
    	assertEquals(dll.getSize(), 0);
        dll.insert(1);
        assertEquals(dll.getSize(), 1);
        dll.insert(2);
        assertEquals(dll.getSize(), 2);
    }
    
//    /**
//     * Tests the update() method
//     */
//    public void testUpdate() {
//        dll.insert(1);
//        assertEquals(dll.getSize(), 1);
//        dll.insert(0);
//        assertEquals(dll.getSize(), 2);
//        dll.update();
//        assertEquals(dll.getSize(), 1);
//    }
    
    /**
     * Tests the delete() method
     */
    public void testDelete() {
        dll.insert(1);
        dll.insert(2);
        dll.insert(3);
        dll.insert(4);
        dll.insert(5);
        
        dll.delete(-1);
        assertEquals(dll.getSize(), 4);
        dll.delete(6);
        assertEquals(dll.getSize(), 4);
        
        dll.delete(1);
        assertEquals(dll.getSize(), 3);
        dll.delete(5);
        assertEquals(dll.getSize(), 2);
        dll.delete(3);
        assertEquals(dll.getSize(), 1);
    }
    
    /**
     * Tests the getSize() size
     */
    public void testGetSize() {
    	assertEquals(dll.getSize(), 0);
        dll.insert(1);
        assertEquals(dll.getSize(), 1);
        dll.insert(2);
        assertEquals(dll.getSize(), 2);
    }
    
    /**
     * Tests the setSize() size
     */
    public void testSetSize() {
    	assertEquals(dll.getSize(), 0);
        dll.setSize(10);
        assertEquals(dll.getSize(), 10);
    }
    
}
