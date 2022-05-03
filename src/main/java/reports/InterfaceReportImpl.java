package reports;

import java.util.LinkedList;
import java.util.List;

public class InterfaceReportImpl implements InterfaceReport{

    private String interfaceName;
    private String interfaceFullPath;
    private final List<String> methodsName;

    public InterfaceReportImpl(){
        this.methodsName = new LinkedList<>();
    }

    @Override
    public String getInterfaceName() {
        return this.interfaceName;
    }

    @Override
    public String getInterfaceSourceFullPath() {
        return this.interfaceFullPath;
    }

    @Override
    public List<String> getMethodsNames() {
        return this.methodsName;
    }

    @Override
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public void setInterfaceFullPath(final String interfaceFullPath) {
        this.interfaceFullPath = interfaceFullPath;
    }

    @Override
    public void addMethodsName(String methodName) {
        this.methodsName.add(methodName);
    }

    @Override
    public String toString() {
        return "InterfaceReport: \n"
                + "\tName: " + this.getInterfaceName() + "\n"
                + "\tFull Path: " + this.getInterfaceSourceFullPath() + "\n"
                + "\tMethods: " + this.getMethodsNames();
    }

}
