
public class MemPool {

    private byte[] data;
    private DoubleLL<Integer> freelist;
    private int initialSize;

    /**
     * 
     * 
     * @param b
     * @param dll
     */
    public MemPool(byte[] b, DoubleLL<Integer> dll) {
        data = b;
        freelist = dll;
        initialSize = b.length;
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
        @SuppressWarnings("unchecked")
		Node<Integer> changeNode = freelist.getNode(loc);
        changeNode.setItem((int)changeNode.getItem() - len);
        freelist.update();
        
        // Insert the object into the byte array
        byte b = (byte)loc;
        insertIntoByteArr(b);
    }

    /**
     * 
     * 
     * @param b
     * @param len
     */
    public void remove(byte[] b, int len) {
        byte by = (byte)len;
        byte z = (byte)0;
        
        // remove something from the byte array
        for (int i = 0; i < b.length; i++) {
            if (b[i] == by) {
                b[i] = z;
            }
        }
        
        // add free space to the freelist
        freelist.insert(len);
    }

    /**
     * 
     * 
     * @param b
     * @param len
     */
    public void print(byte[] b, int len) {
        System.out.println("Blocks in the MemPool");
    	for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }

    /**
     * 
     */
    private void expand() {
        byte[] bigByte = new byte[data.length + initialSize];
        for (int i = 0; i < data.length; i++) {
            bigByte[i] = data[i];
        }
        freelist.insert(data.length);
        data = bigByte;
        System.out.println("Memory pool expanded to be " + bigByte.length + " bytes");
    }
    
    /**
     * 
     * @param b
     */
    private void insertIntoByteArr(byte b) {
        int i = 0;
        byte zeroByte = (byte)0;
        while (data[i] != zeroByte) {
            i++;
        }
        data[i] = b;
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



























