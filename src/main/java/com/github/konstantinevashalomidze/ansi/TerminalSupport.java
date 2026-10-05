package com.github.konstantinevashalomidze.ansi;

class TerminalSupport {

    private static boolean isColorDisabled() {
        return System.getenv("NO_COLOR") != null;
    }

    private static boolean isColorForced() {
        return System.getenv("FORCE_COLOR") != null;
    }

    static boolean shouldNotUseColor() {
        if (isColorDisabled()) {
            return true;
        } else if (isColorForced()) {
            return false;
        } else {
            return System.console() == null;
        }
    }

}
