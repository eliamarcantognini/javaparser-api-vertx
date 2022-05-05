package reports.interfaces;

import reports.info.interfaces.MethodInfo;

import java.util.List;

public interface InterfaceReport extends Report {

    /**
     * Get a list of methods inside the item (class/interface)
     *
     * @return a {@link List} of {@link MethodInfo}
     */
    List<MethodInfo> getMethodsInfo();

    /**
     * Add a {@link MethodInfo} to the report
     *
     * @param methodInfo the {@link MethodInfo}
     */
    void addMethodInfo(MethodInfo methodInfo);
}
