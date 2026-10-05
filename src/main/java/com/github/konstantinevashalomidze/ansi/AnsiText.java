package com.github.konstantinevashalomidze.ansi;

/**
 * <p>A chain of styles, to build up one call at a time. Calling {@link #apply(String)} wraps plain text in the chain's
 * <strong>ANSI</strong> codes and returns the result.</p>
 *
 * <p><em>{@code apply} throws {@link AnsiException} if the text already contains <strong>ANSI</strong> codes.</em></p>
 *
 * @author <a href="https://portfolio.kosta-server.org/">Konstantine Vashalomidze</a>
 */
public class AnsiText {
    private final Style.Builder styleBuilder = new Style.Builder();

    /**
     * Enables bold.
     * @return this chain, for further chaining.
     */
    public AnsiText bold() {
        styleBuilder.bold(true);
        return this;
    }

    /**
     * Enables italic.
     * @return this chain, for further chaining.
     */
    public AnsiText italic() {
        styleBuilder.italic(true);
        return this;
    }

    /**
     * Enables underline.
     * @return this chain, for further chaining.
     */
    public AnsiText underline() {
        styleBuilder.underline(true);
        return this;
    }

    /**
     * Enables strikethrough.
     * @return this chain, for further chaining.
     */
    public AnsiText strikethrough() {
        styleBuilder.strikethrough(true);
        return this;
    }

    /**
     * Sets the foreground color.
     * @param color the text color
     * @return {@link AnsiText} for chaining other styles
     */
    public AnsiText foreground(Style.Color color) {
        styleBuilder.foregroundColor(color);
        return this;
    }

    /**
     * Sets the background color.
     * @param color the background color
     * @return {@link AnsiText} for chaining other styles
     */
    public AnsiText background(Style.Color color) {
        styleBuilder.backgroundColor(color);
        return this;
    }

    String apply(String text, boolean shouldNotUseColor) {
        if (AnsiUtils.containsAnsi(text)) {
            throw new AnsiException("Input text contains ANSI code(s)");
        }
        if (shouldNotUseColor) {
            return text;
        }
        String styleCode = styleBuilder.build().toEscapeCode();
        String result = styleCode + text;
        if (!styleCode.isEmpty()) {
            result += "\033[0m";
        }
        return result;
    }

    /**
     * <p>Wraps plain text in the chain's <strong>ANSI</strong> codes, followed by a reset code. If <strong>NO_COLOR
     * </strong> environment
     * variable is set or output isn't going to a terminal (unless <strong>FORCE_COLOR</strong>
     * is set) the text is returned unchanged. If no style was set on the chain, the text also comes back unchanged,
     * with no reset code.</p>
     * @throws AnsiException when plain text contains <strong>ANSI</strong> codes
     */
    public String apply(String text) {
        return apply(text, TerminalSupport.shouldNotUseColor());
    }
}