package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String next;
    private final List<String> stringHistory = new LinkedList<>();

    @Override
    public void setNextString(String nextString) {
        if(Objects.isNull(nextString)) {
            throw new IllegalArgumentException("null values are not acceptable");
        }
        this.next = nextString;
    }

    @Override
    public String getNextString() {
        return this.next;    
    }

    @Override
    public List<String> getHistoryStrings() {
        return new LinkedList<>(this.stringHistory);    
    }

    @Override
    public void printCurrentString() {
        if(Objects.isNull(this.next)) {
            throw new IllegalStateException("current string is unset");
        }
        System.out.println(this.next);
        this.stringHistory.add(this.next);
    }

}
