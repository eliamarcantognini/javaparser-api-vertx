package reports.info;

import reports.ClassReport;
import reports.InterfaceReport;

import java.util.Optional;

public interface MethodInfo {

	String getName();
	int getSrcBeginLine();
	int getEndBeginLine();
	Optional<String> getModifiers();
	InterfaceReport getParent();
}
