package com.leetcode.problem.reverse.string;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseSentenceImpl implements ReverseSentence {

    private static final String WORD_DELIMITER = " ";

    @Override
    public String reverse(String source) {
        String sentence = Optional.ofNullable(source).orElse("");
        return Stream.of(sentence.split(WORD_DELIMITER))
                .map(reverseWord())
                .collect(Collectors.joining(WORD_DELIMITER));
    }

    private Function<String, String> reverseWord() {
        return src -> src.chars()
                .mapToObj(c -> Character.toString((char)c))
                .reduce("", (a, b) -> b + a);
    }
}
