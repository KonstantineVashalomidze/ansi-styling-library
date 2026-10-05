package com.github.konstantinevashalomidze.ansi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnsiTextTest {
    @Test
    void boldStyleWrapsTextWithCorrectEscapeCode() {
        AnsiText ansiText = new AnsiText();

        String result = ansiText.bold().apply("TEST", false);

        assertEquals("\033[1mTEST\033[0m", result);
    }

    @Test
    void applyThrowsWhenContainsAnsiEscapeCode() {
        AnsiText ansiText = new AnsiText();

        assertThrows(AnsiException.class, () -> {
            ansiText.apply("\033[1mHi\033[0m", false);
        });
    }


    @Test
    void applyThrowsWhenContainsAnsiEscapeCodeEvenIfColorIsDisabled() {
        AnsiText ansiText = new AnsiText();

        assertThrows(AnsiException.class, () -> {
            ansiText.apply("\033[1mHi\033[0m", true);
        });
    }


    @Test
    void applyReturnsPlainTextIfColorIsDisabled() {
        AnsiText ansiText = new AnsiText();

        String result = ansiText.bold().apply("Hi", true);

        assertEquals("Hi", result);
    }

    @Test
    void applyReturnsInputWhenNoStyleWasApplied() {
        AnsiText ansiText = new AnsiText();

        String result = ansiText.apply("Hi", false);

        assertEquals("Hi", result);
    }



}
