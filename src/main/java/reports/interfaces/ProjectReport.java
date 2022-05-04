package reports.interfaces;

import java.util.List;

public interface ProjectReport {

	ClassReport getMainClass();
	
	List<PackageReport> getAllProjects();
	
	ClassReport getClassReport(String fullClassName);
}
