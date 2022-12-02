
public class HashTable {

    private int tableSize;
    private int fullness;
    private HashRecord[] hT;
    private String EMPTYKEY = "";

    public HashTable(int s) {
        tableSize = s;
        hT = new HashRecord[tableSize];
        fullness = 0;

        for (int i = 0; i < tableSize; i++) {
            hT[i] = new HashRecord("", "");
        }
    }


    // Insert e into hash table HT
    public void hashInsert(String K, String e) {
        if (fullness != 0) {
            if ((tableSize / fullness) < 2) {
                extendTable();
            }
        }
        int home = sFoldHash(K); // Home position for e
        int pos = home; // Init probe sequence
        if (hT[pos] == null) {
            hT[pos] = new HashRecord(K, e);
            fullness++;
            return;
        }
        for (int i = 1; EMPTYKEY != (hT[pos]).getKey(); i++) {
            if (K == hT[pos].getKey()) {
                System.out.println("Duplicates not allowed");
                return;
            }
            pos = (home + probe(K, i)) % tableSize; // probe
            if (hT[pos] == null) {
                hT[pos] = new HashRecord(K, e);
                fullness++;
                return;
            }
        }
        hT[pos].setKey(K);
        hT[pos].setValue(e);
        fullness++;
    }


    public boolean hashRemove(String K) {
        // a tombstone will be a null HashRecord
        int home = sFoldHash(K); // Home position for K
        int pos = home; // Initial position is the home slot
        for (int i = 1; (K != (hT[pos]).getKey()) && (EMPTYKEY != (hT[pos])
            .getKey()); i++) {
            pos = (home + probe(K, i)) % tableSize; // probe
            if (hT[pos] == null) {
                i++;
            }
        }
        if (K == hT[pos].getKey()) {
            hT[pos] = null; // setting the record to a tombstone
            fullness--;
            return true;
        }
        else {
            return false;
        }
    }


    // Search for the record with Key K
    public boolean hashSearch(String K, String e) {
        int home = sFoldHash(K); // Home position for K
        int pos = home; // Initial position is the home slot
        int i = 1;
        /*
        for (i = 1; (K != (hT[pos]).getKey()) && (EMPTYKEY != (hT[pos])
            .getKey()); i++) {
            pos = (home + probe(K, i)) % tableSize; // Next on probe sequence
            if (hT[pos] == null) {
                i++;
            }
        }
        */
        if (hT[pos] == null) {
            pos = (home + probe(K, i)) % tableSize;
        }
        while ((K != (hT[pos]).getKey()) && (EMPTYKEY != (hT[pos])
            .getKey())) {
            pos = (home + probe(K, i)) % tableSize; // Next on probe sequence
            if (hT[pos] == null) {
                break;
            }
            i++;
        }
        if (hT[pos] == null) {
            return false;
        }
        if (K == (hT[pos]).getKey()) { // Found it
            e = hT[pos].getValue();
            return true;
        }
        else {
            return false;
        } // K not in hash table
    }


    public int probe(String K, int i) {
        int hv = sFoldHash(K);
        int t = hv;
        for (int j = 0; j < tableSize; j++) {
            // Computing the new hash value
            t = (hv + j * j) % tableSize;
            if (hT[t] != null) {
                if (hT[t].getKey() == EMPTYKEY) {

                    // Break the loop after
                    // inserting the value
                    // in the table
                    break;
                }
            }
        }
        return t;
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


    public void extendTable() {
        HashRecord[] oldTable = hT;
        int oldSize = tableSize;
        tableSize *= 2;

        hT = new HashRecord[tableSize];
        fullness = 0;

        for (int i = 0; i < tableSize; i++) {
            hT[i] = new HashRecord("", "");
        }

        for (int i = 0; i < oldSize; i++) {
            HashRecord temp = oldTable[i];
            if (temp != null) {
                hashInsert(temp.getValue(), temp.getKey());
            }
        }
    }


    public int getTableSize() {
        return tableSize;
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


        private void setKey(String K) {
            key = K;
        }


        private void setValue(String v) {
            value = v;
        }
    }
}
