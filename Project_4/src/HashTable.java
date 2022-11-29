
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
    int sfold(String s, int M) {
        long sum = 0, mul = 1;
        for (int i = 0; i < s.length(); i++) {
            mul = (i % 4 == 0) ? 1 : mul * 256;
            sum += s.charAt(i) * mul;
        }
        return (int)(Math.abs(sum) % M);
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
