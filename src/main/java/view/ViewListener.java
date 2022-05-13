package view;

import controller.AnalysisController;
import javax.swing.*;
import java.io.File;

public class ViewListener {

    private final AnalysisController analysisController;
    private AnalyzerGUI analyzerGUIToLaunchForAnalysis;

    public ViewListener(final AnalysisController analysisController) {
        this.analysisController = analysisController;
    }

    public void eventPerformed(Commands code) {
        switch (code) {
            case ANALYZE -> this.initAnalysis();
            case START -> this.start();
            case STOP -> this.stop();
            case SAVE -> this.saveProjectReport();
        }
    }

    public void setViewToRunForAnalysis(final AnalyzerGUI analyzerGUI) {
        this.analysisController.setReportAnalysisView(analyzerGUI);
        this.analyzerGUIToLaunchForAnalysis = analyzerGUI;
    }

    private void initAnalysis(){
        this.analysisController.setPathProjectToAnalyze(this.getFilePath());
        this.analyzerGUIToLaunchForAnalysis.startAnalyzerGUI();
    }

    private void start() {
        this.analysisController.startAnalysisProject();
    }

    private void stop() {
        this.analysisController.stopAnalysisProject();
    }

    private void saveProjectReport() {
        this.analysisController.saveProjectReportToFile();
    }

    private String getFilePath() {
        JFileChooser chooser = new JFileChooser();
        File f = new java.io.File(".");
        chooser.setCurrentDirectory(f);
        chooser.setDialogTitle(Strings.CHOOSER);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Delete the "." at the end of the path
            var _f = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1);
            // Transfrom the absolute path in a relative path
            return chooser.getSelectedFile().getAbsolutePath().substring(_f.length());
        } else {
            getFilePath();
        }
        return "";
    }

}
