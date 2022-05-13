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
    private AnalyzerGUI view; // Da trasferire nel controller
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
        // controller.startAnalyze(path);
        // TODO: Trasferire nel controller
        // Decommentare per testare la GUI
        var projectAnalyzer = new AsyncProjectAnalyzer(Vertx.vertx());
        var future = projectAnalyzer.getProjectReport("src/main/java/lib");
        future.onSuccess(projectReport -> {
            var json = DTOParser.parseString(DTOs.createProjectDTO(projectReport));
            view.setText(json);
            dto = DTOParser.parseProjectDTO(json);
            view.renderTree(dto);
        });
        //
        view.setStartEnabled(false); // Nel controller
        view.setStopEnabled(true); // Nel controller
    }

    private void stop() {
        // controller.stopAnalysis();
    }

    private void save() {
        // controller.saveResult()
        // TODO: Trasferire nel controller il salvataggio del file
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
        if (path.isBlank())
            InfoDialog.showDialog(Strings.VALID_PATH, Strings.PATH_ERROR, JOptionPane.ERROR_MESSAGE);
        // controller.startAnalyzeProject(path)
    }

    private String getFilePath() {
        JFileChooser chooser = new JFileChooser();
        File f = new java.io.File(".");
        chooser.setCurrentDirectory(f);
        chooser.setDialogTitle(Strings.CHOOSER);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        var choice = chooser.showOpenDialog(null);
        if ( choice == JFileChooser.APPROVE_OPTION) {
            // Delete the "." at the end of the path
            var _f = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1);
            // Transform the absolute path in usable path
            return chooser.getSelectedFile().getAbsolutePath().substring(_f.length());
        } else if (choice == JFileChooser.CANCEL_OPTION) {
            // Add
        } else {
            InfoDialog.showDialog(Strings.VALID_PATH, Strings.PATH_ERROR, JOptionPane.ERROR_MESSAGE);
            getFilePath();
        }
        return "";
    }
}
