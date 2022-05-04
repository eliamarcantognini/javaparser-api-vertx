package reports;

import reports.info.FieldInfo;
import reports.info.MethodInfo;
import java.util.List;

public interface ClassReport {

	String getFullClassName();
	
	String getSrcFullFileName();

	List<MethodInfo> getMethodsInfo();

	List<FieldInfo> getFieldsInfo();

	void setFullClassName(String fullClassName);

	void setSrcFullFileName(String srcFullFileName);

	void addMethodInfo(MethodInfo methodInfo);

	void addFieldInfo(FieldInfo fieldInfo);
	
}
