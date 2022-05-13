package view;

import controller.AnalysisController;
import view.GUI.PathChooserGUI;

public class ViewListener {

    private final AnalysisController analysisController;
    private View analyzerView;

    public ViewListener(final AnalysisController analysisController) {
        this.analysisController = analysisController;
    }

    public void eventPerformed(Commands code) {
        switch (code) {
            case SELECT_PROJECT -> this.initAnalysis();
            case START_ANALYSIS -> this.start();
            case STOP_ANALYSIS -> this.stop();
            case SAVE_REPORT_INSIDE_FILE -> this.saveProjectReport();
        }
    }

    public void setViewToRunForAnalysis(final View view) {
        this.analysisController.setReportAnalysisView(view);
        this.analyzerView = view;
    }

    private void initAnalysis() {
        var p = PathChooserGUI.getFolderPath();
        if (p.isBlank()) {
            analyzerView.showError(Strings.VALID_PATH, Strings.PATH_ERROR);
        } else {
            this.analysisController.setPathProjectToAnalyze(p);
            this.analyzerView.launch();
        }
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

}
