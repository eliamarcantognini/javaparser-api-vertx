import controller.AnalysisController;
import view.AnalyzerGUI;
import view.StartGUI;
import view.ViewListener;

public class App {
    public static void main(String[] args) {
        AnalysisController analysisController = new AnalysisController();
        ViewListener viewListener = new ViewListener(analysisController);
        viewListener.setViewToRunForAnalysis(new AnalyzerGUI(viewListener));
        new StartGUI(viewListener);
        // TODO: Can be done in one line
    }
}
