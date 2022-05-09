package lib.reports.interfaces;

import java.util.List;

public interface PackageReport extends Report{

	List<ClassReport> getClassesReports();

	void addClassReport(ClassReport classReport);

	List<InterfaceReport> getInterfaceReports();

	void addInterfaceReport(InterfaceReport interfaceReport);

}
