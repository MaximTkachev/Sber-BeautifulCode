package ru.sber.beautifulcode.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtils {

    private static final String CONTENT_PATH = "src/test/resources/";

    public static byte[] loadFileContent(String filename) throws Exception {
        try (FileInputStream inputStream = new FileInputStream(CONTENT_PATH + filename)) {
            return inputStream.readAllBytes();
        }
    }
}
