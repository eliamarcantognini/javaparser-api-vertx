package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ViewListener{
//    private final Controller controller;

    private final static String TITLE = "SELECT JAVA FILE";

//    public ViewListener(final Controller controller) {
//        this.controller = controller;
//    }

    public void eventPerformed(Commands code) {
        switch (code) {
            case START:
//                controller.startParsing();
                break;
            case STOP:
//                controller.stopParsing();
                break;
            case FOLDER:
                String path = getFilePath();
            default:
                break;
        }
    }

    private String getFilePath(){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(TITLE);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter(new FileNameExtensionFilter("Java File", "java"));
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
            return chooser.getSelectedFile().toString();
        }
        else {
            System.out.println("No Selection ");
        }
        return "";
    }
}
