
public class HashTable {

    private int tableSize;
    private HashRecord[] theTable;
    
    public HashTable(int s) {
        tableSize = s;
        theTable = new HashRecord[tableSize];
    }

    /**
     * This method is the string folding hash function
     * 
     * @param s
     * @param M
     * @return
     */
    private long sfold(String str, int M) {
        int intLength = str.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char c[] = str.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char c[] = str.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        sum = (sum * sum) >> 8;
        return (Math.abs(sum) % M);
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
