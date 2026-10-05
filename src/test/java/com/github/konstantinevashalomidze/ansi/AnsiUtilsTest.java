package com.github.konstantinevashalomidze.ansi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnsiUtilsTest {

    @Test
    void containsAnsiReturnsTrueWhenTextContainsAnsi() {
        assertTrue(AnsiUtils.containsAnsi("\033[1mHi\033[0m"));
    }

    @Test
    void containsAnsiReturnsFalseWhenTextDoesNotContainAnsi() {
        assertFalse(AnsiUtils.containsAnsi("Hi"));
    }

    @Test
    void visibleLengthReturnsPlainTextLength() {
        assertEquals(2, AnsiUtils.visibleLength("Hi"));
    }

    @Test
    void visibleLengthReturnsPlainTextLengthWhenTextContainsAnsi() {
        assertEquals(2, AnsiUtils.visibleLength("\033[1mHi\033[0m"));
    }

    @Test
    void padNormalCase() {
        assertEquals("Hi   ", AnsiUtils.pad("Hi", 5));
    }

    @Test
    void padStyledCase() {
        assertEquals("\033[1mHi\033[0m        ", AnsiUtils.pad("\033[1mHi\033[0m", 10));
    }

    @Test
    void padOverflowCase() {
        assertThrows(AnsiException.class, () -> AnsiUtils.pad("Hi", 1));
    }

    @Test
    void visibleLengthReturnsPlainTextLengthWhenTextContainsMultiParamAnsi() {
        assertEquals(2, AnsiUtils.visibleLength("\033[1;3mHi\033[0m"));
    }

}
