package lib.reports;

import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;

import java.util.List;

public class PackageReportImpl implements PackageReport {
    @Override
    public List<ClassReport> getClassesReport() {
        return null;
    }

    @Override
    public List<InterfaceReport> getInterfaceReports() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getSourceFullPath() {
        return null;
    }

    @Override
    public void setFullPath(String interfaceFullPath) {

    }
}
