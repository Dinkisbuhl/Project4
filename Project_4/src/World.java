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
            System.out.println("|" + input + "| is added to the song database.");
        }
        else {
            System.out.println("|" + input
                + "| duplicates a record already in the song database.");
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
            System.out.println("|" + input
                + "| is added to the artist database.");
        }
        else {
            System.out.println("|" + input
                + "| duplicates a record already in the artist database.");
        }
    }


    /**
     * Removes a record from the World
     */
    public void remove(HashTable h, String input) {

        if (h.getName().equals("Artist")) {
            if (hTArtist.hashSearch(input)) {
                Handle artistHandle = hTArtist.hashRemove(input);
                mpool.remove(artistHandle);
                
                System.out.println("|" + input
                    + "| is removed from the artists database.");
            }
            System.out.println("|" + input
                + "| does not exist in the artists database."); 
        }  
        else {
            if (hTSong.hashSearch(input)) {
                Handle songHandle = hTSong.hashRemove(input);
                mpool.remove(songHandle);
                
                System.out.println("|" + input
                    + "| is removed from the song database.");
            }
            System.out.println("|" + input
                + "| does not exist in the song database."); 
        }

    }


    /**
     * Prints all the records in the HashTable and the
     * MemPool
     */
    public void print(HashTable h1, HashTable h2, MemPool mp) {

        mpool.printAll();
    }
}
