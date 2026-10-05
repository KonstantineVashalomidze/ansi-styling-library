package com.github.konstantinevashalomidze.ansi;

import java.util.ArrayList;
import java.util.List;

public class Style {
    private final Color foregroundColor;
    private final Color backgroundColor;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;
    private final boolean strikethrough;

    private Style(Color foregroundColor, Color backgroundColor, boolean bold,
                  boolean italic, boolean underline, boolean strikethrough) {
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.strikethrough = strikethrough;
    }

    static class Builder {
        private Color foregroundColor;
        private Color backgroundColor;
        private boolean bold;
        private boolean italic;
        private boolean underline;
        private boolean strikethrough;

        Builder foregroundColor(Color foregroundColor) {
            this.foregroundColor = foregroundColor;
            return this;
        }

        Builder backgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        Builder bold(boolean bold) {
            this.bold = bold;
            return this;
        }

        Builder italic(boolean italic) {
            this.italic = italic;
            return this;
        }

        Builder underline(boolean underline) {
            this.underline = underline;
            return this;
        }

        Builder strikethrough(boolean strikethrough) {
            this.strikethrough = strikethrough;
            return this;
        }

        Style build() {
            return new Style(foregroundColor, backgroundColor, bold, italic, underline, strikethrough);
        }
    }

    String toEscapeCode() {
        final List<String> codes = new ArrayList<>();

        if (foregroundColor != null) {
            codes.add(30 + foregroundColor.getAssociation() + "");
        }

        if (backgroundColor != null) {
            codes.add(40 + backgroundColor.getAssociation() + "");
        }

        if (bold) codes.add("1");
        if (italic) codes.add("3");
        if (underline) codes.add("4");
        if (strikethrough) codes.add("9");

        if (codes.isEmpty()) {
            return "";
        }

        return "\033[" + String.join(";", codes) + "m";
    }

    public enum Color {
        BLACK(0), RED(1), GREEN(2), YELLOW(3),
        BLUE(4), MAGENTA(5), CYAN(6), WHITE(7);

        private final int association;
        Color(int association) { this.association = association; }
        int getAssociation() { return association; }
    }
}