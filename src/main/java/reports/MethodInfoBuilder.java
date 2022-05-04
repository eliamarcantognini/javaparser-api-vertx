package reports;

import reports.info.MethodInfo;
import reports.info.MethodInfoImpl;

public class MethodInfoBuilder {

    private ClassReport parentClassReport;
    private String name;
    private int beginLine;
    private int endLine;
    private String modifiers;

    /*
    TODO: decidere come gestire il caso di getRange == null
     */
    public MethodInfoBuilder classReport(final ClassReport classReport){
        this.parentClassReport = classReport;
        return this;
    }

    public MethodInfoBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public MethodInfoBuilder beginLine(final int line) {
        this.beginLine = line;
        return this;
    }

    public MethodInfoBuilder endLine(final int line) {
        this.endLine = line;
        return this;
    }

    public MethodInfoBuilder modifiers(final String modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public MethodInfo build() {
        return new MethodInfoImpl(this.name, this.beginLine, this.endLine, this.modifiers, this.parentClassReport);
    }

}
