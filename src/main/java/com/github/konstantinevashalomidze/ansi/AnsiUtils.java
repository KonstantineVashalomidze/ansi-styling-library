package com.github.konstantinevashalomidze.ansi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AnsiUtils {
    private static final Pattern pattern = Pattern.compile("\033\\[\\d+(;\\d+)*m");

    static int visibleLength(String text) {
        return matcher(text).replaceAll("").length();
    }

    private static Matcher matcher(String text) {
        return pattern.matcher(text);
    }

    static boolean containsAnsi(String text) {
        return matcher(text).find();
    }

    static String pad(String text, int targetWidth) {
        int visibleTextLength = visibleLength(text);
        int fillCount = targetWidth - visibleTextLength;
        if (fillCount < 0) {
            throw new AnsiException("text is %d characters wide but target width is %d".formatted(visibleTextLength, targetWidth));
        }
        return text + " ".repeat(fillCount);
    }

}
