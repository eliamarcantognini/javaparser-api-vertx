package view;

import dto.ClassInterfaceDTO;
import dto.FieldDTO;
import dto.MethodDTO;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

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
            setTitle("JavaParser GUI");
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

//        public void renderTree(ClassInterfaceDTO dto) {
//            DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
//            root.add(new DefaultMutableTreeNode(dto.name()));
//            root.add(new DefaultMutableTreeNode(dto.path()));
//            DefaultMutableTreeNode methodsNode = new DefaultMutableTreeNode("Methods");
//            for (MethodDTO m : dto.methods()) {
//                if (m.modifiers() != null)
//                    methodsNode.add(new DefaultMutableTreeNode(m.toString().substring(10, m.toString().length() - 1)));
//                else
//                    methodsNode.add(new DefaultMutableTreeNode(m.toString().substring(10, m.toString().length() - 17)));
//            }
//            root.add(methodsNode);
//            if (dto.fields() != null) {
//                DefaultMutableTreeNode fieldsNode = new DefaultMutableTreeNode("Fields");
//                for (FieldDTO f : dto.fields()) {
//                    fieldsNode.add(new DefaultMutableTreeNode(f.toString().substring(9, f.toString().length() - 1)));
//                }
//                root.add(fieldsNode);
//            }
//            JTree tree = new JTree(new DefaultTreeModel(root));
//            getContentPane().add(tree, BorderLayout.CENTER);
//        }

    }

    public void setDTO(ClassInterfaceDTO dto) {
        Trees.renderClassOrInterfaceTree(dto, frame);
//        frame.renderTree(dto);
    }

    public void setText(String txt) {
        frame.setText(txt);
    }
}
