package reports;

import reports.info.FieldInfo;
import reports.info.MethodInfo;
import java.util.List;

public interface ClassReport extends InterfaceReport {

	List<FieldInfo> getFieldsInfo();

	void addFieldInfo(FieldInfo fieldInfo);
}
