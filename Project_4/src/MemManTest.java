import java.io.FileNotFoundException;
import student.TestCase;
import student.testingsupport.PrintStreamWithHistory;

/**
 * Tests the methods in the MemMan Class
 * 
 * @author Rakesh Chandraraj, Kyle Hilgenberg
 * @version 2022-12-01
 */

public class MemManTest extends TestCase {

    private String hashTableSize;
    private String memPoolSize;
    private String fileName;
    private MemMan memMan;

    /**
     * Sets the testMP for the methods
     */
    public void setUp() {
        hashTableSize = "10";
        memPoolSize = "32";
        fileName = "simpleInput.txt";

        memMan = new MemMan();
    }


    /**
     * Tests the main method of the project
     */
    public void testMain() {
        String[] arguments = { hashTableSize, memPoolSize, fileName };
        PrintStreamWithHistory sysout = systemOut();
        sysout.clearHistory();

        try {
            memMan.main(arguments);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String history = sysout.getHistory();
        String output =
            "|When Summer's Through| does not exist in the song database.\r\n"
                + "|Blind Lemon Jefferson| is added to the artist database.\r\n"
                + "Memory pool expanded to be 64 bytes.\r\n"
                + "|Long Lonesome Blues| is added to the song database.\r\n"
                + "|Ma Rainey| is added to the artist database.\r\n"
                + "Memory pool expanded to be 96 bytes.\r\n"
                + "|Ma Rainey's Black Bottom| is added"
                + " to the song database.\r\n"
                + "Memory pool expanded to be 128 bytes.\r\n"
                + "|Charley Patton| is added to the artist database.\r\n"
                + "|Mississippi Boweavil Blues| is added"
                + " to the song database.\r\n"
                + "Memory pool expanded to be 160 bytes.\r\n"
                + "|Sleepy John Estes| is added to the artist database.\r\n"
                + "Memory pool expanded to be 192 bytes.\r\n"
                + "|Street Car Blues| is added to the song database.\r\n"
                + "|Bukka White| is added to the artist database.\r\n"
                + "Memory pool expanded to be 224 bytes.\r\n"
                + "|Fixin' To Die Blues| is added to the song database.\r\n"
                + "Artist hash table size doubled.\r\n"
                + "|Guitar Slim| is added to the artist database.\r\n"
                + "Song hash table size doubled.\r\n"
                + "Memory pool expanded to be 256 bytes.\r\n"
                + "|The Things That I Used To Do| is added"
                + " to the song database.\r\n"
                + "|Style Council| does not exist in the artist database.\r\n"
                + "|Ma Rainey| is removed from the artist database.\r\n"
                + "|Mississippi Boweavil Blues| is removed"
                + " from the song database.\r\n"
                + "|(The Best Part Of) Breakin' Up| does not"
                + " exist in the song database.\r\n"
                + "|Blind Lemon Jefferson| duplicates a record"
                + " already in the artist database.\r\n"
                + "|Got The Blues| is added to the song database.\r\n"
                + "|Little Eva| is added to the artist database.\r\n"
                + "Memory pool expanded to be 288 bytes.\r\n"
                + "|The Loco-Motion| is added to the song database.\r\n"
                + "|Jim Reeves| is added to the artist database.\r\n"
                + "|Jingle Bells| is added to the song database.\r\n"
                + "Memory pool expanded to be 320 bytes.\r\n"
                + "|Mongo Santamaria| is added to the artist database.\r\n"
                + "|Watermelon Man| is added to the song database.";

        assertEquals(output, history);
    }
}
