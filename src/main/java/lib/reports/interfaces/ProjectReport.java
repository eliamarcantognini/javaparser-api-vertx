package lib.reports.interfaces;

import java.util.List;

public interface ProjectReport {

	ClassReport getMainClass();

	void setMainClass(ClassReport classReport);
	
	List<PackageReport> getAllPackageReports();

	void addPackageReport (PackageReport packageReport);
	
	ClassReport getClassReport(String fullClassName);
}
