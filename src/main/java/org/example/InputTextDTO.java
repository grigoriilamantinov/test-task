package org.example;

import java.util.ArrayList;
import java.util.List;

public class InputTextDTO {
    private List<String> firstStrings = new ArrayList<>();
    private List<String> secondStrings = new ArrayList<>();

    public List<String> getFirstStrings() {
        return firstStrings;
    }

    public void setFirstStrings(List<String> firstStrings) {
        this.firstStrings = firstStrings;
    }

    public List<String> getSecondStrings() {
        return secondStrings;
    }

    public void setSecondStrings(List<String> secondStrings) {
        this.secondStrings = secondStrings;
    }

    @Override
    public String toString() {
        return "InputTextDTO{" +
            "firstStrings=" + firstStrings +
            ", secondStrings=" + secondStrings +
            '}';
    }
}


