package org.example;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private final static Pattern amountStrings = Pattern.compile("^\\d+$");
    private final static Pattern anyLetter = Pattern.compile("[а-яА-Я]");

    public InputTextDTO parseData (List<String> inputText) {
        Matcher amountStringsMatcher;
        Matcher anyLetterMatcher;
        int counter = 0;
        InputTextDTO inputTextDTO = new InputTextDTO();
        for (int i = 0; i < inputText.size(); i++) {
            String line = inputText.get(i);
            amountStringsMatcher = amountStrings.matcher(line);
            anyLetterMatcher = anyLetter.matcher(line);
            if (amountStringsMatcher.find()) {
                counter++;
            }
            if (counter == 1) {
                if(anyLetterMatcher.find()) {
                    inputTextDTO.getFirstStrings().add(line);
                }
            } else {
                if(anyLetterMatcher.find()) {
                    inputTextDTO.getSecondStrings().add(line);
                }
            }
        }
        return inputTextDTO;
    }

}
