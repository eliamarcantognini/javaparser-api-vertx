package lib.reports.interfaces;

import java.util.List;

public interface PackageReport {

	String getFullClassName();
	
	String getSrcFullFileName();

	List<ClassReport> getClassesReport();

	List<InterfaceReport> getInterfaceReports();
	
}
