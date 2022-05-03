package reports;

import reports.info.FieldInfo;
import reports.info.MethodInfo;
import java.util.List;

public interface PackageReport {

	String getFullClassName();
	
	String getSrcFullFileName();

	List<MethodInfo> getMethodsInfo();

	List<FieldInfo> getFieldsInfo();
	
}
