package view.GUI;

import view.utils.Commands;
import view.utils.Strings;
import view.ViewListener;

import javax.swing.*;
import java.awt.*;

public class StartGUI extends JFrame {

    public StartGUI(ViewListener listener){
        var w = Toolkit.getDefaultToolkit().getScreenSize().width / 5;
        var h = Toolkit.getDefaultToolkit().getScreenSize().height / 5;
        setTitle(Strings.TITLE);
        setSize(w, h);
        setResizable(true);
        var btn = new JButton(Strings.SELECT_PROJECT);
        btn.addActionListener(e -> listener.eventPerformed(Commands.SELECT_PROJECT));
        add(btn);
        setVisible(true);
    }

}
