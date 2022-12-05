import junit.framework.TestCase;

public class MemPoolTest extends TestCase {

    private MemPool testMP;
    DoubleLL<FreeBlock> dll;
    
    /**
     * 
     */
    public void setUp() {
        byte[] testB = new byte[10];
        dll = new DoubleLL<FreeBlock>();
        testMP = new MemPool(testB, dll);
    }
    
    /**
     * Tests the constructor  
     */
    public void testConstructor() {
        assertEquals(testMP.getByteArr().length, 10);
        assertEquals(dll.getSize(), 1);
    }
    
    /**
     * Tests the getByteArr() method
     */
    public void testGetByteArr() {
    	assertEquals(testMP.getByteArr().length, 10);
    }
    
    /**
     * Tests the getFreeList() method
     */
    public void testGetFreeList() {
    	assertEquals(testMP.getFreeList().getSize(), 1);
    }
    
    /**
     * Tests the getFreeList() method
     */
    public void testGetInitialSize() {
    	assertEquals(testMP.getInitialSize(), 10);
    }
    
    /**
     * Tests the insert() method
     */
    public void testInsert() {
        byte[] testBytes = new byte[1];
        DoubleLL<FreeBlock> dllist = new DoubleLL<FreeBlock>();
        MemPool tMP = new MemPool(testBytes, dllist);
        
        dllist.setSize(0);
        tMP.insert("First");
        assertEquals(tMP.getFreeList().getSize(), 1);
        
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 8);
    }
    
    /**
     * Tests the remove() method
     */
    public void testRemove() {
        testMP.insert("First");
        testMP.insert("Second");
        testMP.insert("Third");
        
        
    }
    
}


































