/**
 * Handles the free space in the byte[]
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-02
 */

public class FreeBlock {

    private int pos;
    private int size;

    /**
     * The constructor for FreeBlock
     * 
     * @param p
     *            the input position
     * @param s
     *            the input size
     */
    public FreeBlock(int p, int s) {
        pos = p;
        size = s;
    }


    /**
     * Returns the pos of the FreeBlock
     * 
     * @return
     *         The freeblock's position
     */
    public int getPosition() {
        return pos;
    }


    /**
     * Returns the size of the FreeBlock
     * 
     * @return int
     *         The size of the FreeBlock
     */
    public int getSize() {
        return size;
    }


    /**
     * Changes the position of the FreeBlock
     * 
     * @param p
     *            The new position of the FreeBlock
     */
    public void setPosition(int p) {
        pos = p;
    }


    /**
     * Changes the size of the FreeBlock
     * 
     * @param s
     *            The new size of the FreeBlock
     */
    public void setSize(int s) {
        size = s;
    }


    /**
     * Converts a FreeBlock to a string
     * 
     * @return
     *         The FreeBlock as a string
     */
    public String toString() {
        return "(" + pos + "," + size + ")";
    }

}
