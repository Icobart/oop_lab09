package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String USERHOME = System.getProperty("user.home");
    private static final String OUTPUTFILE = "output.txt";

    private File fileCurr = new File(USERHOME + File.separator + OUTPUTFILE);

    /**
     * Sets a file as current file
     * 
     * @param current file to set as current file
     */
    public void setCurrentFile(final File current) {
        if(current.getParentFile().exists()) {
            this.fileCurr = current;
        }
        else {
            throw new IllegalArgumentException("cannot set current file if it doesn't exist");
        }
    }

    /**
     * Sets a file as current file
     * 
     * @param current file to set as current file
     */
    public void setCurrentFile(final String current) {
        setCurrentFile(new File(current));
    }

    /**
     * Return the current file
     * 
     * @return current file
     */
    public File getCurrentFile() {
        return this.fileCurr;
    }

    /**
     * Return the current file path
     * 
     * @return current file path
     */
    public String getCurrentStringFile() {
        return this.fileCurr.getPath();
    }

    /**
     * Saves the text written
     * @param inputString text that has to be saved
     * @throws IOException if save fails
     */
    public void fetchAndWrite (final String inputString) throws IOException{
        try (PrintStream ps = new PrintStream(fileCurr, StandardCharsets.UTF_8)) {
            ps.println(inputString);
        }
    }

}
