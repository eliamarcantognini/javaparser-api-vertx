package reports.interfaces;

import reports.info.interfaces.FieldInfo;

import java.util.List;

public interface ClassReport extends InterfaceReport {

    List<FieldInfo> getFieldsInfo();

    void addFieldInfo(FieldInfo fieldInfo);
}
