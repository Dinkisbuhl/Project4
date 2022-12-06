import student.TestCase;
import student.testingsupport.PrintStreamWithHistory;


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
        dll.insert(null);
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
        assertEquals(dll.getSize(), 0);
        dll.delete(null);
        assertEquals(dll.getSize(), 0);
        
        dll.insert(1);
        dll.insert(2);
        dll.insert(3);
        dll.insert(4);
        dll.insert(5);
        
        dll.delete(-1);
        assertEquals(dll.getSize(), 5);
        dll.delete(6);
        assertEquals(dll.getSize(), 5);
        dll.delete(null);
        assertEquals(dll.getSize(), 5);
        
        dll.delete(1);
        assertEquals(dll.getSize(), 4);
        
        // Errors are down here
        dll.delete(5);
        assertEquals(dll.getSize(), 3);
        dll.delete(3);
        assertEquals(dll.getSize(), 2);
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
     * Tests the setSize() method
     */
    public void testSetSize() {
        assertEquals(dll.getSize(), 0);
        dll.setSize(10);
        assertEquals(dll.getSize(), 10);
    }
    
    /**
     * Tests the getNode() method
     */
    @SuppressWarnings("unchecked")
	public void testGetNode() {
        assertEquals(dll.getSize(), 0);
        Node<Integer> n1 = dll.getNode(1);
        assertNull(n1);
        dll.insert(0);
        assertEquals(dll.getSize(), 1);
        Node<Integer> n2 = dll.getNode(1);
        assertNull(n2);
        
        Node<Integer> nd = dll.getNode(0);
        assertEquals(nd.getItem(), 0);
        
        dll.insert(1);
        assertEquals(dll.getSize(), 2);
        dll.insert(2);
        assertEquals(dll.getSize(), 3);

        Node<Integer> nd1 = dll.getNode(1);
        assertEquals(nd1.getItem(), 1);

        Node<Integer> nd2 = dll.getNode(2);
        assertEquals(nd2.getItem(), 2);
    }
    
    /**
     * Tests the print() method
     */
    public void testPrint() {

        PrintStreamWithHistory sysout = systemOut();
        dll.print();
        dll.insert(1);
        dll.print();
        dll.insert(2);
        dll.print();
        dll.insert(3);
        dll.print();
        String history = sysout.getHistory();
        String outComp = "No FreeBlocks:\r\n" +
            "FreeBlock List:\r\n" +
        	"1\r\n" + "Total FreeBlocks: 1\r\n" +	
            "FreeBlock List:\r\n" +
        	"1\r\n" + "2\r\n" + "Total FreeBlocks: 2\r\n" +	
        	"FreeBlock List:\r\n" +
        	"1\r\n" + "2\r\n" + "3\r\n" + 
        	"Total FreeBlocks: 3\r\n";
        assertEquals(history, outComp);
        
        
//        sysout = systemOut();
//        dll.insert(1);
//        dll.print();
//        history = sysout.getHistory();
//        outComp = "FreeBlock List:\r\n" +
//            "1\r\n" + "Total FreeBlocks: 1";
//        assertEquals(history, outComp);
//        
//        
//        sysout = systemOut();
//        dll.insert(2);
//        dll.print();
//        history = sysout.getHistory();
//        outComp = "FreeBlock List:\r\n" +
//                "1\r\n" + "2\r\n" +
//                "Total FreeBlocks: 2";
//        assertEquals(history, outComp);
//        
//        
//        sysout = systemOut();
//        dll.insert(3);
//        dll.print();
//        history = sysout.getHistory();
//        outComp = "FreeBlock List:\r\n" +
//                "1\r\n" + "2\r\n" + "3\r\n" +
//                "Total FreeBlocks: 3";
//        assertEquals(history, outComp);
        
    }
    
    
    
    
    
    
    
    
    
    
    
}






















