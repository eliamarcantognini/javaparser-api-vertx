package reports.info;

import reports.InterfaceReport;

public class MethodInfoBuilder {

    private InterfaceReport parent;
    private String name;
    private int beginLine;
    private int endLine;
    private String modifiers;

    /*
    TODO: decidere come gestire il caso di getRange == null
     */
    public MethodInfoBuilder classReport(final InterfaceReport classReport){
        this.parent = classReport;
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
        return new MethodInfoImpl(this.name, this.beginLine, this.endLine, this.modifiers, this.parent);
    }

}
