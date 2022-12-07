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
    private boolean first;

    /**
     * The constructor for World
     * 
     * @param h1
     *            the song hash table
     * @param h2
     *            the artist hash table
     * @param mp
     *            the memory pool
     */
    public World(HashTable h1, HashTable h2, MemPool mp) {
        hTSong = h1;
        hTArtist = h2;
        mpool = mp;
        first = true;
    }


    /**
     * Inserts the record into the mpool and puts the handle into the song hash
     * table
     * 
     * @param h
     *            the hash table
     * @param input
     *            the string being inserted
     */
    public void insertSong(HashTable h, String input) {

        if (first) {
            first = false;
        }
        else {
            System.out.print("\n");
        }

        if (!hTSong.hashSearch(input)) {

            if (hTSong.getFullness() != 0) {
                if (hTSong.willExtend()) {
                    System.out.print("Song hash table size doubled.\n");
                }
            }

            Handle nH = mpool.insert(input);
            hTSong.hashInsert(input, nH);
            System.out.print("|" + input + "| is added to the song database.");
        }
        else {
            System.out.print("|" + input
                + "| duplicates a record already in the song database.");
        }
    }


    /**
     * Inserts the record into the mpool and puts the handle into the artist
     * hash table
     * 
     * @param h
     *            the hash table
     * @param input
     *            the string being inserted
     */
    public void insertArtist(HashTable h, String input) {

        if (first) {
            first = false;
        }
        else {
            System.out.print("\n");
        }

        if (!hTArtist.hashSearch(input)) {

            if (hTArtist.getFullness() != 0) {
                if (hTArtist.willExtend()) {
                    System.out.print("Artist hash table size doubled.\n");
                }
            }

            Handle nH = mpool.insert(input);
            hTArtist.hashInsert(input, nH);
            System.out.print("|" + input
                + "| is added to the artist database.");
        }
        else {
            System.out.print("|" + input
                + "| duplicates a record already in the artist database.");
        }
    }


    /**
     * Removes a string record from the hash table and memory pool
     * 
     * @param h
     *            the hash table
     * @param input
     *            the string being removed
     */
    public void remove(HashTable h, String input) {

        if (first) {
            first = false;
        }
        else {
            System.out.print("\n");
        }

        if (h.getName().equals("Artist")) {
            if (hTArtist.hashSearch(input)) {
                Handle artistHandle = hTArtist.hashRemove(input);
                mpool.remove(artistHandle);

                System.out.print("|" + input
                    + "| is removed from the artist database.");
            }
            else {
                System.out.print("|" + input
                    + "| does not exist in the artist database.");
            }
        }
        else {
            if (hTSong.hashSearch(input)) {
                Handle songHandle = hTSong.hashRemove(input);
                mpool.remove(songHandle);

                System.out.print("|" + input
                    + "| is removed from the song database.");
            }
            else {
                System.out.print("|" + input
                    + "| does not exist in the song database.");
            }
        }

    }


    /**
     * Prints
     * 
     * @param h1
     *            the song hash table
     * @param h2
     *            the artist hash table
     * @param mp
     *            the memory pool
     */
    public void print(HashTable h1, HashTable h2, MemPool mp) {

        mpool.printAll();
    }
}
