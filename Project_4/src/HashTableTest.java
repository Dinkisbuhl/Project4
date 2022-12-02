import student.TestCase;
public class HashTableTest extends TestCase {
    
    TempTable hT;
    /**
     * Sets up the Node for the
     * test methods
     */
    public void setUp() {
        hT = new TempTable(10);
    }
    
    public void testSfoldHash() {
        hT = new TempTable(10);
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
    
    public void testSfoldHash2() {
        hT = new TempTable(5);
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
    
    public void testInsert() {
        hT = new TempTable(10);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "";
        hT.hashInsert(str1, str1);
        assertFalse(hT.hashSearch(str2));
        assertTrue(hT.hashSearch(str1));
        hT.hashInsert(str2, str2);
        assertTrue(hT.hashSearch(str2));
    }
    
    public void testInsert2() {
        hT = new TempTable(10);
        String str1 = "idk";
        String str2 = "sigh";
        hT.hashInsert(str1, str1);
        assertFalse(hT.hashSearch(str2));
        assertTrue(hT.hashSearch(str1));
        hT.hashInsert(str2, str2);
        assertTrue(hT.hashSearch(str2));
        assertEquals(7, hT.sFoldHash(str1));
        assertEquals(7, hT.sFoldHash(str2));
    }
    
    public void testExtendTable() {
        hT = new TempTable(5);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "idk";
        String str4 = "keep it up";
        hT.hashInsert(str1, str1);
        assertTrue(hT.hashSearch(str1));
        hT.hashInsert(str2, str2);
        assertTrue(hT.hashSearch(str2));
        hT.hashInsert(str3, str3);
        assertTrue(hT.hashSearch(str3));
        assertEquals(hT.getTableSize(), 5);
        
        hT.hashInsert(str4, str4);
        assertTrue(hT.hashSearch(str4));
        assertEquals(hT.getTableSize(), 10);
    }
      
    public void testRemove() {
        hT = new TempTable(5);
        String str1 = "something";
        hT.hashInsert(str1, str1);
        assertTrue(hT.hashSearch(str1));
        assertTrue(hT.hashRemove(str1));
        assertFalse(hT.hashSearch(str1));      
    }
    
    public void testRemove2() {
        hT = new TempTable(5);
        String str1 = "something";
        String str2 = "thing";
        String str3 = "idk";
        String str4 = "keep it up";
        String str5 = "sigh";
        String str6 = "e";
        hT.hashInsert(str1, str1);
        assertTrue(hT.hashSearch(str1));  
        assertTrue(hT.hashRemove(str1));
        assertFalse(hT.hashSearch(str1));   
        
        hT.hashInsert(str2, str2);
        assertTrue(hT.hashSearch(str2));  
        hT.hashInsert(str3, str3);
        assertTrue(hT.hashSearch(str3));  
        hT.hashInsert(str4, str4);
        assertTrue(hT.hashSearch(str4));  
        hT.hashInsert(str6, str6);
        assertTrue(hT.hashSearch(str6));  
        
        assertEquals(hT.getTableSize(), 10);
        assertFalse(hT.hashSearch(str1));   
        
        hT.hashInsert(str5, str5);
        assertTrue(hT.hashSearch(str5)); 
        assertTrue(hT.hashRemove(str3));
        assertFalse(hT.hashSearch(str3));  
        assertTrue(hT.hashSearch(str5)); 
        assertTrue(hT.hashRemove(str5));
        assertFalse(hT.hashSearch(str5)); 
    }
}
