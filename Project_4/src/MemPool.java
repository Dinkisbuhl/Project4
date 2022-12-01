
public class MemPool {

    private byte[] data;
    private DoubleLL<Integer> freelist;

    /**
     * 
     * 
     * @param b
     * @param dll
     */
    public MemPool(byte[] b, DoubleLL<Integer> dll) {
        data = b;
        freelist = dll;
    }

    /**
     * 
     * 
     * @param pool
     * @param len
     */
    public void insert(byte[] pool, int len) {
        // If there's no room, expand
    	if (freelist.getSize() == 0) {
            expand();
        }
        
    	// Insert the object into the freelist 
        int loc = findSpot(len);
        if (loc == -1) {
    	    System.out.println("There is no suitable spot for this record"); // CHANGE LATER
    	    return;
        }
        
        
        // Insert the object into the byte array
        
    }

    /**
     * 
     * 
     * @param b
     * @param len
     */
    public void remove(byte[] b, int len) {
        
    }

    /**
     * 
     * 
     * @param b
     * @param len
     */
    public void print(byte[] b, int len) {
        
    }

    /**
     * 
     */
    private void expand() {
        byte[] bigByte = new byte[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            bigByte[i] = data[i];
        }
        data = bigByte;
        System.out.println("Memory pool expanded to be " + bigByte.length + " bytes");
    }

    /**
     * 
     * 
     * @param b
     * @param leng
     * @return
     */
    private int findSpot(int leng) {
        int location = -1;
        int minDistance = Integer.MAX_VALUE;
        Node<Integer> curr = freelist.head.getNext();
    	for (int i = 0; i < freelist.getSize(); i++) {
            if ((int)curr.getItem() < leng) {
                int minDist = leng - (int)curr.getItem();
                if (minDist < minDistance) {
                    minDistance = minDist;
                    location = i;
                }
            }
            curr = curr.getNext();
        }
    	return location;
    }
}



























