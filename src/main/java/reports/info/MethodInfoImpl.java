package reports.info;

import reports.ClassReport;
import java.util.Optional;

public class MethodInfoImpl implements MethodInfo{

    final String methodName;
    final int srcBeginLine;
    final int endBeginLine;
    final String modifiers;
    final ClassReport parent;

    public MethodInfoImpl(String methodName, int srcBeginLine, int endBeginLine, String modifiers, ClassReport parent) {
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
    public ClassReport getParent() {
        return this.parent;
    }

    /*
    TODO: Non è presente la stampa del class report parent perché si creerebbe
        un loop dato che anche class report del padre richiamerebbe la toString di
        method info, decidere come gestirlo.
    */
    @Override
    public String toString() {

        return "\n\t\tMethodInfoImpl{"
                + "methodName=" + methodName
                + ", srcBeginLine=" + srcBeginLine
                + ", endBeginLine=" + endBeginLine
                + ", modifiers=" + modifiers
                + ", parent=" + parent.getSrcFullFileName();
    }
}
