package com.dyvak.service;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

@Component
public class LinkGenerator {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private int STRING_LENGTH = 6;

    public String generate() {
        return generateString(ALPHABET, STRING_LENGTH);
    }

    private String generateString(String alphabet, int length) {
        return generateString(alphabet, length, new SecureRandom()::nextInt);
    }

    private String generateString(String source, int length, IntFunction<Integer> nextInt) {
        StringBuilder sb = new StringBuilder();
        IntStream.generate(source::length)
                .boxed()
                .limit(length)
                .map(nextInt::apply)
                .map(source::charAt)
                .forEach(sb::append);
        return sb.toString();
    }

    public String generate(String scheme, String serverName) {
        String generatedString = generate();
        StringBuilder stringBuilder = new StringBuilder(scheme);
        stringBuilder = stringBuilder
                .append("://")
                .append(serverName)
                .append("/")
                .append(generatedString);
        return stringBuilder.toString();
    }
}
