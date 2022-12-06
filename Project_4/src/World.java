/**
 * Handles the methods in the World Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-02
 */

public class World {

    private HashTable hTSong;
    private HashTable hTArtist;
    private MemPool mpool;

    /**
     * Constructor
     */
    public World(HashTable h1, HashTable h2, MemPool mp) {
        hTSong = h1;
        hTArtist = h2;
        mpool = mp;
    }


    /**
     * Inserts the record into the mpool and puts the handle into the song hash
     * table
     */
    public void insertSong(HashTable h, String input) {

        if (!hTSong.hashSearch(input)) {

            Handle nH = mpool.insert(input);
            hTSong.hashInsert(input, nH);
        }
    }


    /**
     * Inserts the record into the mpool and puts the handle into the artist
     * hash table
     */
    public void insertArtist(HashTable h, String input) {

        if (!hTArtist.hashSearch(input)) {

            Handle nH = mpool.insert(input);
            hTArtist.hashInsert(input, nH);
        }
    }


    /**
     * Removes a record from the World
     */
    public void remove(HashTable h, String input) {

        // Get len from input
        int length = 0; // change later

    }


    /**
     * Prints all the records in the HashTable and the
     * MemPool
     */
    public void print(HashTable h1, HashTable h2, MemPool mp) {

        mpool.printAll();
    }
}
