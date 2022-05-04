package reports;

import reports.info.MethodInfo;

import java.util.List;

public interface InterfaceReport extends Report{

    List<MethodInfo> getMethodsNames();
    void addMethodsName(MethodInfo methodName);
}
