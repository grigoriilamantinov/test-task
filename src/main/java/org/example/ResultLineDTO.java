package org.example;

public class ResultLineDTO {
    private String firstLine;
    private String secondLine;
    private Integer highestNumberMatches;

    public ResultLineDTO(String firstLine, String secondLine, Integer highestNumberMatches) {
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.highestNumberMatches = highestNumberMatches;
    }


    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public Integer getHighestNumberMatches() {
        return highestNumberMatches;
    }

    public void setHighestNumberMatches(Integer highestNumberMatches) {
        this.highestNumberMatches = highestNumberMatches;
    }

    @Override
    public String toString() {
        return "ResultLineDTO{" +
            "firstLine='" + firstLine + '\'' +
            ", secondLine='" + secondLine + '\'' +
            ", highestNumberMatches=" + highestNumberMatches +
            '}';
    }
}
