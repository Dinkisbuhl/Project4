import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MemMan {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 1) {
            System.out.println("Error message (change later)");
            return;
        }

        String filename = args[0].trim();
        File commandFile = new File(filename);
        if (!commandFile.exists()) {
            System.out.println("File does not exist: " + filename);
            return;
        }

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
                            // TO DO
                        }
                        else if (insertWhat.equals("song")) {
                            // TO DO
                        }
                        else {
                            String artistAndSong = cScanner2.next();
                            String[] splits = artistAndSong.split("<");
                            String[] findingSong = splits[1].split(">");

                            String artist = splits[0];
                            String song = findingSong[1];

                            // TO DO
                        }
                    }
                    else if (next.equals("remove")) {
                        String removeWhat = cScanner2.next();
                        if (removeWhat.equals("song")) {
                            // TO DO
                        }
                        else if (removeWhat.equals("artist")) {
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
