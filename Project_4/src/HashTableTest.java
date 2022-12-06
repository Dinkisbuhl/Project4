import student.TestCase;

/**
 * Tests the methods in the HashTable Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-01
 */

public class HashTableTest extends TestCase {

    /**
     * The Hashtable to be tested here
     */
    HashTable hT;
    /**
     * The Handle to be tested here
     */
    Handle basicHandle;

    /**
     * Sets up the HashTable for the
     * test methods
     */
    public void setUp() {
        hT = new HashTable(10);
        basicHandle = new Handle(0);
    }

    /**
     * Tests the sFold method under normal
     * circumstances
     */
    public void testSfoldHash() {
        hT = new HashTable(10);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "idk";
        String str4 = "keep it up";
        String str5 = "sigh";
        assertEquals(4, hT.sFoldHash(str1));
        assertEquals(3, hT.sFoldHash(str2));
        assertEquals(7, hT.sFoldHash(str3));
        assertEquals(0, hT.sFoldHash(str4));
        assertEquals(7, hT.sFoldHash(str5));
    }

    /**
     * Tests the sFold method for a filled up HashTable
     */
    public void testSfoldHash2() {
        hT = new HashTable(5);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "idk";
        String str4 = "keep it up";
        String str5 = "sigh";
        String str6 = "e";
        assertEquals(4, hT.sFoldHash(str1));
        assertEquals(3, hT.sFoldHash(str2));
        assertEquals(2, hT.sFoldHash(str3));
        assertEquals(0, hT.sFoldHash(str4));
        assertEquals(2, hT.sFoldHash(str5));
        assertEquals(1, hT.sFoldHash(str6));
    }

    /**
     * Tests the insert method under normal
     * circumstances
     */
    public void testInsert() {
        hT = new HashTable(10);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "";
        hT.hashInsert(str1, basicHandle);
        assertFalse(hT.hashSearch(str2));
        assertTrue(hT.hashSearch(str1));
        hT.hashInsert(str2, basicHandle);
        assertTrue(hT.hashSearch(str2));
    }

    /**
     * Tests the insert method and checking
     * their size
     */
    public void testInsert2() {
        hT = new HashTable(10);
        String str1 = "idk";
        String str2 = "sigh";
        hT.hashInsert(str1, basicHandle);
        assertFalse(hT.hashSearch(str2));
        assertTrue(hT.hashSearch(str1));
        hT.hashInsert(str2, basicHandle);
        assertTrue(hT.hashSearch(str2));
        assertEquals(7, hT.sFoldHash(str1));
        assertEquals(7, hT.sFoldHash(str2));
    }

    /**
     * Tests the extendTable() method
     */
    public void testExtendTable() {
        hT = new HashTable(5);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "idk";
        String str4 = "keep it up";
        hT.hashInsert(str1, basicHandle);
        assertTrue(hT.hashSearch(str1));
        hT.hashInsert(str2, basicHandle);
        assertTrue(hT.hashSearch(str2));
        hT.hashInsert(str3, basicHandle);
        assertTrue(hT.hashSearch(str3));
        assertEquals(hT.getTableSize(), 5);

        hT.hashInsert(str4, basicHandle);
        assertTrue(hT.hashSearch(str4));
        assertEquals(hT.getTableSize(), 10);
    }

    /**
     * Tests the remove() method under 
     * normal circumstances
     */
    public void testRemove() {
        hT = new HashTable(5);
        String str1 = "something";
        hT.hashInsert(str1, basicHandle);
        assertTrue(hT.hashSearch(str1));
        assertTrue(hT.hashRemove(str1));
        assertFalse(hT.hashSearch(str1));
    }

    /**
     * Tests the remove method when filling
     * the table 
     */
    public void testRemove2() {
        hT = new HashTable(5);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "idk";
        String str4 = "keep it up";
        String str5 = "sigh";
        String str6 = "e";
        hT.hashInsert(str1, basicHandle);
        assertTrue(hT.hashSearch(str1));
        assertTrue(hT.hashRemove(str1));
        assertFalse(hT.hashSearch(str1));

        hT.hashInsert(str2, basicHandle);
        assertTrue(hT.hashSearch(str2));
        hT.hashInsert(str3, basicHandle);
        assertTrue(hT.hashSearch(str3));
        hT.hashInsert(str4, basicHandle);
        assertTrue(hT.hashSearch(str4));
        hT.hashInsert(str6, basicHandle);
        assertTrue(hT.hashSearch(str6));

        assertEquals(hT.getTableSize(), 10);
        assertFalse(hT.hashSearch(str1));

        hT.hashInsert(str5, basicHandle);
        assertTrue(hT.hashSearch(str5));
        assertTrue(hT.hashRemove(str3));
        assertFalse(hT.hashSearch(str3));
        assertTrue(hT.hashSearch(str5));
        assertTrue(hT.hashRemove(str5));
        assertFalse(hT.hashSearch(str5));
    }
}
