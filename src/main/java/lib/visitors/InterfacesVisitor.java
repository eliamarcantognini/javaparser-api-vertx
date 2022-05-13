package lib.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import lib.Logger;
import lib.reports.interfaces.InterfaceReport;

public class InterfacesVisitor extends FileVisitor<InterfaceReport> {

    public InterfacesVisitor(Logger logger) {
        super(false, logger);
    }

    private void testMethodDeclarationMethods(MethodDeclaration md){
        System.out.println("=== START VISIT METHOD ===");
        //System.out.println("md.getDeclarationAsString() -> " + md.getDeclarationAsString());
        System.out.println("md.getNameAsExpression() -> " + md.getNameAsExpression());
        //System.out.println("md.getParameters() -> " + md.getParameters());
        System.out.println("md.getModifiers() -> " + md.getModifiers());
        System.out.println("md.getRange() -> " + md.getRange());
        System.out.println("md.getRange().get().begin -> " + md.getRange().get().begin);
        System.out.println("md.getRange().get().begin.line -> " + md.getRange().get().begin.line);
        System.out.println("md.getRange().get().begin.column -> " + md.getRange().get().begin.column);
        System.out.println("md.getRange().get().end -> " + md.getRange().get().end);
        System.out.println("md.getRange().get().end.line -> " + md.getRange().get().end.line);
        System.out.println("md.getRange().get().end.column -> " + md.getRange().get().end.column);
        System.out.println("md.getRange().get().getLineCount() -> " + md.getRange().get().getLineCount());
        //System.out.println("md.getTokenRange() -> " + md.getTokenRange());
        //System.out.println("md.getTokenRange().get() -> " + md.getTokenRange().get());
        //System.out.println("md.getTokenRange().get().getBegin() -> " + md.getTokenRange().get().getBegin());
        //System.out.println("md.getTokenRange().get().getEnd() -> " + md.getTokenRange().get().getEnd());
        System.out.println("=== END VISIT METHOD ===");
    }

//    public void visit(PackageDeclaration pd, InterfaceReport collector) {
//        super.visit(pd, collector);
//        collector.setInterfaceFullPath(pd.getNameAsString());
//    }
}
