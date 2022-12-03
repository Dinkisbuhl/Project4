
public class MemPool {

    private byte[] data;
    private DoubleLL<FreeBlock> freelist;
    private int initialSize;

    /**
     * 
     * 
     * @param b
     * @param dll
     */
    public MemPool(byte[] b, DoubleLL<FreeBlock> dll) {
        data = b;
        freelist = dll;
        initialSize = b.length;
        FreeBlock startFBlock = new FreeBlock(0, initialSize);
        freelist.insert(startFBlock);
    }
    
    /**
     * Gets the byte array from the MemPool
     * 
     * @return
     *        The byte array
     */
    public byte[] getByteArr() {
        return data;
    }

    /**
     * 
     * 
     * @param pool
     * @param len
     */
    public void insert(String str) {
        
    	// If there's no room, expand 
        if (freelist.getSize() == 0) {
            expand();
        }
        
        byte[] bytes = str.getBytes();
        int leng = bytes.length + 2; 
        
        FreeBlock block = (FreeBlock) freelist.getNode(0).getItem();
        block.setPosition(leng + 2 + 1);
        
    }

    /**
     * 
     * 
     * @param b
     * @param len
     */
    public void remove(String str) {
        
        byte[] artist = str.getBytes();
        int leng = artist.length + 2; 
        
        
        
    }

    /**
     * 
     */
    public void printAll() {
        System.out.println("Blocks in the MemPool: ");
//        for (int i = 0; i < data.length; i++) {
//            System.out.println(data[i]);
//        }
    }

    /**
     * Increases space for the MemPool if there is no
     * more space available
     */
    private void expand() {
        byte[] bigByte = new byte[data.length + initialSize];
        for (int i = 0; i < data.length; i++) {
            bigByte[i] = data[i];
        }
        data = bigByte;
        
        FreeBlock newStartFBlock = new FreeBlock(0, initialSize);
        freelist.insert(newStartFBlock);
        System.out.println("Memory pool expanded to be " + bigByte.length + " bytes");
    }
    
    /**
     * Inserts a byte into the array
     * 
     * @param b
     *       The byte to be added into the 
     *       array 
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
     * Finds the best spot to put the data 
     * into the MemPool using Best-Fit
     * 
     * @param leng
     *       The object to be added to the
     *       FreeList
     * @return int
     *        The location in the FreeList where
     *        the thing should be added to
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



























