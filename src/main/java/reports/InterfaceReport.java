package reports;

import java.util.List;

public interface InterfaceReport {
    String getInterfaceName();
    String getInterfaceSourceFullPath();
    List<String> getMethodsNames();
    void setInterfaceName(String interfaceName);
    void setInterfaceFullPath(String interfaceFullPath);
    void addMethodsName(String methodName);
}
