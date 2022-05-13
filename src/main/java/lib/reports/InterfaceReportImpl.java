package lib.reports;

import lib.reports.info.interfaces.MethodInfo;
import lib.reports.interfaces.InterfaceReport;

import java.util.LinkedList;
import java.util.List;

public class InterfaceReportImpl implements InterfaceReport {

    private String interfaceName;
    private String interfaceFullPath;
    private final List<MethodInfo> methodsInfo;

    public InterfaceReportImpl(){
        this.interfaceName = "";
        this.interfaceFullPath = "";
        this.methodsInfo = new LinkedList<>();
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
    public List<MethodInfo> getMethodsInfo() {
        return this.methodsInfo;
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
    public void addMethodInfo(MethodInfo methodInfo) {
        this.methodsInfo.add(methodInfo);
    }

    @Override
    public String toString() {
        return "InterfaceReport: \n"
                + "\tName: " + this.getName() + "\n"
                + "\tFull Path: " + this.getSourceFullPath() + "\n"
                + "\tMethods: " + this.getMethodsInfo().toString();
    }

}
