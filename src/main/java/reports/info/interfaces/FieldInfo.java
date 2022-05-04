package reports.info.interfaces;

import reports.interfaces.Report;

import java.util.Optional;

public interface FieldInfo {
	String getFieldName();
	String getFieldType();
	Optional<String> getFieldModifiers();
	Report getParentClassReport();
}
