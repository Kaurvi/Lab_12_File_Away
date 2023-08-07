import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;
public class FileAway {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser ();
        File selectedFile;
        String line = "";
        ArrayList<String> lines = new ArrayList<> ();
        try {

            File workingDirectory = new File ( System.getProperty ( "user.dir" ) );
            chooser.setCurrentDirectory ( workingDirectory );

            if (chooser.showOpenDialog ( null ) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile ();
                Path file = selectedFile.toPath ();
                InputStream in =
                        new BufferedInputStream ( Files.newInputStream ( file, CREATE ) );
                BufferedReader reader =
                        new BufferedReader ( new InputStreamReader ( in ) );

                int lineCount = 0;
                int wordCount = 0;
                int characterNum = 0;

                while (reader.ready ()) {
                    line = reader.readLine ();
                    lines.add ( line );
                    lineCount++;

                    System.out.printf ( "\nLine %4d %-60s ", lineCount, line );
                    String[] words = line.split ( " " );
                    wordCount = wordCount + words.length;

                    characterNum = characterNum + line.length ();
                    String name = selectedFile.getName ();

                }
                reader.close ();
                System.out.println ( "\n\nData file read!" );
                System.out.println ( "\n\n" );
                String name = selectedFile.getName ();
                System.out.println ( "Name of File" + name );
                System.out.println ( "Number OF Lines in the File" + lineCount );
                System.out.println ( "Number OF words in the File" + wordCount );
                System.out.println ( "Number OF character in the File" + characterNum );


            } else {
                System.out.println ( "ERROR!!! ... exiting.\nRun the program again and select a file." );
            }
        } catch (FileNotFoundException e) {
            System.out.println ( "File not found!!!" );
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
