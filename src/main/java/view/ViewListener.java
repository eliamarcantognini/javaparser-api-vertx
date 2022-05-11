package view;

import javax.swing.*;
import java.io.File;

public class ViewListener {
//    private final Controller controller;

    private final static String TITLE = "SELECT JAVA FILE";

//    public ViewListener(final Controller controller) {
//        this.controller = controller;
//    }

    public void eventPerformed(Commands code) {
        switch (code) {
            case START -> start();
            case STOP -> stop();
            case ANALYZE -> startAnalyzeProject(getFilePath());
        }
    }

    private void start() {

    }

    private void stop() {

    }

    private void startAnalyzeProject(String path) {
        var gui = new GUIView(800, 800);
        // controller.setGUI(gui);
        // controller.analyzeProject(path);

    }

    private String getFilePath() {
        JFileChooser chooser = new JFileChooser();
        File f = new java.io.File(".");
        chooser.setCurrentDirectory(f);
        chooser.setDialogTitle(TITLE);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Delete the "." at the end of the path
            String _f = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1);
            // Transfrom the absolute path in a relative path
            return chooser.getSelectedFile().getAbsolutePath().substring(_f.length());
        }
        return "";
    }
}
