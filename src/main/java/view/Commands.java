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
     * Command to select getProjectReport API
     */
    PROJECT,
    /**
     * Command to select getPackageReport API
     */
    PACKAGE,
    /**
     * Command to select getClassReport API
     */
    CLASS,
    /**
     * Command to select getInterfaceReport API
     */
    INTERFACE,
    /**
     * Command to select analyzeProject API
     */
    ANALYZE,
    /**
     * Command to open filesystem view.
     */
    FOLDER
}
