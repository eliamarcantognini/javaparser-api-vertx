package lib.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import lib.Logger;
import lib.reports.info.InfoBuilder;
import lib.reports.info.interfaces.MethodInfo;
import lib.reports.interfaces.InterfaceReport;

public class FileVisitor<T extends InterfaceReport> extends VoidVisitorAdapter<T> {

    private final boolean attachModifiers;

    protected Logger logger;

    public FileVisitor(boolean attachModifiers, Logger logger) {
        this.logger = logger;
        this.attachModifiers = attachModifiers;
    }

    public void visit(ClassOrInterfaceDeclaration cd, T collector) {
        super.visit(cd, collector);
        collector.setName(cd.getNameAsString());
        collector.setFullPath(cd.getFullyQualifiedName().orElse("Fully class name not found"));
    }

    public void visit(MethodDeclaration md, T collector) {
        super.visit(md, collector);
        buildMethodInfo(md, collector);
    }

    private void buildMethodInfo(MethodDeclaration md, T collector) {
        InfoBuilder builder = new InfoBuilder()
                .report(collector)
                .name(md.getNameAsExpression().toString());

        if (this.attachModifiers)
            builder.modifiers(md.getModifiers().toString());

        if (md.getRange().isPresent()) {
            builder.beginLine(md.getRange().get().begin.line).endLine(md.getRange().get().end.line);
        } else {
            builder.beginLine(-1).endLine(-1);
        }
        MethodInfo method = builder.buildMethod();
        logger.log(method);
        collector.addMethodInfo(method);
    }

}
