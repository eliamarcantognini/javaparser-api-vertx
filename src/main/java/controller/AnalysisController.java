package controller;

import utils.dto.DTOParser;
import utils.dto.DTOs;
import utils.dto.ProjectDTO;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import utils.Printer;
import lib.ProjectAnalyzer;
import lib.async.AsyncProjectAnalyzer;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;
import view.View;

import java.io.FileWriter;
import java.io.IOException;

public class AnalysisController {

    private final static String OUTPUT_PATH = "./output.json";
    private final static String VERTX_CHANNEL_TOPIC = "new_find";

//    private final StartGUI startGUI;
    private View view;
    private final ProjectAnalyzer projectAnalyzer;
    private final Vertx vertx;
    private ProjectDTO dto;
    private String pathProjectToAnalyze;

//    public AnalysisController(final StartGUI startGUI){
    public AnalysisController(){
//        this.startGUI = startGUI;
        this.vertx = Vertx.vertx();
        this.projectAnalyzer = new AsyncProjectAnalyzer(this.vertx);
    }

    public void setView(View view){
        this.view = view;
    }

    public void setPathProjectToAnalyze(final String pathProjectToAnalyze){
        this.pathProjectToAnalyze = pathProjectToAnalyze;
    }

    public void startAnalysisProject(){
        this.view.setStartEnabled(false);
        this.view.setStopEnabled(true);
        this.view.setSaveEnabled(true);
        // this.initializeEventBus();
        // this.projectAnalyzer.analyzeProject(this.pathProjectToAnalyze, AnalysisController.VERTX_CHANNEL_TOPIC);
//        this.testProjectReportWithoutBus(this.pathProjectToAnalyze);
        //this.testPackageReportWithoutBus(this.pathProjectToAnalyze);
        //this.testClassReportWithoutBus(this.pathProjectToAnalyze);
        //this.testInterfaceReportWithoutBus(this.pathProjectToAnalyze);
        projectAnalyzer.analyzeProject(this.pathProjectToAnalyze, "default");
        vertx.eventBus().consumer("default", m -> {
            if (m.body().toString().contains(">>proj<<")) {
                    view.renderTree(DTOParser.parseProjectDTO(m.body().toString().substring(8)));
            }
        });
    }

    public void stopAnalysisProject(){
        this.view.setStopEnabled(false);
        this.vertx.eventBus()
                .publish(AnalysisController.VERTX_CHANNEL_TOPIC,
                        AsyncProjectAnalyzer.STOP_ANALYZING_PROJECT);
    }

    // TODO: Could be useful have this method public? If yes, it can be called with parameters
    private void initializeEventBus(){
        this.vertx.eventBus()
                .localConsumer(AnalysisController.VERTX_CHANNEL_TOPIC, message -> this.view.printText("" + message.body()));
        // TODO: Decide what effectively arrives in the bus and set view relatively
    }

    // TODO: In Javadoc say where it saved or allow to specify file with parameter
    public void saveProjectReportToFile(){
        try {
            var writer = new FileWriter(AnalysisController.OUTPUT_PATH);
            writer.write(DTOParser.parseStringToPrettyJSON(dto));
            writer.flush();
            writer.close();
        } catch (IOException e){
            // TODO: Decide where print this
            System.out.println(e.getMessage());
            //this.reportAnalysisView.showError(e.getStackTrace());
        }

    }

    // TODO: Delete test before upload to Virtuale
    private void testProjectReportWithoutBus(final String pathToAnalyze) {
        Future<ProjectReport> future = projectAnalyzer.getProjectReport(pathToAnalyze);
        future.onSuccess(projectReport -> {
            Printer.printMessage("Future end successfully");
            var json = DTOParser.parseString(DTOs.createProjectDTO(projectReport));
            this.view.printText(json);
            this.dto = DTOParser.parseProjectDTO(json);
            this.view.renderTree(this.dto);
        });
        future.onFailure(failure -> {
           Printer.printMessage("Future fail");
           Printer.printMessage(failure.toString());
        });
    }

    private void testPackageReportWithoutBus(final String pathToAnalyze) {
        Future<PackageReport> future = projectAnalyzer.getPackageReport(pathToAnalyze);
        future.onSuccess(packageReport -> {
            var json = DTOParser.parseString(DTOs.createPackageDTO(packageReport));
            this.view.printText(json);
            // this.dto = DTOParser.parseProjectDTO(json);
            this.view.renderTree(DTOParser.parsePackageDTO(json));
        });
    }

    private void testInterfaceReportWithoutBus(final String pathToAnalyze) {
        Future<InterfaceReport> future = projectAnalyzer.getInterfaceReport(pathToAnalyze);
        future.onSuccess(interfaceReport -> {
            var json = DTOParser.parseString(DTOs.createInterfaceDTO(interfaceReport));
            this.view.printText(json);
            //this.dto = DTOParser.parseClassInterfaceDTO(json);
            this.view.renderTree(DTOParser.parseClassInterfaceDTO(json));
        });
    }

    private void testClassReportWithoutBus(final String pathToAnalyze) {
        Future<ClassReport> future = projectAnalyzer.getClassReport(pathToAnalyze);
        future.onSuccess(classReport -> {
            var json = DTOParser.parseString(DTOs.createClassDTO(classReport));
            this.view.printText(json);
            //this.dto = DTOParser.parseClassInterfaceDTO(json);
            this.view.renderTree(DTOParser.parseClassInterfaceDTO(json));
        });
    }
}
