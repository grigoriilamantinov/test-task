package org.example;

import org.example.text.TextHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class TextHandlerTest {
    TextHandler textHandler = new TextHandler();

    @ParameterizedTest
    @MethodSource("methodDataProvider")
    void getSimilarLine(
        final List<String> line1,
        final List<String> line2,
        final List<String> exceptedResult
    ) {

        final var actualResult = textHandler.getSimilarLine(line1, line2);
        Assertions.assertEquals(exceptedResult, actualResult);
    }

    static Stream<Arguments> methodDataProvider() {
        final var case1Line1 = List.of(
            "гвоздь", "шуруп", "краска синяя", "ведро для воды"
        );
        final var case1Line2 = List.of(
            "шуруп 3х15", "краска", "корыто для воды"
        );
        final var exceptedResult = List.of(
            "гвоздь:?", "шуруп:шуруп 3х15", "краска синяя:краска", "ведро для воды:корыто для воды"
        );

        final var case2Line1 = List.of(
            "Бетон с присадкой"
        );
        final var case2Line2 = List.of(
            "Цемент"
        );
        final var exceptedResult2 = List.of(
            "Бетон с присадкой:Цемент"
        );

        final var case3Line2 = List.of(
            "присадка для бетона", "доставка"
        );
        final var exceptedResult3 = List.of(
            "Бетон с присадкой:присадка для бетона", "доставка:?"
        );
        return Stream.of(
            arguments(case1Line1, case1Line2, exceptedResult),
            arguments(case2Line1, case2Line2, exceptedResult2),
            arguments(case2Line1, case3Line2, exceptedResult3)
        );
    }
}