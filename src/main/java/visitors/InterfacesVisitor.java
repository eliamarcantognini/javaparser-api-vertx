package visitors;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import reports.InterfaceReport;

import java.util.List;

public class InterfacesVisitor extends VoidVisitorAdapter<InterfaceReport> {

    public void visit(ClassOrInterfaceDeclaration cd, InterfaceReport collector) {
        super.visit(cd, collector);
        //System.out.println(cd.getNameAsString());
        collector.setInterfaceName(cd.getNameAsString());
        collector.setInterfaceFullPath(cd.getFullyQualifiedName().orElse("Package not found!"));
    }

    public void visit(MethodDeclaration md, InterfaceReport collector) {
        super.visit(md, collector);
        collector.addMethodsName(md.getNameAsString());
    }

//    public void visit(PackageDeclaration pd, InterfaceReport collector) {
//        super.visit(pd, collector);
//        collector.setInterfaceFullPath(pd.getNameAsString());
//    }
}
