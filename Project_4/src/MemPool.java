
public class MemPool {

    private byte[] data;
    private DoubleLL<FreeBlock> freelist;
    private int initialSize;

    /**
     * Constructor for the MemPool
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
     * Gets the freelist from the MemPool
     * 
     * @return
     *        The FreeList of the MemPool
     */
    public DoubleLL<FreeBlock> getFreeList() {
        return freelist;
    }
    
    /**
     * Gets the initialSize of the MemPool
     */
    public int getInitialSize() {
        return initialSize;
    }

    /**
     * Inserts a string record into the byte[]
     * 
     * @param str
     *       The string record being put into the byte[]
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
     * Removes a string record from the byte[]
     * 
     * @param str
     *       The string record to be removed from the byte[]
     *       
     */
    public void remove(String str) {
        
        byte[] artist = str.getBytes();
        int leng = artist.length + 2; 
        int j = 0;
        int k = 0;
        int counter = 1;
        int startingLocation = -1;
        
        // Finds the starting location of the string to remove
        for (int i = 0; i < freelist.getSize(); i++) {
        	j = 0;
            if (data[i] == artist[j]) {
                while (data[i + 1 + k] == artist[j + 1 + k]) {
                    k++;
                    counter++;
                    if (counter == leng) {
                        startingLocation = i - leng;
                        break;
                    }
                }
                k = 0;
            }
        }
        
        // Empties the byte[] 
        for (int i = startingLocation; i < startingLocation + leng; i++) {
            data[i] = 0;
        }
        
        // Adds a FreeBlock to the FreeList
        if (startingLocation == -1) {
            System.out.println("The record doesn't exist in the MemPool");
        }
        else {
        	FreeBlock newFreeBlock = new FreeBlock(startingLocation, leng);
            freelist.insert(newFreeBlock);
            merge();
        }
        
    }

    /**
     * Prints all the elements in the FreeList
     */
    public void printAll() {
        freelist.print();
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
     * Merges FreeBlocks in the FreeList together
     */
    private void merge() {
        for (int i = 0; i < freelist.getSize() - 1; i++) {
            FreeBlock fb = (FreeBlock) freelist.getNode(i).getItem();
            for (int j = 1; j < freelist.getSize(); i++) {
                FreeBlock fb1 = (FreeBlock) freelist.getNode(j).getItem();
                if (fb.getPosition() + fb.getSize() == fb1.getPosition()) {
                    FreeBlock newFB = new FreeBlock(fb.getPosition(), fb.getSize() + fb1.getSize());
                    freelist.delete(fb);
                    freelist.delete(fb1);
                    freelist.insert(newFB);
                }
            }
        }
    }

//    /**
//     * Inserts a byte into the array
//     * 
//     * @param b
//     *       The byte to be added into the 
//     *       array 
//     */
//    private void insertIntoByteArr(byte b) {
//        int i = 0;
//        byte zeroByte = (byte)0;
//        while (data[i] != zeroByte) {
//            i++;
//        }
//        data[i] = b;
//    }

//    /**
//     * Finds the best spot to put the data 
//     * into the MemPool using Best-Fit
//     * 
//     * @param leng
//     *       The object to be added to the
//     *       FreeList
//     * @return int
//     *        The location in the FreeList where
//     *        the thing should be added to
//     */
//    private int findSpot(int leng) {
//        int location = -1;
//        int minDistance = Integer.MAX_VALUE;
//        Node<FreeBlock> curr = freelist.head.getNext();
//    	for (int i = 0; i < freelist.getSize(); i++) {
//            if ((int)curr.getItem() < leng) {
//                int minDist = leng - ((FreeBlock)curr.getItem()).getSize();
//                if (minDist < minDistance) {
//                    minDistance = minDist;
//                    location = i;
//                }
//            }
//            curr = curr.getNext();
//        }
//    	return location;
//    }
    
}



























