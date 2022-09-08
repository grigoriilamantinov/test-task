package org.example.dto;

public class ResultLineDTO {
    private final String firstLine;
    private String wordForUnion;
    private final Integer highestNumberMatches;

    public ResultLineDTO(String firstLine, String secondLine, Integer highestNumberMatches) {
        this.firstLine = firstLine;
        this.wordForUnion = secondLine;
        this.highestNumberMatches = highestNumberMatches;
    }


    public String getFirstLine() {
        return firstLine;
    }

    public String getWordForUnion() {
        return wordForUnion;
    }

    public void setWordForUnion(String wordForUnion) {
        this.wordForUnion = wordForUnion;
    }

    public Integer getHighestNumberMatches() {
        return highestNumberMatches;
    }

    @Override
    public String toString() {
        return "ResultLineDTO{" +
            "firstLine='" + firstLine + '\'' +
            ", secondLine='" + wordForUnion + '\'' +
            ", highestNumberMatches=" + highestNumberMatches +
            '}';
    }
}
