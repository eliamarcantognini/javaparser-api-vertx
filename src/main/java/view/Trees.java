package view;

import dto.ClassInterfaceDTO;
import dto.FieldDTO;
import dto.MethodDTO;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public final class Trees {
    public static void renderClassOrInterfaceTree(ClassInterfaceDTO dto, GUIView.VisualiserFrame frame){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
        root.add(new DefaultMutableTreeNode(dto.name()));
        root.add(new DefaultMutableTreeNode(dto.path()));
        DefaultMutableTreeNode methodsNode = new DefaultMutableTreeNode("Methods");
        for (MethodDTO m : dto.methods()) {
            if (m.modifiers() != null)
                methodsNode.add(new DefaultMutableTreeNode(m.toString().substring(10, m.toString().length() - 1)));
            else
                methodsNode.add(new DefaultMutableTreeNode(m.toString().substring(10, m.toString().length() - 17)));
        }
        root.add(methodsNode);
        if (dto.fields() != null) {
            DefaultMutableTreeNode fieldsNode = new DefaultMutableTreeNode("Fields");
            for (FieldDTO f : dto.fields()) {
                fieldsNode.add(new DefaultMutableTreeNode(f.toString().substring(9, f.toString().length() - 1)));
            }
            root.add(fieldsNode);
        }
        JTree tree = new JTree(new DefaultTreeModel(root));
        frame.getContentPane().add(tree, BorderLayout.CENTER);
    }
}
