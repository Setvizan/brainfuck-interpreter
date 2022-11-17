package io.github.setvizan.brainfuck;

import java.util.regex.Pattern;

// this adds up to 4ms before execution to save maybe 0.4ms
public class Optimizer {
    private static final Pattern INCREMENT_DECREMENT_PATTERN = Pattern.compile("\\+-|-\\+");

    private static final Pattern FORWARD_BACKWARD_PATTERN = Pattern.compile("><|<>");

    private static final Pattern REMOVE_USELESS_CHARACTERS = Pattern.compile("[^<>\\.,\\[\\]\\+-]");

    public static String apply(String in) {
        String optimized = in;
        optimized = removeUselessCharacters(optimized);
        optimized = removeForwardBackwards(optimized);
        optimized = removeIncrementDecrements(optimized);
        return optimized;
    }

    private static String removeIncrementDecrements(String input) {
        return INCREMENT_DECREMENT_PATTERN.matcher(input).replaceAll("");
    }

    private static String removeForwardBackwards(String input) {
        return FORWARD_BACKWARD_PATTERN.matcher(input).replaceAll("");
    }

    private static String removeUselessCharacters(String input) {
        return REMOVE_USELESS_CHARACTERS.matcher(input).replaceAll("");
    }
}
