import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MemMan {
   
    private static HashTable hT1;
    private static HashTable hT2;
    private static MemPool mpool;
    private static byte[] bytes;
    
    public static void main(String[] args) throws FileNotFoundException {

        int hashSize = 0;
        int memSize = 0;
        
        if (args.length != 3) {
            System.out.println("Error message (change later)");
            return;
        }
        
        try {
            hashSize = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            System.out.println("Error parsing hash size");
        }
        
        try {
            memSize = Integer.parseInt(args[1]);
            bytes = new byte[memSize];
        }
        catch (NumberFormatException e) {
            System.out.println("Error parsing mem size");
        }

        String filename = args[2].trim();
        File commandFile = new File(filename);
        if (!commandFile.exists()) {
            System.out.println("File does not exist: " + filename);
            return;
        }

        hT1 = new HashTable(hashSize);
        hT2 = new HashTable(hashSize);
        DoubleLL<Integer> dll = new DoubleLL<Integer>();
        mpool = new MemPool(bytes, dll);// Add instantiation of MemPool object here
        World world = new World(hT1, hT2, mpool);
        
        try (Scanner commandScanner = new Scanner(commandFile)) {
            while (commandScanner.hasNextLine()) {
                String line = commandScanner.nextLine().trim();
                Scanner cScanner2 = new Scanner(line);
                if (cScanner2.hasNext()) {
                    String next = cScanner2.next();
                    if (next.isBlank()) {
                        continue;
                    }
                    else if (next.equals("print")) {
                        String whatToPrint = cScanner2.next();
                        if (whatToPrint.equals("blocks")) {
                            // TO DO
                        }
                        else if (whatToPrint.equals("songs")) {
                            // TO DO
                        }
                        else if (whatToPrint.equals("artists")) {
                            // TO DO
                        }
                    }
                    else if (next.equals("insert")) {
                        String insertWhat = cScanner2.next();
                        if (insertWhat.equals("artist")) {
                            String artistName = cScanner2.next();
                            
                            // Check if the record if it's in the artist HashTable
                            mpool.insert(artistName);
                        }
                        else if (insertWhat.equals("song")) {
                            String songName = cScanner2.next();
                            
                            // Check if the record if it's in the song HashTable
                            
                        }
                        else {
                            String artistAndSong = cScanner2.next();
                            String[] splits = artistAndSong.split("<");
                            String[] findingSong = splits[1].split(">");

                            String artist = splits[0];
                            String song = findingSong[1];

                           // Check if the record if it's in the artist HashTable
                           // Check if the record if it's in the song HashTable
                           
                        }
                    }
                    else if (next.equals("remove")) {
                        String removeWhat = cScanner2.next();
                        if (removeWhat.equals("song")) {
                            String removeSong = cScanner2.next();
                            // TO DO
                        }
                        else if (removeWhat.equals("artist")) {
                       	    String removeArtist = cScanner2.next();
                            // TO DO
                        }
                    }
                }
                cScanner2.close();
            }
            commandScanner.close();
        }
    }

}
