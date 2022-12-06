import student.TestCase;

/**
 * Tests the methods in the MemPool Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-01
 */



public class MemPoolTest extends TestCase {

    private MemPool testMP;
    DoubleLL<FreeBlock> dll;
    
    /**
     * Sets the testMP for the methods 
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
        byte[] testBytes = new byte[100];
        DoubleLL<FreeBlock> dllist = new DoubleLL<FreeBlock>();
        MemPool tMP = new MemPool(testBytes, dllist);
        
        tMP.insert("a");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 3);
        
        byte[] theBytes = tMP.getByteArr();
        assertEquals(theBytes[0], 0);
        assertEquals(theBytes[1], 1);
        assertEquals(theBytes[2], 97);
        assertEquals(theBytes[3], 0);
        
        tMP.insert("something");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 14);
    }
    
    /**
     * Tests the insert() method
     */
    public void testInsertExpand() {
        byte[] testBytes = new byte[2];
        DoubleLL<FreeBlock> dllist = new DoubleLL<FreeBlock>();
        MemPool tMP = new MemPool(testBytes, dllist);
        
        tMP.insert("a");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 3);
        
        byte[] theBytes = tMP.getByteArr();
        assertEquals(theBytes[0], 0);
        assertEquals(theBytes[1], 1);
        assertEquals(theBytes[2], 97);
        assertEquals(theBytes[3], 0);
        
        tMP.insert("something");
        
        assertEquals(tMP.getFreeList().getSize(), 0);
        
        tMP.insert("zero");
        
        assertEquals(tMP.getFreeList().getSize(), 0);
        
        tMP.insert("zoros");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 27);
    }
    
    /**
     * Tests the remove() method
     */
    public void testRemove() {
        byte[] testBytes = new byte[3];
        DoubleLL<FreeBlock> dllist = new DoubleLL<FreeBlock>();
        MemPool tMP = new MemPool(testBytes, dllist);
        
        tMP.insert("a");
        
        assertEquals(tMP.getFreeList().getSize(), 0);
        
        byte[] theBytes = tMP.getByteArr();
        assertEquals(theBytes[0], 0);
        assertEquals(theBytes[1], 1);
        assertEquals(theBytes[2], 97);
        
        Handle testHandle = new Handle(0);
        
        tMP.remove(testHandle);
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        
        byte[] theBytes2 = tMP.getByteArr();
        assertEquals(theBytes2[0], 0);
        assertEquals(theBytes2[1], 0);
        assertEquals(theBytes2[2], 0);
    }
    
    /**
     * Tests the remove() method
     */
    public void testRemove2() {
        byte[] testBytes = new byte[100];
        DoubleLL<FreeBlock> dllist = new DoubleLL<FreeBlock>();
        MemPool tMP = new MemPool(testBytes, dllist);
        
        tMP.insert("a");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 3);
        
        byte[] theBytes = tMP.getByteArr();
        assertEquals(theBytes[0], 0);
        assertEquals(theBytes[1], 1);
        assertEquals(theBytes[2], 97);
        assertEquals(theBytes[3], 0);
        
        tMP.insert("something");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 14);
        
        Handle testHandle = new Handle(0);
        
        tMP.remove(testHandle);
        
        byte[] theBytes2 = tMP.getByteArr();
        assertEquals(theBytes2[0], 0);
        assertEquals(theBytes2[1], 0);
        assertEquals(theBytes2[2], 0);
        assertEquals(theBytes2[3], 0);
        assertEquals(theBytes2[4], 9);
        
        assertEquals(tMP.getFreeList().getSize(), 2);
    }
    
    /**
     * Tests the remove() method
     */
    public void testRemove3() {
        byte[] testBytes = new byte[2];
        DoubleLL<FreeBlock> dllist = new DoubleLL<FreeBlock>();
        MemPool tMP = new MemPool(testBytes, dllist);
        
        tMP.insert("a");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 3);
        
        byte[] theBytes = tMP.getByteArr();
        assertEquals(theBytes[0], 0);
        assertEquals(theBytes[1], 1);
        assertEquals(theBytes[2], 97);
        assertEquals(theBytes[3], 0);
        
        tMP.insert("something");
        
        assertEquals(tMP.getFreeList().getSize(), 0);
        
        tMP.insert("zero");
        
        assertEquals(tMP.getFreeList().getSize(), 0);
        
        tMP.insert("zoros");
        
        assertEquals(tMP.getFreeList().getSize(), 1);
        assertEquals(((FreeBlock) tMP.getFreeList().getNode(0).getItem()).getPosition(), 27);
    }
    
}
