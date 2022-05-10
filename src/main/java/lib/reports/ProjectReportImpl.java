package lib.reports;

import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

import java.util.List;

public class ProjectReportImpl implements ProjectReport {
    @Override
    public ClassReport getMainClass() {
        return null;
    }

    @Override
    public List<PackageReport> getAllProjects() {
        return null;
    }

    @Override
    public ClassReport getClassReport(String fullClassName) {
        return null;
    }
}
