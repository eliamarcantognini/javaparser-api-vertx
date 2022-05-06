package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Simulation view
 *
 * @author aricci
 */
public class GUIView implements View {

    private JButton btnFolder;
    private JButton btnStart;
    private JButton btnStop;
    private JTextPane txtPane;

    /**
     * Creates a view of the specified size (in pixels)
     *
     * @param w width
     * @param h height
     */
    public GUIView(int w, int h) {
        VisualiserFrame frame = new VisualiserFrame(w, h);
    }

//    @Override
//    public void display(final List<Body> bodies, final double vt, final long iter, final Boundary bounds) {
//        this.frame.display(bodies, vt, iter, bounds);
//    }

    @Override
    public void setStopEnabled(final Boolean enabled) {
        this.btnStop.setEnabled(enabled);
    }

    @Override
    public void setStartEnabled(final Boolean enabled) {
        this.btnStart.setEnabled(enabled);
    }

    @Override
    public void addListener(final ViewListener listener) {
        this.btnStart.addActionListener(e -> listener.eventPerformed(Commands.START));
        this.btnStop.addActionListener(e -> listener.eventPerformed(Commands.STOP));
        this.btnFolder.addActionListener(e -> listener.eventPerformed(Commands.FOLDER));
    }

    public class VisualiserFrame extends JFrame {

        public VisualiserFrame(int w, int h) {
            setTitle("Bodies Simulation");
            setSize(w, h);
            setResizable(false);

            JPanel btnPanel = new JPanel();
            btnPanel.setLayout(new FlowLayout());
            btnFolder = new JButton("SELECT FILE");
            btnStart = new JButton("START");
            btnStop = new JButton("STOP");
            btnPanel.add(btnFolder);
            btnPanel.add(btnStart);
            btnPanel.add(btnStop);

            txtPane = new JTextPane();
            txtPane.setEditable(false);

            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(btnPanel, BorderLayout.NORTH);
            getContentPane().add(txtPane, BorderLayout.CENTER);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.setVisible(true);
        }

        public void repaintView() {
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow();
            repaint();
        }

        public void setText(final String text){
            txtPane.setText(text);
        }
    }
}
