/**
 * Handles the methods in the MemPool Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-01
 */

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
     * @return DoubleLL<FreeBlock>
     *         The FreeList of the MemPool
     */
    public DoubleLL<FreeBlock> getFreeList() {
        return freelist;
    }


    /**
     * Gets the initialSize of the MemPool
     * 
     * @return int
     *         The initial size of
     *         the MemPool
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
    public Handle insert(String str) {

        // Gets the bytes and the length of
        // the input string
        byte[] bytes = str.getBytes();
        short leng = (short)bytes.length; // a short is 2 bytes
        byte[] lenBytes = new byte[2];
        lenBytes[0] = (byte)(leng >> 8);
        lenBytes[1] = (byte)(leng);

        int totalLength = leng + 2;

        FreeBlock bestFit = new FreeBlock(-1, -1);
        int bestFitIndex = -1;
        boolean first = true;

        // find the smallest size freeblock, and set bestFit equal to it
        int freeListLen = freelist.getSize();
        for (int i = 0; i < freeListLen; i++) {
            Node currNode = freelist.getNode(i);
            FreeBlock curr = (FreeBlock)currNode.getItem();
            if (curr.getSize() >= totalLength) {
                if (first) {
                    bestFit = curr;
                    bestFitIndex = i;
                    first = false;
                }
                else if (curr.getSize() < bestFit.getSize()) {
                    bestFit = curr;
                    bestFitIndex = i;
                }
            }
        }

        // if we still are left with the original bestFit freeblock, then we
        // must have no freeblock big enough left... so expand
        while (bestFit.getSize() == -1) {
            expand();

            freeListLen = freelist.getSize();

            for (int i = 0; i < freeListLen; i++) {
                Node currNode = freelist.getNode(i);
                FreeBlock curr = (FreeBlock)currNode.getItem();
                if (curr.getSize() >= totalLength) {
                    if (first) {
                        bestFit = curr;
                        bestFitIndex = i;
                        first = false;
                    }
                    else if (curr.getSize() < bestFit.getSize()) {
                        bestFit = curr;
                        bestFitIndex = i;
                    }
                }
            }
        }

        // from here we must now subtract space from the freeblock we've found
        // and update it's position, and then copy over the bytes from lenBytes
        // and then bytes in that order

        int oldPos = bestFit.getPosition();

        if (bestFit.getSize() == totalLength) {
            freelist.delete(bestFit);
        }
        else {
            int newSize = bestFit.getSize() - totalLength;
            int newPos = bestFit.getPosition() + totalLength;
            FreeBlock newBlock = new FreeBlock(newPos, newSize);
            freelist.getNode(bestFitIndex).setItem(newBlock);
        }

        // Adds the two byte header and the string bytes to the
        // data[]
        int j = 0;
        data[oldPos] = lenBytes[0];
        data[oldPos + 1] = lenBytes[1];
        for (int i = oldPos + 2; i < oldPos + totalLength; i++) {
            data[i] = bytes[j];
            j++;
        }

        // create a handle and return it for Hash Table use
        Handle retHand = new Handle(oldPos);
        return retHand;
    }


    /**
     * Removes a string record from the byte[]
     * 
     * @param str
     *            The string record to be removed from the byte[]
     * 
     */
    public void remove(Handle ticket) {

        int startPos = ticket.getPosInMp();
        byte[] lenBytes = new byte[2];
        lenBytes[0] = data[startPos];
        lenBytes[1] = data[startPos + 1];

        int lengthOfBytes = (lenBytes[0] & 0xFF) << 8 | (lenBytes[1] & 0xFF);

        // just because I like it better this way, zero out the data
        for (int i = startPos; i < lengthOfBytes + 2; i++) {
            data[i] = 0;
        }

        int freeListLen = freelist.getSize();

        if (freeListLen == 0) {
            FreeBlock newBlock = new FreeBlock(startPos, lengthOfBytes + 2);
            freelist.insert(newBlock);
        }
        else {
            FreeBlock before = new FreeBlock(-1, -1);
            FreeBlock after = new FreeBlock(-1, -1);
            
            int bInd = -1;
            int aInd = -1;
            int thisInd = 0;

            for (int i = 0; i < freeListLen; i++) {
                FreeBlock curr = (FreeBlock)freelist.getNode(i).getItem();

                if (curr.getPosition() + curr.getSize() == startPos) {
                    before = curr;
                    bInd = i;
                }
                if (startPos + lengthOfBytes + 2 == curr.getPosition()) {
                    after = curr;
                    aInd = i;
                }
                
                if (curr.getPosition() < startPos) {
                    thisInd = i;
                }
            }

            if (before.getPosition() != -1 && after.getPosition() != -1) {
                int newSize = before.getSize() + lengthOfBytes + 2 + after.getSize();
                int newPos = before.getPosition();
                FreeBlock newBlock = new FreeBlock(newPos, newSize);
                freelist.getNode(bInd).setItem(newBlock);
                freelist.delete(after);
            }
            else if (before.getPosition() != -1) {
                int newSize = before.getSize() + lengthOfBytes + 2;
                int newPos = before.getPosition();
                FreeBlock newBlock = new FreeBlock(newPos, newSize);
                freelist.getNode(bInd).setItem(newBlock);
            }
            else if (after.getPosition() != -1) {
                int newSize = lengthOfBytes + 2 + after.getSize();
                int newPos = startPos;
                FreeBlock newBlock = new FreeBlock(newPos, newSize);
                freelist.getNode(aInd).setItem(newBlock);
            }
            else {
                FreeBlock newBlock = new FreeBlock(startPos, lengthOfBytes + 2);
                freelist.insert(newBlock, thisInd);
            }
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
        int oldEnd = data.length;
        byte[] bigByte = new byte[data.length + initialSize];
        for (int i = 0; i < data.length; i++) {
            bigByte[i] = data[i];
        }

        data = bigByte;

        FreeBlock lastBlock;

        if (freelist.getSize() == 0) {
            lastBlock = new FreeBlock(oldEnd, initialSize);
            freelist.insert(lastBlock);
        }
        else {
            lastBlock = (FreeBlock)freelist.getNode(freelist.getSize() - 1)
                .getItem();

            // if the last freeblock includes the space at the end, just expand
            // it
            // by initialSize, if not, then instead create a new freeblock
            if (lastBlock.getPosition() + lastBlock.getSize() == oldEnd) {
                int newSize = lastBlock.getSize() + initialSize;
                int newPos = lastBlock.getPosition();
                FreeBlock newBlock = new FreeBlock(newPos, newSize);
                freelist.getNode(freelist.getSize() - 1).setItem(newBlock);
            }
            else {
                FreeBlock newSpaceFBlock = new FreeBlock(oldEnd, initialSize);
                freelist.insert(newSpaceFBlock);
            }
        }

        System.out.println("Memory pool expanded to be " + bigByte.length
            + " bytes");
    }


    /**
     * Merges FreeBlocks in the FreeList together
     */
    private void merge(int firstPos, int secondPos) {
        
    }
}
