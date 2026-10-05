package com.github.konstantinevashalomidze.ansi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import static com.github.konstantinevashalomidze.ansi.AnsiUtils.pad;

public class Box {

    private static String topBorder(int padding, int targetWidth) {
        int contentWidth = targetWidth  + 2 * padding;
        return "┌" + "─".repeat(contentWidth) + "┐";
    }

    private static String contentLine(String text, int padding, int targetWidth) {
        return "│" + " ".repeat(padding) + pad(text, targetWidth) + " ".repeat(padding) +  "│";
    }

    private static String bottomBorder(int padding, int targetWidth) {
        int contentWidth = targetWidth + 2 * padding;
        return "└" + "─".repeat(contentWidth) + "┘";
    }


    /**
     * Returns specified texts wrapped in a border, one per line. Width of the rectangle follows the longest line by
     * visible width, so styled text is measured correctly.
     * @param padding number of spaces between the text and the left and right borders
     * @param texts each text on new line to wrap in a border
     * @return the box as a multi-line string, with no trailing line break
     * @throws AnsiException when any text is null, no {@code texts} are given or padding is negative
     * @throws NullPointerException when {@code texts} itself is null
     */
    public static String draw(int padding, String... texts) {
        Objects.requireNonNull(texts);

        if (padding < 0) {
            throw new AnsiException("Padding is negative");
        }

        if (Arrays.stream(texts).anyMatch(Objects::isNull)) {
            throw new AnsiException("Drawing text shouldn't be null");
        }

        int maxWidth = AnsiUtils.visibleLength(
                Arrays.stream(texts)
                        .max(Comparator.comparing(AnsiUtils::visibleLength))
                        .orElseThrow(() -> new AnsiException("Couldn't find maxWidth string"))
        );

        StringBuilder result = new StringBuilder();
        result.append(topBorder(padding, maxWidth));
        result.append(System.lineSeparator());
        for (String text : texts) {
            result.append(contentLine(text, padding, maxWidth));
            result.append(System.lineSeparator());
        }
        result.append(bottomBorder(padding, maxWidth));
        return result.toString();
    }

}
