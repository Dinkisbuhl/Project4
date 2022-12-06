import student.TestCase;

public class FreeBlockTest extends TestCase {

    private FreeBlock fbTest;
    
    /**
     * Sets up the FreeBlock for the
     * test methods
     */
    public void setUp() {
        fbTest = new FreeBlock(1, 2);
    }
    
    /**
     * Tests the getPosition() method
     */
    public void testGetPosition() {
        assertEquals(fbTest.getPosition(), 1);
    }
    
    /**
     * Tests the getSize() method
     */
    public void testGetSize() {
        assertEquals(fbTest.getSize(), 2);
    }
    
    /**
     * Tests the setPosition(int) method
     */
    public void testSetPosition() {
        fbTest.setPosition(3);
        assertEquals(fbTest.getPosition(), 3);
    }
    
    /**
     * Tests the setSize(int) method
     */
    public void testSetSize() {
        fbTest.setSize(4);
        assertEquals(fbTest.getSize(), 4);
    }

}
























