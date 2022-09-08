package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;


public class Main {

    private final static String inputFileName = "/home/grigorii/Документы/projects/test-task/src/test/resources/input.txt";
    private final static String outputFileName = "/home/grigorii/Документы/projects/test-task/src/test/resources/output.txt";

    public static void main(String[] args) {
        TextParser textParser = new TextParser();
        TextHandler textHandler = new TextHandler();

        List<String> content;
        try {
            content = Files.readAllLines(Paths.get(inputFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var b = textParser.parseData(content);
        var text = textHandler.getSimilarLine(b.getFirstStrings(), b.getSecondStrings());
        try {
            Files.write(Paths.get(outputFileName), text, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}