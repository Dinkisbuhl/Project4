
public class HashTable {

    private int tableSize;
    private HashRecord[] hT;
    private String EMPTYKEY = "";

    public HashTable(int s) {
        tableSize = s;
        hT = new HashRecord[tableSize];
    }


    // Insert e into hash table HT
    public void hashInsert(String k, String e) {
        int home = sFoldHash(k); // Home position for e
        int pos = home; // Init probe sequence
        for (int i = 1; EMPTYKEY != (hT[pos]).getKey(); i++) {
            if (k == hT[pos].getKey()) {
                System.out.println("Duplicates not allowed");
                return;
            }
            //pos = (home + p(k, i)) % tableSize; // probe
        }
        hT[pos].setValue(e);
    }


    // Search for the record with Key K
    public boolean hashSearch(String K, String e) {
        int home = sFoldHash(K); // Home position for K
        int pos = home; // Initial position is the home slot
        for (int i = 1; (K != (hT[pos]).getKey()) && (EMPTYKEY != (hT[pos])
            .getKey()); i++) {
            //pos = (home + p(K, i)) % tableSize; // Next on probe sequence
        }
        if (K == (hT[pos]).getKey()) { // Found it
            e = hT[pos].getValue();
            return true;
        }
        else {
            return false;
        } // K not in hash table
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
        return (int)(Math.abs(sum)); // don't forget to % table size
    }

    private static class HashRecord {
        String key;
        String value;

        public HashRecord(String k, String v) {
            key = k;
            value = v;
        }


        private String getKey() {
            return key;
        }


        private String getValue() {
            return value;
        }
        
        private void setValue(String v) {
            value = v;
        }
    }
}
