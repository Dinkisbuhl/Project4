
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
     * Inserts the record into the World
     */
    public void insertSong(HashTable h, String input) {
        
        if (!hTSong.hashSearch(input)) {
            // Get len from input
            int length = 0; // change later
            mpool.insert(input);
        }
    }
    
    /**
     * Inserts the record into the World
     */
    public void insertArtist(HashTable h, String input) {
        
        if (!hTArtist.hashSearch(input)) {
            // Get len from input
            int length = 0; // change later
            mpool.insert(input);
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
     * 
     */
    public void print(HashTable h1, HashTable h2, MemPool mp) {
        
    	mpool.printAll();
    }
}
