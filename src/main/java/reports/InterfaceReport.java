package reports;

import java.util.List;

public interface InterfaceReport {
    String getInterfaceName();
    String getInterfaceSourceFullPath();
    List<String> getMethodsName();
}
