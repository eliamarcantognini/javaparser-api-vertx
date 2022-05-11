package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ViewListener{
//    private final Controller controller;

    private final static String TITLE = "SELECT JAVA FILE";

//    public ViewListener(final Controller controller) {
//        this.controller = controller;
//    }

    public void eventPerformed(Commands code) {
        switch (code){
            case START -> start();
            case STOP -> stop();
            case PROJECT -> startGetProjectReport(getFilePath(JFileChooser.DIRECTORIES_ONLY));
            case PACKAGE -> startGetPackageReport(getFilePath(JFileChooser.DIRECTORIES_ONLY));
            case CLASS -> startGetClassReport(getFilePath(JFileChooser.FILES_ONLY));
            case INTERFACE -> startGetInterfaceReport(getFilePath(JFileChooser.FILES_ONLY));
            case ANALYZE -> startAnalyzeProject(getFilePath(JFileChooser.DIRECTORIES_ONLY));
        }
    }

    private void start(){

    }

    private void stop(){

    }

    private void startGetProjectReport(String path){

    }
    private void startGetPackageReport(String path){

    }
    private void startGetClassReport(String path){

    }
    private void startGetInterfaceReport(String path){

    }

    private void startAnalyzeProject(String path){

    }

    private String getFilePath(int filter){
        JFileChooser chooser = new JFileChooser();
        File f = new java.io.File(".");
        chooser.setCurrentDirectory(f);
        chooser.setDialogTitle(TITLE);
        chooser.setFileSelectionMode(filter);
        if (filter == JFileChooser.FILES_ONLY)
            chooser.setFileFilter(new FileNameExtensionFilter("Java File", "java"));
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Delete the "." at the end of the path
            String _f = f.getAbsolutePath().substring(0, f.getAbsolutePath().length()-1);
            // Transfrom the absolute path in a relative path
            return chooser.getSelectedFile().getAbsolutePath().substring(_f.length());
        }
        return "";
    }
}
