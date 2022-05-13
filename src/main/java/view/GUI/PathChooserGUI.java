package view.GUI;

import view.Strings;

import javax.swing.*;
import java.io.File;

public class PathChooserGUI {

    public static String getFolderPath() {
        JFileChooser chooser = new JFileChooser();
        File f = new java.io.File(".");
        chooser.setCurrentDirectory(f);
        chooser.setDialogTitle(Strings.CHOOSER);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        var choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            // Delete the "." at the end of the path
            var _f = f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 1);
            // Transform the absolute path in a relative path
            return chooser.getSelectedFile().getAbsolutePath().substring(_f.length());
        }
        return "";
    }
}
