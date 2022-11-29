
public class HashTable {

    private int tableSize;
    private HashRecord[] theTable;

    public HashTable(int s) {
        tableSize = s;
        theTable = new HashRecord[tableSize];
    }


    public void insert() {

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
    }
}
