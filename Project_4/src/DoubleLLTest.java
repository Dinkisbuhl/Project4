import junit.framework.TestCase;

public class DoubleLLTest extends TestCase {

    DoubleLL<Integer> dll;

    /**
     * 
     */
    public void setUp() {
    	dll = new DoubleLL<Integer>();
    }
    
    /**
     * 
     */
    public void testInsert() {
    	assertEquals(dll.getSize(), 0);
        dll.insert(1);
        assertEquals(dll.getSize(), 1);
        dll.insert(2);
        assertEquals(dll.getSize(), 2);
    }
    
    /**
     * 
     */
    public void testUpdate() {
        dll.insert(1);
        assertEquals(dll.getSize(), 1);
        dll.insert(0);
        assertEquals(dll.getSize(), 2);
        dll.update();
        assertEquals(dll.getSize(), 1);
    }
    
    /**
     * 
     */
    public void testDelete() {
        dll.insert(1);
        dll.insert(2);
        dll.insert(3);
        dll.insert(4);
        
        dll.delete(-1);
        assertEquals(dll.getSize(), 4);
        dll.delete(5);
        assertEquals(dll.getSize(), 4);
        
        dll.delete(0);
        assertEquals(dll.getSize(), 3);
        dll.delete(2);
        assertEquals(dll.getSize(), 2);
        dll.delete(1);
        assertEquals(dll.getSize(), 1);
    }
    
    /**
     * 
     */
    public void testSize() {
    	assertEquals(dll.getSize(), 0);
        dll.insert(1);
        assertEquals(dll.getSize(), 1);
        dll.insert(2);
        assertEquals(dll.getSize(), 2);
    }
    
}






















