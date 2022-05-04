package reports;

import com.github.javaparser.ast.body.MethodDeclaration;
import reports.info.MethodInfo;
import reports.info.MethodInfoImpl;

public class MethodInfoBuilder {

    private final MethodDeclaration md;
    private final ClassReport parentClassReport;

    public MethodInfoBuilder(final MethodDeclaration md, ClassReport classReport){
        this.md = md;
        this.parentClassReport = classReport;
    }

    /*
    TODO: decidere come gestire il caso di getRange == null
     */
    public MethodInfo buildMethodInfo(){
        return new MethodInfoImpl(md.getNameAsExpression().toString(),
                md.getRange().get().begin.line,
                md.getRange().get().end.line,
                md.getModifiers().toString(),
                this.parentClassReport);
    }
}
