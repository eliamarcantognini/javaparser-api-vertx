package view;

import dto.DTOParser;
import dto.DTOs;
import dto.ProjectDTO;
import io.vertx.core.Vertx;
import lib.ProjectAnalyzer;
import lib.async.AsyncProjectAnalyzer;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ViewListener {
    //    private final Controller controller;
    private AnalyzerGUI view;
    private ProjectDTO dto;
    private static FileWriter file;

//    public ViewListener(final Controller controller) {
//        this.controller = controller;
//    }

    public void eventPerformed(Commands code) {
        switch (code) {
            case START -> start();
            case STOP -> stop();
            case SAVE -> save();
            case ANALYZE -> startAnalyzeProject(getFilePath());
        }
    }

    private void start() {
        // controller.analyzeProject(path);
        // TODO: Trasferire nel controller
        // Decommentare per testare la GUI
        ProjectAnalyzer projectAnalyzer;
        projectAnalyzer = new AsyncProjectAnalyzer(Vertx.vertx());
        var future = projectAnalyzer.getProjectReport("src/main/java/lib");
        future.onSuccess(projectReport -> {
            var json = DTOParser.parseString(DTOs.createProjectDTO(projectReport));
            view.setText(json);
            dto = DTOParser.parseProjectDTO(json);
            view.renderTree(dto);
        });
        //
        view.setStartEnabled(false);
        view.setStopEnabled(true);
    }

    private void stop() {
        // controller.stop();
    }

    private void save() {
        try {
            file = new FileWriter(Strings.OUTPUT_PATH);
            file.write(DTOParser.parseStringToPrettyJSON(dto));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startAnalyzeProject(String path) {
        view = new AnalyzerGUI(this);
        // controller.setGUI(gui);
    }

    private String getFilePath() {
        JFileChooser chooser = new JFileChooser();
        File f = new java.io.File(".");
        chooser.setCurrentDirectory(f);
        chooser.setDialogTitle(Strings.CHOOSER);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Delete the "." at the end of the path
            String _f = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1);
            // Transfrom the absolute path in a relative path
            return chooser.getSelectedFile().getAbsolutePath().substring(_f.length());
        } else {
            getFilePath();
        }
        return "";
    }
}
