package org.example.text;

import org.example.dto.ResultLineDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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

        for (String firstLine : highestList) {
            var iteratorSmallestList = smallestList.iterator();
            highestNumberMatches = 0;
            while (iteratorSmallestList.hasNext()) {
                var secondLine = iteratorSmallestList.next();
                var counter = this.getNumberIdenticalLettersInline(firstLine, secondLine);
                if (counter > highestNumberMatches) {
                    highestNumberMatches = counter;
                    wordForUnion = secondLine;
                }
            }
            resultLineDTOs.add(new ResultLineDTO(firstLine, wordForUnion, highestNumberMatches));
        }
        var resultLineDTOWithMarks = this.removeDuplicatesFromWordForUnion(resultLineDTOs);

        return this.formatToStrings(resultLineDTOWithMarks, isReverse);
    }

    private List<String> formatToStrings(List<ResultLineDTO> resultLineDTO, boolean isReverse) {
        List<String> result = new ArrayList<>();
        if (isReverse) {
            for(ResultLineDTO it : resultLineDTO) {
                if (it.getWordForUnion().equals("?")) {
                    result.add(it.getFirstLine() + ":" + it.getWordForUnion());
                } else {
                    result.add(it.getWordForUnion() + ":" + it.getFirstLine());
                }
            }
        } else {
            result = resultLineDTO.stream()
                .map(it -> it.getFirstLine() + ":" + it.getWordForUnion())
                .collect(Collectors.toList());
        }
        return result;
    }

    private List<ResultLineDTO> removeDuplicatesFromWordForUnion(final List<ResultLineDTO> resultLineDTOs) {
        final var secondLines = resultLineDTOs.stream()
            .map(ResultLineDTO::getWordForUnion)
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
                final var list = resultLineDTOs.stream()
                    .filter(resultLineDTO -> resultLineDTO.getWordForUnion().equals(value))
                    .collect(Collectors.toList());
                final var maxCounter = list.stream()
                    .max(Comparator.comparing(ResultLineDTO::getHighestNumberMatches))
                    .orElseThrow()
                    .getHighestNumberMatches();
                for (ResultLineDTO resultLineDTO : list) {
                    if (!Objects.equals(resultLineDTO.getHighestNumberMatches(), maxCounter)) {
                        resultLineDTO.setWordForUnion("?");
                    }
                }
            }
        }
        return resultLineDTOs;
    }

    private int getNumberIdenticalLettersInline(final String inputLine1, final String inputLine2) {
        final var line1 = inputLine1.toCharArray();
        final Set<Character> setFromLine1 = new HashSet<>();
        for (Character symbol : line1) {
            setFromLine1.add(symbol);
        }

        var line2 = inputLine2.toCharArray();
        final Set<Character> setFromLine2 = new HashSet<>();
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
