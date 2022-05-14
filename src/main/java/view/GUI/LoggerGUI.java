package view.GUI;

import view.utils.Strings;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

/**
 * GUI used to display the log sent by the library.
 */
public class LoggerGUI {

    private static final int HEIGHT_DIVISOR = 3;
    private static final int WIDTH_DIVISOR = 2;

    private JTextPane txtPane;

    private VisualiserFrame frame;

    /**
     * Constructor of the GUI
     *
     */
    public LoggerGUI() {

    }

    /**
     * Launch GUI
     *
     * @see VisualiserFrame
     */
    public void launch(){
        var h = Toolkit.getDefaultToolkit().getScreenSize().height / HEIGHT_DIVISOR;
        var w = Toolkit.getDefaultToolkit().getScreenSize().width / WIDTH_DIVISOR;
        frame = new VisualiserFrame(w, h);
    }

    /**
     * Print in view text passed as parameter
     *
     * @param textToPrint text to print in the view
     */
    public void printText(String textToPrint) {
        frame.addText(textToPrint);
    }

    private class VisualiserFrame extends JFrame {

        public VisualiserFrame(int w, int h) {
            getContentPane().setLayout(new BorderLayout());
            setTitle(Strings.LOGGER_TITLE);
            setSize(w, h);
            setResizable(true);

            txtPane = new JTextPane();
            txtPane.setEditable(false);
            var txtScrollPane = new JScrollPane(txtPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            DefaultCaret caret = (DefaultCaret) txtPane.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

            this.getContentPane().add(txtScrollPane, BorderLayout.CENTER);

            this.setVisible(true);
        }

        void setText(final String text) {
            txtPane.setText(text);
        }

        void addText(final String text) {
            txtPane.setText(txtPane.getText() + "\n" + text);
        }

    }
}
