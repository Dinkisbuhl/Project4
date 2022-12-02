public class TempTable {

    private int tableSize;
    private int fullness;
    private HashRecord[] hT;
    private String EMPTYKEY = "";

    public TempTable(int s) {
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

        int home = sFoldHash(K);
        int pos = home;

        if (hT[pos] == null) {
            hT[pos] = new HashRecord(K, e);
            fullness++;
            return;
        }
        else if (hT[pos].getKey().equals(EMPTYKEY)) {
            hT[pos].setKey(K);
            hT[pos].setValue(e);
            fullness++;
            return;
        }
        else {
            pos = probe(K, home);
            hT[pos].setKey(K);
            hT[pos].setValue(e);
            fullness++;
            return;
        }
    }


    public boolean hashRemove(String K) {
        return false;
    }


    /**
     * Search for a record with key K
     * 
     * @param K
     * @param e
     * @return
     */
    public boolean hashSearch(String K, String e) {
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
