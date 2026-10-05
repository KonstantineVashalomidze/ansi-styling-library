package com.github.konstantinevashalomidze.ansi;

/**
 * <p>Entry point for styling your terminal UI with different options such as bold, italic and underline.</p>
 *
 * <pre>{@code
 * // Returns the text wrapped in bold and red ANSI codes
 * Ansi.foreground(Style.Color.RED).bold().apply("Error");
 * }</pre>
 *
 * <p><em>Output is plain text when <strong>NO_COLOR</strong> environment variable is set or when output is not going to
 * a terminal (unless <strong>FORCE_COLOR</strong> is set).</em></p>
 *
 * @author <a href="https://portfolio.kosta-server.org/">Konstantine Vashalomidze</a>
 */
public class Ansi {

    /**
     * Starts a new style chain with bold enabled.
     * @return {@link AnsiText} for chaining other styles
     */
    public static AnsiText bold() {
        return new AnsiText().bold();
    }

    /**
     * Starts a new style chain with italic enabled.
     * @return {@link AnsiText} for chaining other styles
     */
    public static AnsiText italic() {
        return new AnsiText().italic();
    }

    /**
     * Starts a new style chain with underline enabled.
     * @return {@link AnsiText} for chaining other styles
     */
    public static AnsiText underline() {
        return new AnsiText().underline();
    }

    /**
     * Starts a new style chain with strikethrough enabled.
     * @return {@link AnsiText} for chaining other styles
     */
    public static AnsiText strikethrough() {
        return new AnsiText().strikethrough();
    }

    /**
     * Starts a new style chain with the given foreground color.
     * @param color the text color
     * @return {@link AnsiText} for chaining other styles
     */
    public static AnsiText foreground(Style.Color color) {
        return new AnsiText().foreground(color);
    }

    /**
     * Starts a new style chain with the given background color.
     * @param color the background color
     * @return {@link AnsiText} for chaining other styles
     */
    public static AnsiText background(Style.Color color) {
        return new AnsiText().background(color);
    }
}