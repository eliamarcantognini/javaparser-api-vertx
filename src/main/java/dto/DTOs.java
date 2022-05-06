package dto;

import reports.info.interfaces.FieldInfo;
import reports.info.interfaces.MethodInfo;
import reports.interfaces.ClassReport;
import reports.interfaces.InterfaceReport;
import reports.interfaces.PackageReport;
import reports.interfaces.ProjectReport;

import java.util.ArrayList;
import java.util.List;

public final class DTOs {

    private DTOs() {
    }

    public static ProjectDTO createProjectDTO(ProjectReport report) {
        return new ProjectDTO(createClassDTO(report.getMainClass()), createPackageDTOs(report.getAllProjects()));
    }

    public static PackageDTO createPackageDTO(PackageReport report) {
        return new PackageDTO(report.getFullClassName(), report.getSrcFullFileName(), createClassDTOs(report.getClassesReport()), createInterfaceDTOs(report.getInterfaceReports()));
    }

    public static ClassInterfaceDTO createInterfaceDTO(InterfaceReport report) {
        return new ClassInterfaceDTO(report.getName(), report.getSourceFullPath(), createMethodDTOs(report.getMethodsInfo(), false));
    }

    public static ClassInterfaceDTO createClassDTO(ClassReport report) {
        return new ClassInterfaceDTO(report.getName(), report.getSourceFullPath(), createMethodDTOs(report.getMethodsInfo(), true), createFieldDTOs(report.getFieldsInfo()));
    }

    private static List<PackageDTO> createPackageDTOs(List<PackageReport> reports) {
        List<PackageDTO> packageDTOs = new ArrayList<>();
        for (PackageReport r : reports)
            packageDTOs.add(createPackageDTO(r));
        return packageDTOs;
    }

    private static List<ClassInterfaceDTO> createInterfaceDTOs(List<InterfaceReport> reports) {
        List<ClassInterfaceDTO> interfaceDTOs = new ArrayList<>();
        for (InterfaceReport r : reports)
            interfaceDTOs.add(createInterfaceDTO(r));
        return interfaceDTOs;
    }

    private static List<ClassInterfaceDTO> createClassDTOs(List<ClassReport> reports) {
        List<ClassInterfaceDTO> classDTOs = new ArrayList<>();
        for (ClassReport r : reports)
            classDTOs.add(createClassDTO(r));
        return classDTOs;
    }

    private static List<MethodDTO> createMethodDTOs(List<MethodInfo> methods, boolean attachModifiers) {
        List<MethodDTO> methodDTOs = new ArrayList<>();
        for (MethodInfo m : methods)
            if (attachModifiers)
                methodDTOs.add(new MethodDTO(m.getName(), m.getSrcBeginLine(), m.getEndBeginLine(), m.getModifiers().orElse("")));
            else methodDTOs.add(new MethodDTO(m.getName(), m.getSrcBeginLine(), m.getEndBeginLine()));
        return methodDTOs;
    }

    private static List<FieldDTO> createFieldDTOs(List<FieldInfo> fields) {
        List<FieldDTO> fieldDTOs = new ArrayList<>();
        for (FieldInfo f : fields)
            fieldDTOs.add(new FieldDTO(f.getFieldName(), f.getFieldType(), f.getFieldModifiers().orElse("")));
        return fieldDTOs;
    }
}