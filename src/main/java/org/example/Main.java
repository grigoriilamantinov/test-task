package org.example;

import org.example.text.TextHandler;
import org.example.text.TextParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Main {

    private final static String inputFileName = "/home/grigorii/Документы/projects/test-task/src/main/resources/input.txt";
    private final static String outputFileName = "/home/grigorii/Документы/projects/test-task/src/main/resources/output.txt";

    public static void main(String[] args) {
        TextParser textParser = new TextParser();
        TextHandler textHandler = new TextHandler();

        try {
            final var fulLContentFromFile = Files.readAllLines(Paths.get(inputFileName));
            final var dataForUnion = textParser.parseData(fulLContentFromFile);
            final var textForWriting = textHandler.getSimilarLine(dataForUnion.getFirstStrings(), dataForUnion.getSecondStrings());
            Files.write(Paths.get(outputFileName), textForWriting, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}