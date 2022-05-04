package reports.info.interfaces;

import reports.interfaces.ClassReport;
import java.util.Optional;

public interface FieldInfo {
	String getFieldName();
	String getFieldType();
	Optional<String> getFieldModifiers();
	ClassReport getParentClassReport();
}
