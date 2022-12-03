
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
     * @return int
     *        The size of the FreeBlock
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Changes the position of the FreeBlock
     * 
     * @param p
     *       The new position of the FreeBlock
     */
    public void setPosition(int p) {
        pos = p;
    }
    
    /**
     * Changes the size of the FreeBlock
     * 
     * @param s
     *       The new size of the FreeBlock
     */
    public void setSize(int s) {
        size = s;
    }

    
}
