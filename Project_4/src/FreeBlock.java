
public class FreeBlock {

    private int pos;
    private int size;
    
    /**
     * Constructor 
     */
    public FreeBlock(int p, int s) {
        pos = p;
        size = s;
    }

    /**
     * Returns the pos of the FreeBlock
     * 
     * @return
     *        The freeblock's position
     */
    public int getPosition() {
        return pos;
    }

    /**
     * Returns the size of the FreeBlock
     * 
     * @return
     *        The size of the FreeBlock
     */
    public int getSize() {
        return size;
    }
}
