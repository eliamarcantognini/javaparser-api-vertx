package view;

/**
 * Commands use to manage analyzing of a project.
 *
 * @see ViewListener
 * @see AnalyzerGUI
 */
public enum Commands {
    /**
     * Command to start analyze project.
     */
    START,
    /**
     * Command to stop analyze project.
     */
    STOP,
    /**
     * Command to select analyzeProject API
     */
    ANALYZE,
    /**
     * Command to save output JSON
     */
    SAVE,
}
