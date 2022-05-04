package visitors;

import com.github.javaparser.Range;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import reports.ClassReport;
import reports.MethodInfoBuilder;
import reports.info.MethodInfo;

public class ClassesVisitor extends VoidVisitorAdapter<ClassReport> {
    public void visit(ClassOrInterfaceDeclaration cd, ClassReport collector) {
        super.visit(cd, collector);
        collector.setFullClassName(cd.getNameAsString());
        collector.setSrcFullFileName(cd.getFullyQualifiedName().orElse("Fully class name not found"));
    }

    public void visit(MethodDeclaration md, ClassReport collector) {
        super.visit(md, collector);
        MethodInfoBuilder builder = new MethodInfoBuilder()
                .classReport(collector)
                .name(md.getNameAsExpression().toString())
                .modifiers(md.getModifiers().toString());

        if (md.getRange().isPresent()) {
            builder.beginLine(md.getRange().get().begin.line).endLine(md.getRange().get().end.line);
        } else {
            builder.beginLine(-1).endLine(-1);
        }

        collector.addMethodInfo(builder.build());
    }

    /*
    TODO: Manca lo studio dei campi della classe
     */

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
}