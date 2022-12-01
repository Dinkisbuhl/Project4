import student.TestCase;
public class HashTableTest extends TestCase {
    
    HashTable hT;
    /**
     * Sets up the Node for the
     * test methods
     */
    public void setUp() {
        hT = new HashTable(10);
    }
    
    public void testSfoldHash() {
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
    
    public void testInsert() {
        hT = new HashTable(10);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "";
        hT.hashInsert(str1, str1);
        assertFalse(hT.hashSearch(str2, str2));
        assertTrue(hT.hashSearch(str1, str1));
        assertTrue(hT.hashSearch(str3, str3));
        hT.hashInsert(str2, str2);
        assertTrue(hT.hashSearch(str2, str2));
    }
    
    public void testInsert2() {
        hT = new HashTable(10);
        String str1 = "idk";
        String str2 = "sigh";
        String str3 = "";
        hT.hashInsert(str1, str1);
        assertFalse(hT.hashSearch(str2, str2));
        assertTrue(hT.hashSearch(str1, str1));
        assertTrue(hT.hashSearch(str3, str3));
        hT.hashInsert(str2, str2);
        assertTrue(hT.hashSearch(str2, str2));
        assertEquals(7, hT.sFoldHash(str1));
        assertEquals(7, hT.sFoldHash(str2));
    }
    
    public void testExtendTable() {
        hT = new HashTable(5);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "idk";
        String str4 = "keep it up";
        hT.hashInsert(str1, str1);
        assertTrue(hT.hashSearch(str1, str1));
        hT.hashInsert(str2, str2);
        assertTrue(hT.hashSearch(str2, str2));
        hT.hashInsert(str3, str3);
        assertTrue(hT.hashSearch(str3, str3));
        assertEquals(hT.getTableSize(), 5);
        
        hT.hashInsert(str4, str4);
        assertTrue(hT.hashSearch(str4, str4));
        assertEquals(hT.getTableSize(), 10);
    }
}
