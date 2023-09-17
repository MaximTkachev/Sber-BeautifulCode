package ru.sber.beautifulcode.core.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.sber.beautifulcode.core.TextCheckerService;
import ru.sber.beautifulcode.exception.InvalidArgumentException;

@Slf4j
@Service
public class DefaultTextCheckerService implements TextCheckerService {

    @Override
    public boolean checkBrackets(String text) {
        log.info("Check string for brackets = {}", text);

        if (!StringUtils.hasLength(text)) {
            throw new InvalidArgumentException("invalid text");
        }

        int bracketsCounter = 0;
        int textCounter = 0;
        boolean isTextRequired = true;

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            if (isOpeningBracket(character)) {
                bracketsCounter++;
                isTextRequired = true;
            }

            if (isClosingBracket(character)) {
                bracketsCounter--;
                textCounter--;
                isTextRequired = true;

                if (bracketsCounter < 0 || textCounter < 0) {
                    return false;
                }
            }

            if (isAlphabeticSymbol(character) && isTextRequired) {
                textCounter++;
                isTextRequired = false;
            }
        }

        return bracketsCounter == 0;
    }

    private static boolean isOpeningBracket(char c) {
        return c == '(';
    }
    
    private static boolean isClosingBracket(char c) {
        return c == ')';
    }

    private static boolean isAlphabeticSymbol(char c) {
        return Character.isAlphabetic(c);
    }
}
