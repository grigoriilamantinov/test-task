package org.example.dto;

import java.util.ArrayList;
import java.util.List;

public class DataForUnion {
    private final List<String> firstStrings = new ArrayList<>();
    private final List<String> secondStrings = new ArrayList<>();

    public List<String> getFirstStrings() {
        return firstStrings;
    }

    public List<String> getSecondStrings() {
        return secondStrings;
    }

    @Override
    public String toString() {
        return "InputTextDTO{" +
            "firstStrings=" + firstStrings +
            ", secondStrings=" + secondStrings +
            '}';
    }
}


