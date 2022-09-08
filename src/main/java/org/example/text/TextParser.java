package org.example.text;

import org.example.dto.DataForUnion;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private final static Pattern amountStrings = Pattern.compile("^\\d+$");
    private final static Pattern anyLetter = Pattern.compile("[а-яА-Я]");

    public DataForUnion parseData (List<String> inputText) {
        Matcher amountStringsMatcher;
        Matcher anyLetterMatcher;
        int counter = 0;
        DataForUnion inputTextDTO = new DataForUnion();
        for (String line : inputText) {
            amountStringsMatcher = amountStrings.matcher(line);
            anyLetterMatcher = anyLetter.matcher(line);
            if (amountStringsMatcher.find()) {
                counter++;
            }
            if (counter == 1) {
                if (anyLetterMatcher.find()) {
                    inputTextDTO.getFirstStrings().add(line);
                }
            } else {
                if (anyLetterMatcher.find()) {
                    inputTextDTO.getSecondStrings().add(line);
                }
            }
        }
        return inputTextDTO;
    }

}
