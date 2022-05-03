package reports.info;

import reports.ClassReport;

public interface FieldInfo {

	String getName();
	String getFieldTypeFullName();
	
	ClassReport getParent();
}
