package com.github.konstantinevashalomidze.ansi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoxTest {

    private static String platformLines(String textBlock) {
        return textBlock.replace("\n", System.lineSeparator());
    }

    @Test
    void singleLineIsWrappedInBorder() {
        String expected = platformLines("""
                ┌────┐
                │ Hi │
                └────┘""");

        assertEquals(expected, Box.draw(1, "Hi"));
    }

    @Test
    void shorterLinesArePaddedToLongestLine() {
        String expected = platformLines("""
                ┌────────┐
                │ Hi1    │
                │ Hi1234 │
                │ Hi12   │
                └────────┘""");

        assertEquals(expected, Box.draw(1, "Hi1", "Hi1234", "Hi12"));
    }

    @Test
    void styledTextIsSizedByVisibleWidth() {
        String expected = platformLines("""
                ┌────┐
                │ \033[1mA\033[0m  │
                │ Hi │
                └────┘""");

        assertEquals(expected, Box.draw(1, "\033[1mA\033[0m", "Hi"));
    }

    @Test
    void zeroPaddingHugsText() {
        String expected = platformLines("""
                ┌──┐
                │Hi│
                └──┘""");

        assertEquals(expected, Box.draw(0, "Hi"));
    }

    @Test
    void drawWithoutTextsThrows() {
        assertThrows(AnsiException.class, () -> Box.draw(1));
    }

    @Test
    void nullTextThrows() {
        assertThrows(AnsiException.class, () -> Box.draw(1, null, "Hi"));
    }

    @Test
    void nullTextsThrows() {
        assertThrows(NullPointerException.class, () -> Box.draw(1, (String[]) null));
    }

    @Test
    void negativePaddingThrows() {
        assertThrows(AnsiException.class, () -> Box.draw(-1, "Hi"));
    }

}