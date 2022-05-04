package reports.info;

import reports.info.interfaces.FieldInfo;
import reports.info.interfaces.MethodInfo;
import reports.interfaces.Report;

public class InfoBuilder {

    private Report report;
    private String name;
    private int beginLine;
    private int endLine;
    private String modifiers;
    private String type;

    /*
    TODO: decidere come gestire il caso di getRange == null
     */
    public InfoBuilder report(final Report report) {
        this.report = report;
        return this;
    }

    public InfoBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public InfoBuilder beginLine(final int line) {
        this.beginLine = line;
        return this;
    }

    public InfoBuilder endLine(final int line) {
        this.endLine = line;
        return this;
    }

    public InfoBuilder modifiers(final String modifiers) {
        this.modifiers = modifiers;
        return this;
    }

    public InfoBuilder type(final String type) {
        this.type = type;
        return this;
    }

    public MethodInfo buildMethod() {
        return new MethodInfoImpl(this.name, this.beginLine, this.endLine, this.modifiers, this.report);
    }

    public FieldInfo buildField() {
        return new FieldInfoImpl(this.name, this.type, this.modifiers, this.report);
    }

}
