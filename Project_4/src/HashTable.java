/**
 * Handles the methods in the HashTable Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-01
 */

public class HashTable {

    private int tableSize;
    private int fullness;
    private String name;
    private HashRecord[] hT;
    private String EMPTYKEY = "";

    /**
     * Constructor
     * 
     * @param s
     *       The size of the HashTable
     */
    public HashTable(int s, String n) {
        tableSize = s;
        name = n;
        hT = new HashRecord[tableSize];
        fullness = 0;

        Handle emptyHandle = new Handle(-1);
        
        for (int i = 0; i < tableSize; i++) {
            hT[i] = new HashRecord("", emptyHandle);
        }
    }


    /**
     * Insert something into a HashTable
     * 
     * @param K
     *       The length of the record
     * @param h
     *       The record
     */
    public void hashInsert(String K, Handle h) {
        if (fullness != 0) {
            if ((tableSize / fullness) < 2) {
                extendTable();
            }
        }

        int home = sFoldHash(K);
        int pos = home;

        if (hT[pos] == null) {
            hT[pos] = new HashRecord(K, h);
            fullness++;
            return;
        }
        else if (hT[pos].getKey().equals(EMPTYKEY)) {
            hT[pos].setKey(K);
            hT[pos].setHandle(h);
            fullness++;
            return;
        }
        else {
            pos = probe(K, home);
            hT[pos].setKey(K);
            hT[pos].setHandle(h);
            fullness++;
            return;
        }
    }

    /**
     * Removes a string from the HashTable
     * 
     * @param K
     *       The record to be removed from the
     *       HashTable 
     * @return
     *       True if the record is removed, and 
     *       false otherwise 
     */
    public Handle hashRemove(String K) {
        // a tombstone will be a null HashRecord
        int pos = posSearch(K);
        if (pos == -1) {
            return null;
        }
        else {
            Handle retH = hT[pos].getHandle();
            hT[pos] = null;
            fullness--;
            return retH;
        }
    }

    /**
     * Search for a record with key K
     * 
     * @param K
     *       The key of the record 
     * @param e
     *       The record 
     * @return boolean
     *        True if the record is in the 
     *        HashTable, and false 
     *        otherwise
     */
    public boolean hashSearch(String K) {
        int home = sFoldHash(K);
        int pos = home;
        int j = 0;

        boolean cont = true;

        while (cont) {
            if (hT[pos] == null) { // if the index is a tombstone
                pos = (home + j * j) % tableSize;
                j++;
            }
            else if (hT[pos].getKey().equals(EMPTYKEY)) {
                return false; // if it gets here, there is no Key K
            }
            else if (!(hT[pos].getKey().equals(K))) {
                pos = (home + j * j) % tableSize;
                j++;
            }
            else {
                cont = false;
            }
        }
        return true;
    }

    /**
     * Searches for the record in the HashTable
     * 
     * @param K
     *       The key of the record 
     * @return int
     *       The position of the record
     */
    public int posSearch(String K) {
        int home = sFoldHash(K);
        int pos = home;
        int j = 0;

        boolean cont = true;

        while (cont) {
            if (hT[pos] == null) { // if the index is a TombStone
                pos = (home + j * j) % tableSize;
                j++;
            }
            else if (hT[pos].getKey().equals(EMPTYKEY)) {
                return -1; // if it gets here, there is no Key K
            }
            else if (!(hT[pos].getKey().equals(K))) {
                pos = (home + j * j) % tableSize;
                j++;
            }
            else {
                cont = false;
            }
        }
        return pos;
    }

    /**
     * Does the quadratic probe to find the next
     * possible spot 
     * 
     * @param K
     *       The key of the record
     * @param i
     *       The starting location of the probe
     * @return int
     *       The position in the HashTable where
     *       the record is to be stored
     */
    public int probe(String K, int i) {
        int home = i;
        int pos = home;
        int j = 1;

        while (hT[pos].getKey() != EMPTYKEY) {
            pos = (home + j * j) % tableSize;
            j++;
            while (hT[pos] == null) {
                pos = (home + j * j) % tableSize;
                j++;
            }
        }

        return pos;
    }

    /**
     * Extends the table when the table isn't 
     * large enough to store more records
     */
    public void extendTable() {
        HashRecord[] oldTable = hT;
        int oldSize = tableSize;
        tableSize *= 2;

        hT = new HashRecord[tableSize];
        fullness = 0;
        
        Handle emptyHandle = new Handle(-1);

        for (int i = 0; i < tableSize; i++) {
            hT[i] = new HashRecord("", emptyHandle);
        }

        for (int i = 0; i < oldSize; i++) {
            HashRecord temp = oldTable[i];
            if (temp != null) {
                hashInsert(temp.getKey(), temp.getHandle());
            }
        }
        
        System.out.println(name + " hash table size doubled");
    }

    /**
     * This method is the string folding hash function.
     * Use folding on a string, summed 4 bytes at a time
     * 
     * @param s
     * @param M
     * @return
     */
    public int sFoldHash(String s) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int)(Math.abs(sum) % tableSize); // don't forget to % table size
    }

    /**
     * Returns the table size
     * 
     * @return int
     *      The size of the table
     */
    public int getTableSize() {
        return tableSize;
    }
    
    /**
     * Returns the name of the table
     * @return
     */
    public String getName() {
        return name;
    }

    
    private static class HashRecord {
        /**
         * The key of the HashRecord
         */
    	String key;
    	/**
    	 * The handle of the HashRecord
    	 */
        Handle handle;

        /**
         * Constructor
         * 
         * @param k
         *       The key of the record
         * @param h
         *       The record
         */
        public HashRecord(String k, Handle h) {
            key = k;
            handle = h;
        }


        private String getKey() {
            return key;
        }


        private Handle getHandle() {
            return handle;
        }


        private void setKey(String K) {
            key = K;
        }


        private void setHandle(Handle h) {
            handle = h;
        }
    }
}
