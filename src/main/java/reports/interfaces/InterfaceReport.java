package reports.interfaces;

import reports.info.interfaces.MethodInfo;

import java.util.List;

public interface InterfaceReport extends Report{

    List<MethodInfo> getMethodsNames();
    void addMethodsName(MethodInfo methodName);
}
