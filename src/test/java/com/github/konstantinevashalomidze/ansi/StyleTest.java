package com.github.konstantinevashalomidze.ansi;

import org.junit.jupiter.api.Test;

import static com.github.konstantinevashalomidze.ansi.Style.Color.BLUE;
import static com.github.konstantinevashalomidze.ansi.Style.Color.RED;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StyleTest {

    @Test
    void noAttributesReturnsEmptyString() {
        Style style = new Style.Builder().build();

        String result = style.toEscapeCode();

        assertEquals("", result);
    }

    @Test
    void singleBoldAttributeReturnsCorrectCode() {
        Style style = new Style.Builder().bold(true).build();

        String result = style.toEscapeCode();

        assertEquals("\033[1m", result);
    }

    @Test
    void boldAndItalicAttributesReturnsCorrectCode() {
        Style style = new Style.Builder().bold(true).italic(true).build();

        String result = style.toEscapeCode();

        assertEquals("\033[1;3m", result);
    }

    @Test
    void foregroundColorReturnsCorrectCode() {
        Style style = new Style.Builder().foregroundColor(RED).build();

        String result = style.toEscapeCode();

        assertEquals("\033[31m", result);
    }

    @Test
    void backgroundColorReturnsCorrectCode() {
        Style style = new Style.Builder().backgroundColor(BLUE).build();

        String result = style.toEscapeCode();

        assertEquals("\033[44m", result);
    }

    @Test
    void combinedAttributesReturnsCorrectCode() {
        Style style = new Style.Builder()
                .foregroundColor(RED)
                .backgroundColor(BLUE)
                .bold(true)
                .build();

        String result = style.toEscapeCode();

        assertEquals("\033[31;44;1m", result);
    }

}
