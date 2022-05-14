package lib.visitors;

import com.github.javaparser.ast.body.FieldDeclaration;
import lib.Logger;
import lib.reports.info.interfaces.FieldInfo;
import lib.reports.interfaces.ClassReport;
import lib.reports.info.InfoBuilder;

/**
 * Visitor for classes that extends from {@link FileVisitor}
 *
 * @see com.github.javaparser.ast.visitor
 */
public class ClassesVisitor extends FileVisitor<ClassReport> {

    /**
     * Class constructor
     *
     * @param logger logger where send message when incurred new find
     */
    public ClassesVisitor(Logger logger) {
        super(true, logger);
    }

    public void visit(FieldDeclaration fd, ClassReport collector) {
        super.visit(fd, collector);
        InfoBuilder builder = new InfoBuilder()
                .name(""+fd.getVariable(0))
                .type(""+fd.getElementType())
                .modifiers(""+fd.getModifiers())
                .report(collector);
        FieldInfo field = builder.buildField();
        logger.log(field);
        collector.addFieldInfo(field);
    }
}
