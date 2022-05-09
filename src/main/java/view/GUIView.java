package view;

import dto.ClassInterfaceDTO;
import dto.PackageDTO;
import dto.ProjectDTO;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;


public class GUIView implements View {

    private JButton btnFolder;
    private JButton btnStart;
    private JButton btnStop;
    private JTextPane txtPane;

    VisualiserFrame frame;

    /**
     * Creates a view of the specified size (in pixels)
     *
     * @param w width
     * @param h height
     */
    public GUIView(int w, int h) {
        frame = new VisualiserFrame(w, h);
    }

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


    public <T> void renderTree(T dto) {
        var node = new DefaultMutableTreeNode();
        if (dto instanceof ProjectDTO) node = Trees.createProjectTreeNode((ProjectDTO) dto);
        else if (dto instanceof PackageDTO) node = Trees.createPackageTreeNode((PackageDTO) dto);
        else if (dto instanceof ClassInterfaceDTO) node = Trees.createClassOrInterfaceTreeNode((ClassInterfaceDTO) dto);
        JTree tree = new JTree(new DefaultTreeModel(node));
        frame.getContentPane().add(tree, BorderLayout.CENTER);
    }

    public void setText(String txt) {
        frame.setText(txt);
    }

    public class VisualiserFrame extends JFrame {

        public VisualiserFrame(int w, int h) {
            setTitle("JavaParser GUI");
            setSize(w, h);
            setResizable(true);

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
            getContentPane().add(txtPane, BorderLayout.SOUTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.setVisible(true);
        }

        public void repaintView() {
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            requestFocusInWindow();
            repaint();
        }

        public void setText(final String text) {
            txtPane.setText(text);
        }

    }

}
