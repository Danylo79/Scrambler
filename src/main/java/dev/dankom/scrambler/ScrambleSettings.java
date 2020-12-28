package dev.dankom.scrambler;

public class ScrambleSettings {
    private static boolean showLogs = true;
    private static boolean showTestLogs = true;

    public static boolean showLogs() {
        return showLogs;
    }

    public static void setShowLogs(boolean b) {
        showLogs = b;
    }

    public static boolean showTestLogs() {
        return showTestLogs;
    }

    public static void setShowTestLogs(boolean showTestLogs) {
        ScrambleSettings.showTestLogs = showTestLogs;
    }
}
