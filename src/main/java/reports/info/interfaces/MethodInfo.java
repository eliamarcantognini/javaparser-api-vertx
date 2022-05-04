package reports.info.interfaces;

import reports.interfaces.Report;

import java.util.Optional;

public interface MethodInfo {

	String getName();
	int getSrcBeginLine();
	int getEndBeginLine();
	Optional<String> getModifiers();
	Report getParent();
}
