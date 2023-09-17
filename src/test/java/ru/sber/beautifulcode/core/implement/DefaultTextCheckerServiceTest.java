package ru.sber.beautifulcode.core.implement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import ru.sber.beautifulcode.exception.InvalidArgumentException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DefaultTextCheckerServiceTest {

    private DefaultTextCheckerService service;

    @BeforeEach
    void setUp() {
        service = new DefaultTextCheckerService();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testCheckBracketsForException(String text) {
        assertThatThrownBy(() -> service.checkBrackets(text))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("invalid text");
    }

    @ParameterizedTest
    @MethodSource("argumentsForTestBracketsForFailure")
    void testCheckBracketsForFailure(String text) {
        assertThat(service.checkBrackets(text)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("argumentsForTestBracketsForSuccess")
    void testCheckBracketsForSuccess(String text) {
        assertThat(service.checkBrackets(text)).isTrue();
    }

    private static Stream<String> argumentsForTestBracketsForFailure() {
        return Stream.of(
                "(",
                ")",
                "(()",
                "())",
                "()",
                "( )",
                "((a))",
                "((a)(a))"
        );
    }

    private static Stream<String> argumentsForTestBracketsForSuccess() {
        return Stream.of(
                "aaa",
                "aaa(a)",
                "aaa(a(a))",
                "aaa(a(a)a)aa",
                "(a)",
                "(a)(a)"
        );
    }
}