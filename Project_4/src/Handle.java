/**
 * Is the handle of the record in the mempool
 * that is represented in the HashTable
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-11-30
 */

public class Handle {

    private int posInMP;

    /**
     * The constructor for Handle
     * 
     * @param pos
     *            The pos saved by Handle
     */
    public Handle(int pos) {
        posInMP = pos;
    }


    /**
     * Returns the posInMP
     * 
     * @return
     *         The position in the MP where
     *         the handle is (I think)
     */
    public int getPosInMp() {
        return posInMP;
    }

}
