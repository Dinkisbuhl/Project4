
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
     *         The byte array
     */
    public byte[] getByteArr() {
        return data;
    }


    /**
     * Gets the FreeList from the MemPool
     * 
     * @return
     *         The FreeList of the MemPool
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
     *            The string record being put into the byte[]
     */
    public void insert(String str) {

        // Gets the bytes and the length of
        // the input string
        byte[] bytes = str.getBytes();
        short leng = (short)bytes.length; // a short is 2 bytes
        byte[] lenBytes = new byte[2];
        lenBytes[0] = (byte)(leng >> 8);
        lenBytes[1] = (byte)(leng);

        int totalLength = leng + 2;

        FreeBlock bestFit = new FreeBlock(-1, -1);
        boolean first = true;

        // find the smallest size freeblock, and set bestFit equal to it
        int freeListLen = freelist.getSize();
        for (int i = 0; i < freeListLen; i++) {
            FreeBlock curr = (FreeBlock)freelist.getNode(i).getItem();
            if (curr.getSize() >= totalLength) {
                if (first) {
                    bestFit = curr;
                    first = false;
                }
                else if (curr.getSize() < bestFit.getSize()) {
                    bestFit = curr;
                }
            }
        }

        // if we still are left with the original bestFit freeblock, then we
        // must have no freeblock big enough left... so expand
        if (bestFit.getSize() == -1) {
            expand();
        }

        // from here we must now subtract space from the freeblock we've found,
        // or make a new freeblock out of the space we've found idk, and then
        // copy over the bytes from lenBytes and then bytes in that order
        
        
        // end of my changes so far
        

        // Adds a FreeBlock showing the free space
        // that was used up
        FreeBlock block = (FreeBlock)freelist.getNode(0).getItem();
        block.setPosition(leng + 2 + 1);

        // Adds the string bytes to the
        // byte[]
        int j = 0;
        for (int i = block.getPosition(); i < block.getPosition() + leng; i++) {
            data[i] = bytes[j];
            j++;
        }
    }


    /**
     * Removes a string record from the byte[]
     * 
     * @param str
     *            The string record to be removed from the byte[]
     * 
     */
    public void remove(String str) {

    	// Converts the string input to bytes
        byte[] bytes = str.getBytes();
        short leng = (short)bytes.length; // a short is 2 bytes
        byte[] lenBytes = new byte[2];
        lenBytes[0] = (byte)(leng >> 8);
        lenBytes[1] = (byte)(leng);
        int totalLength = leng + 2;

        // Finds the starting location of the string to remove 
        int startingLocation = -1;
        for (int i = 0; i < bytes.length - 2 - totalLength; i++) {
            if (bytes[i] == lenBytes[0] && bytes[i + 1] == lenBytes[1]) {
                startingLocation = i;
                int j = 0;
                if (data[i + 2] == bytes[j]) {
                    for (int k = 0; k < totalLength; i++) {
                   	    if (data[i + 2 + k] != bytes[j + k]) {
                   	        break;
                   	    }
                    }
                }
            }
            startingLocation = -1;
        }

        // Adds a FreeBlock to the FreeList 
        if (startingLocation == -1) {
            System.out.println("The record doesn't exist in the MemPool");
        }
        else {
            // Empties the byte[] 
            for (int i = startingLocation; i < startingLocation + totalLength; i++) {
                data[i] = 0;
            }

            // Puts a FreeBlock into the FreeList 
            FreeBlock newFreeBlock = new FreeBlock(startingLocation, totalLength);
            freelist.insert(newFreeBlock);
            merge(newFreeBlock);
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
        System.out.println("Memory pool expanded to be " + bigByte.length
            + " bytes");
    }


    /**
     * Merges FreeBlocks in the FreeList together
     */
    private void merge(FreeBlock fb) {
    
        int startLoc = fb.getPosition();
        int sizeOfFreeBloc = fb.getSize();
        for (int i = 0; i < freelist.getSize(); i++) { 
            FreeBlock fB = (FreeBlock)freelist.getNode(i).getItem();
            
            // Merge the FreeBlock with the FreeBlock behind it 
            if ()
            
            // Merge the FreeBlock with the FreeBlock in front of it 
            
            
            // Merge the FreeBlcok with the FreeBlocks in front of it and behind it 
            
        }

//    	for (int i = 0; i < freelist.getSize() - 1; i++) {
//            FreeBlock fb = (FreeBlock)freelist.getNode(i).getItem();
//            for (int j = 1; j < freelist.getSize(); i++) {
//                FreeBlock fb1 = (FreeBlock)freelist.getNode(j).getItem();
//                if (fb.getPosition() + fb.getSize() == fb1.getPosition()) {
//                    FreeBlock newFB = new FreeBlock(fb.getPosition(), fb
//                        .getSize() + fb1.getSize());
//                    freelist.delete(fb);
//                    freelist.delete(fb1);
//                    freelist.insert(newFB);
//                }
//            }
//        }
    }

// /**
// * Inserts a byte into the array
// *
// * @param b
// * The byte to be added into the
// * array
// */
// private void insertIntoByteArr(byte b) {
// int i = 0;
// byte zeroByte = (byte)0;
// while (data[i] != zeroByte) {
// i++;
// }
// data[i] = b;
// }

// /**
// * Finds the best spot to put the data
// * into the MemPool using Best-Fit
// *
// * @param leng
// * The object to be added to the
// * FreeList
// * @return int
// * The location in the FreeList where
// * the thing should be added to
// */
// private int findSpot(int leng) {
// int location = -1;
// int minDistance = Integer.MAX_VALUE;
// Node<FreeBlock> curr = freelist.head.getNext();
// for (int i = 0; i < freelist.getSize(); i++) {
// if ((int)curr.getItem() < leng) {
// int minDist = leng - ((FreeBlock)curr.getItem()).getSize();
// if (minDist < minDistance) {
// minDistance = minDist;
// location = i;
// }
// }
// curr = curr.getNext();
// }
// return location;
// }

}
