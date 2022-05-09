package lib.reports;

import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;

import java.util.LinkedList;
import java.util.List;

public class PackageReportImpl implements PackageReport {

    private String name;
    private String fullPath;
    List<ClassReport> classReports;
    List<InterfaceReport> interfaceReports;

    public PackageReportImpl() {
        this.classReports = new LinkedList<>();
        this.interfaceReports = new LinkedList<>();
    }

    @Override
    public List<ClassReport> getClassesReports() {
        return this.classReports;
    }

    @Override
    public void addClassReport(ClassReport classReport) {
        this.classReports.add(classReport);
    }

    @Override
    public List<InterfaceReport> getInterfaceReports() {
        return this.interfaceReports;
    }

    @Override
    public void addInterfaceReport(InterfaceReport interfaceReport) {
        this.interfaceReports.add(interfaceReport);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSourceFullPath() {
        return this.fullPath;
    }

    @Override
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}