package ru.sber.beautifulcode.core;

import ru.sber.beautifulcode.exception.InvalidArgumentException;

/**
 * The interface contains methods for checking strings.
 */
public interface TextCheckerService {

    /**
     * Checks the correctness of brackets in string.
     *
     * @param text The text to check.
     * @return The check result.
     * @throws InvalidArgumentException If text is null or empty.
     */
    boolean checkBrackets(String text);
}