package lib.reports.interfaces;

import java.util.List;

public interface PackageReport extends Report{

	List<ClassReport> getClassesReport();

	List<InterfaceReport> getInterfaceReports();
	
}
