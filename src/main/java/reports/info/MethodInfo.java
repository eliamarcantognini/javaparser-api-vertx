package reports.info;

import reports.ClassReport;

public interface MethodInfo {

	String getName();
	int getSrcBeginLine();
	int getEndBeginLine();
	ClassReport getParent();
		
}
