package dto;

import reports.info.interfaces.FieldInfo;
import reports.info.interfaces.MethodInfo;
import reports.interfaces.ClassReport;
import reports.interfaces.InterfaceReport;
import reports.interfaces.PackageReport;

import java.util.ArrayList;
import java.util.List;

public final class DTOs {

    private DTOs() {
    }

    public static PackageDTO createPackageDTO(PackageReport report) {
        return new PackageDTO(report.getFullClassName(), report.getSrcFullFileName(), createClassesDTO(report.getClassesReport()), createInterfacesDTO(report.getInterfaceReports()));
    }

    public static ClassInterfaceDTO createInterfaceDTO(InterfaceReport report) {
        return new ClassInterfaceDTO(report.getName(), report.getSourceFullPath(), createMethodsDTO(report.getMethodsInfo(), false));
    }

    public static ClassInterfaceDTO createClassDTO(ClassReport report) {
        return new ClassInterfaceDTO(report.getName(), report.getSourceFullPath(), createMethodsDTO(report.getMethodsInfo(), true), createFieldsDTO(report.getFieldsInfo()));
    }

    private static List<ClassInterfaceDTO> createInterfacesDTO(List<InterfaceReport> reports) {
        List<ClassInterfaceDTO> interfaceDTOs = new ArrayList<>();
        for (InterfaceReport r : reports)
            interfaceDTOs.add(createInterfaceDTO(r));
        return interfaceDTOs;
    }

    private static List<ClassInterfaceDTO> createClassesDTO(List<ClassReport> reports) {
        List<ClassInterfaceDTO> classDTOs = new ArrayList<>();
        for (ClassReport r : reports)
            classDTOs.add(createClassDTO(r));
        return classDTOs;
    }

    private static List<MethodDTO> createMethodsDTO(List<MethodInfo> methods, boolean attachModifiers) {
        List<MethodDTO> methodDTOs = new ArrayList<>();
        for (MethodInfo m : methods)
            if (attachModifiers)
                methodDTOs.add(new MethodDTO(m.getName(), m.getSrcBeginLine(), m.getEndBeginLine(), m.getModifiers().orElse("")));
            else
                methodDTOs.add(new MethodDTO(m.getName(), m.getSrcBeginLine(), m.getEndBeginLine()));
        return methodDTOs;
    }

    private static List<FieldDTO> createFieldsDTO(List<FieldInfo> fields) {
        List<FieldDTO> fieldDTOs = new ArrayList<>();
        for (FieldInfo f : fields)
            fieldDTOs.add(new FieldDTO(f.getFieldName(), f.getFieldType(), f.getFieldModifiers().orElse("")));
        return fieldDTOs;
    }
}
