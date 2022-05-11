package lib.reports;

import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

import java.util.LinkedList;
import java.util.List;

public class ProjectReportImpl implements ProjectReport {

    private final List<PackageReport> packageReports;
    private ClassReport mainClass;

    public ProjectReportImpl() {
        this.packageReports = new LinkedList<>();
    }

    @Override
    public ClassReport getMainClass() {
        return this.mainClass;
    }

    @Override
    public void setMainClass(ClassReport classReport) {
        this.mainClass = classReport;
    }

    @Override
    public List<PackageReport> getAllPackageReports() {
        return this.packageReports;
    }

    @Override
    public void addPackageReport(PackageReport packageReport) {
        this.packageReports.add(packageReport);
    }

    @Override
    public ClassReport getClassReport(String fullClassName) {
        final ClassReport[] classReport = new ClassReportImpl[1];
        classReport[0] = null;
        this.packageReports.forEach(p -> p.getClassesReports().forEach(c -> {
            if (c.getName().equals(fullClassName)) classReport[0] = c;
        }));
        return classReport[0];
    }

    @Override
    public String toString() {
        return "Project Report:\n" + "\tProject Reports: \n" + this.packageReports + "\n";
    }
}
