package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TextHandler {

    public List<String> getSimilarLine (final List<String> lines1, final List<String> lines2) {
        List<ResultLineDTO> resultLineDTOs = new LinkedList<>();
        int highestNumberMatches;
        var wordForUnion = "?";

        List<String> highestList;
        List<String> smallestList;
        boolean isReverse = false;
        if (lines1.size() > lines2.size()) {
            highestList = List.copyOf(lines1);
            smallestList = List.copyOf(lines2);
        } else {
            highestList = List.copyOf(lines2);
            smallestList = List.copyOf(lines1);
            isReverse = true;
        }

        var iteratorHighestList = highestList.iterator();
        while (iteratorHighestList.hasNext()) {
            var line1 = iteratorHighestList.next();
            var iterator = smallestList.iterator();
            highestNumberMatches = 0;
            while (iterator.hasNext()) {
                var line2 = iterator.next();
                var counter = this.getNumberIdenticalLettersInline(line1, line2);
                if (counter > highestNumberMatches) {
                    highestNumberMatches = counter;
                    wordForUnion = line2;
                }
            }
                resultLineDTOs.add(new ResultLineDTO(line1, wordForUnion, highestNumberMatches));
            wordForUnion = "?";
        }
        var b = this.formatToStrings(this.cleanData(resultLineDTOs), isReverse);
        System.out.println(b);
        return b;
    }

    private List<String> formatToStrings(List<ResultLineDTO> resultLineDTO, boolean isReverse) {
        List<String> result = new ArrayList<>();
        if (isReverse) {
            for(ResultLineDTO it : resultLineDTO) {
                if (it.getSecondLine().equals("?")) {
                    result.add(it.getFirstLine() + ":" + it.getSecondLine());
                } else {
                    result.add(it.getSecondLine() + ":" + it.getFirstLine());
                }
            }
        } else {
            result = resultLineDTO.stream()
                .map(it -> it.getFirstLine() + ":" + it.getSecondLine())
                .collect(Collectors.toList());
        }
        return result;
    }

    private List<ResultLineDTO> cleanData(List<ResultLineDTO> resultLineDTOs) {
        var secondLines = resultLineDTOs.stream()
            .map(resultLine -> resultLine.getSecondLine())
            .collect(Collectors.toList());
        Map<String, Integer> valuesCounter = new HashMap<>();

        for (String value : secondLines) {
            if (valuesCounter.containsKey(value)) {
                var buffer = valuesCounter.get(value) + 1;
                valuesCounter.put(value, buffer);
            } else {
                valuesCounter.put(value, 1);
            }
        }
        for (String value : secondLines) {
            if (valuesCounter.get(value) > 1) {
                var list = resultLineDTOs.stream()
                    .filter(resultLineDTO -> resultLineDTO.getSecondLine().equals(value))
                    .collect(Collectors.toList());
                var maxCounter = list.stream()
                    .max(Comparator.comparing(ResultLineDTO::getHighestNumberMatches))
                    .orElseThrow()
                    .getHighestNumberMatches();
                for (ResultLineDTO resultLineDTO : list) {
                    if (!Objects.equals(resultLineDTO.getHighestNumberMatches(), maxCounter)) {
                        resultLineDTO.setSecondLine("?");
                    }
                }
            }
        }
        return resultLineDTOs;
    }

    private int getNumberIdenticalLettersInline(String inputLine1, String inputLine2) {
        var line1 = inputLine1.toCharArray();
        Set<Character> setFromLine1 = new HashSet<>();
        for (Character symbol : line1) {
            setFromLine1.add(symbol);
        }

        var line2 = inputLine2.toCharArray();
        Set<Character> setFromLine2 = new HashSet<>();
        for (Character symbol : line2) {
            setFromLine2.add(symbol);
        }

        int counter = 0;
        for (Character letter : setFromLine1) {
            if (setFromLine2.contains(letter)) {
                counter++;
            }
        }
        return counter;
    }
}
