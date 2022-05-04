package reports.interfaces;

import reports.info.interfaces.MethodInfo;

import java.util.List;

public interface InterfaceReport extends Report{

    List<MethodInfo> getMethodsInfo();
    void addMethodInfo(MethodInfo methodInfo);
}
