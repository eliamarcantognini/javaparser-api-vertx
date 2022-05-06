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
        switch (code) {
            case START:
//                controller.startParsing();
                break;
            case STOP:
//                controller.stopParsing();
                break;
            case FOLDER:
                getFilePath();
//                controller.setFilePath(getFilePath());
            default:
                break;
        }
    }

    private String getFilePath(){
        JFileChooser chooser = new JFileChooser();
        File f = new java.io.File(".");
        chooser.setCurrentDirectory(f);
        chooser.setDialogTitle(TITLE);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
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
