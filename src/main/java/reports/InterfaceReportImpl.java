package reports;

import reports.info.interfaces.MethodInfo;
import reports.interfaces.InterfaceReport;

import java.util.LinkedList;
import java.util.List;

public class InterfaceReportImpl implements InterfaceReport {

    private String interfaceName;
    private String interfaceFullPath;
    private final List<MethodInfo> methodsName;

    public InterfaceReportImpl(){
        this.methodsName = new LinkedList<>();
    }

    @Override
    public String getName() {
        return this.interfaceName;
    }

    @Override
    public String getSourceFullPath() {
        return this.interfaceFullPath;
    }

    @Override
    public List<MethodInfo> getMethodsNames() {
        return this.methodsName;
    }

    @Override
    public void setName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public void setFullPath(final String interfaceFullPath) {
        this.interfaceFullPath = interfaceFullPath;
    }

    @Override
    public void addMethodsName(MethodInfo methodName) {
        this.methodsName.add(methodName);
    }

    @Override
    public String toString() {
        return "InterfaceReport: \n"
                + "\tName: " + this.getName() + "\n"
                + "\tFull Path: " + this.getSourceFullPath() + "\n"
                + "\tMethods: " + this.getMethodsNames().toString();
    }

}
