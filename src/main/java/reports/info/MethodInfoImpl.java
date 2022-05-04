package reports.info;

import reports.info.interfaces.MethodInfo;
import reports.interfaces.Report;

import java.util.Optional;

public class MethodInfoImpl implements MethodInfo {

    final String methodName;
    final int srcBeginLine;
    final int endBeginLine;
    final String modifiers;
    final Report parent;

    public MethodInfoImpl(String methodName, int srcBeginLine, int endBeginLine, String modifiers, Report parent) {
        this.methodName = methodName;
        this.srcBeginLine = srcBeginLine;
        this.endBeginLine = endBeginLine;
        this.modifiers = modifiers;
        this.parent = parent;
    }

    @Override
    public String getName() {
        return this.methodName;
    }

    @Override
    public int getSrcBeginLine() {
        return this.srcBeginLine;
    }

    @Override
    public int getEndBeginLine() {
        return this.endBeginLine;
    }

    @Override
    public Optional<String> getModifiers() {
        return this.modifiers == null ? Optional.empty() : Optional.of(this.modifiers);
    }

    @Override
    public Report getParent() {
        return this.parent;
    }

    @Override
    public String toString() {

        return "\n\t\tMethodInfoImpl{"
                + "methodName=" + methodName
                + ", srcBeginLine=" + srcBeginLine
                + ", endBeginLine=" + endBeginLine
                + ", modifiers=" + modifiers
                + ", parent=" + parent.getName();
    }
}
