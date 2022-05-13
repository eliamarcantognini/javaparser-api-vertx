package view.GUI;

import javax.swing.*;

public class InfoDialog {

    public static void showDialog(String text, String title, int messageType){
        JOptionPane.showMessageDialog(null, text, title, messageType);
    }
}
