
public class World {

    private HashTable ht1;
    private HashTable ht2;
    private MemPool mpool;
    
    /**
     * Constructor
     */
    public World(HashTable h1, HashTable h2, MemPool mp) {
        ht1 = h1;
        ht2 = h2;
        mpool = mp;
    }
    
    /**
     * Inserts the record into the World
     */
    public void insert(HashTable h, String input) {
        
    	// Get len from input
    	int length = 0; // change later
        mpool.insert(mpool.getByteArr(), length);
    }
    
    /**
     * Removes a record from the World
     */
    public void remove(HashTable h, String input) {
        
        // Get len from input
    	int length = 0; // change later
        mpool.insert(mpool.getByteArr(), length);
    }
    
    /**
     * 
     */
    public void print(HashTable h1, HashTable h2, MemPool mp) {
        
    	mpool.printAll();
    }
}
