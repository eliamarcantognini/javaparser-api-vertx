package reports.info.interfaces;

import reports.interfaces.ClassReport;

public interface FieldInfo {

	String getName();
	String getFieldTypeFullName();
	
	ClassReport getParent();
}
