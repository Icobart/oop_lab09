package it.unibo.mvc;

import java.util.List;

/**
 * 
 */
public interface Controller {

    /**
     * @param nextString next string to print on standard output
     */
    public void setNextString(String nextString);

    /**
     * 
     * @return the next string to print
     */
    public String getNextString();

    /**
     * 
     * @return the history of the printed strings
     */
    public List<String> getHistoryStrings();

    /**
     * 
     */
    public void printCurrentString();
}
