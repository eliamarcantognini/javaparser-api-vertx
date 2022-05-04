package visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import reports.interfaces.ClassReport;
import reports.info.InfoBuilder;

public class ClassesVisitor extends FileVisitor<ClassReport> {

    public ClassesVisitor() {
        super(true);
    }

    public void visit(FieldDeclaration fd, ClassReport collector) {
        super.visit(fd, collector);
        InfoBuilder builder = new InfoBuilder()
                //TODO: hardcoded fd.getVariable(0) per prendere il nome del campo
                .name(""+fd.getVariable(0))
                .type(""+fd.getElementType())
                .modifiers(""+fd.getModifiers())
                .report(collector);
        collector.addFieldInfo(builder.buildField());
        this.testFieldDeclarationMethods(fd);
    }

    private void testMethodDeclarationMethods(MethodDeclaration md){
        System.out.println("=== START VISIT METHODS OF METHOD DECLARATION ===");
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
        System.out.println("=== END VISIT METHODS OF METHOD DECLARATION ===");
    }

    private void testFieldDeclarationMethods(FieldDeclaration fd){
        System.out.println("=== START VISIT METHODS OF FIELD DECLARATION ===");
        System.out.println("fd => " + fd);
        System.out.println("fd.asFieldDeclaration() => " + fd.asFieldDeclaration());
        System.out.println("fd.getModifiers() => " + fd.getModifiers());
        System.out.println("fd.getVariables() => " + fd.getVariables());
        System.out.println("fd.getRange() => " + fd.getRange());
        System.out.println("fd.getBegin() => " + fd.getBegin());
        System.out.println("fd.getElementType() => " + fd.getElementType());
//        System.out.println("fd => " + fd);
        System.out.println("=== END VISIT METHODS OF FIELD DECLARATION ===");
    }
}
