package view;

import dto.*;

import javax.swing.tree.DefaultMutableTreeNode;

public final class Trees {

    private Trees(){}
    public static DefaultMutableTreeNode createClassOrInterfaceTreeNode(ClassInterfaceDTO dto){
        var root = new DefaultMutableTreeNode("");
        root.add(new DefaultMutableTreeNode(dto.name()));
        root.add(new DefaultMutableTreeNode(dto.path()));
        var methodsNode = new DefaultMutableTreeNode("Methods");
        for (MethodDTO m : dto.methods()) {
            if (m.modifiers() != null)
                methodsNode.add(new DefaultMutableTreeNode(m.toString().substring(10, m.toString().length() - 1)));
            else
                methodsNode.add(new DefaultMutableTreeNode(m.toString().substring(10, m.toString().length() - 17)));
        }
        root.add(methodsNode);
        if (dto.fields() != null) {
            var fieldsNode = new DefaultMutableTreeNode("Fields");
            for (FieldDTO f : dto.fields()) {
                fieldsNode.add(new DefaultMutableTreeNode(f.toString().substring(9, f.toString().length() - 1)));
            }
            root.add(fieldsNode);
        }
        return root;
    }

    public static DefaultMutableTreeNode createPackageTreeNode(PackageDTO dto){
        var root = new DefaultMutableTreeNode("");
        root.add(new DefaultMutableTreeNode(dto.name()));
        root.add(new DefaultMutableTreeNode(dto.path()));
        var interfaces = new DefaultMutableTreeNode("interfaces");
        for (ClassInterfaceDTO c : dto.interfaces())
            interfaces.add(createClassOrInterfaceTreeNode(c));
        root.add(interfaces);
        var classes = new DefaultMutableTreeNode("classes");
        for (ClassInterfaceDTO c : dto.classes())
            classes.add(createClassOrInterfaceTreeNode(c));
        root.add(classes);

        return root;
    }

    public static DefaultMutableTreeNode createProjectTreeNode(ProjectDTO dto){
        var root = new DefaultMutableTreeNode("");
        root.add(new DefaultMutableTreeNode(dto.mainClass().name()));
        var packages = new DefaultMutableTreeNode("packages");
        for (PackageDTO p : dto.packages())
            packages.add(createPackageTreeNode(p));

        return root;
    }
}
