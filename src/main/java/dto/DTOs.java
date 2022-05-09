package dto;

import lib.reports.info.interfaces.FieldInfo;
import lib.reports.info.interfaces.MethodInfo;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

import java.util.ArrayList;
import java.util.List;

public final class DTOs {

    private DTOs() {
    }

    /**
     * Create a {@link ProjectDTO} from a {@link ProjectReport}
     *
     * @param report the {@link ProjectReport}
     * @return a {@link ProjectDTO}
     */
    public static ProjectDTO createProjectDTO(ProjectReport report) {
        return new ProjectDTO(createClassDTO(report.getMainClass()), createPackageDTOs(report.getAllProjects()));
    }

    /**
     * Create a {@link PackageDTO} from a {@link PackageReport}
     *
     * @param report the {@link PackageReport}
     * @return a {@link PackageDTO}
     */
    public static PackageDTO createPackageDTO(PackageReport report) {
        return new PackageDTO(report.getName(), report.getSourceFullPath(), createClassDTOs(report.getClassesReport()), createInterfaceDTOs(report.getInterfaceReports()));
    }

    /**
     * Create a {@link ClassInterfaceDTO} from a {@link InterfaceReport}
     *
     * @param report the {@link InterfaceReport}
     * @return a {@link ClassInterfaceDTO}
     */
    public static ClassInterfaceDTO createInterfaceDTO(InterfaceReport report) {
        return new ClassInterfaceDTO(report.getName(), report.getSourceFullPath(), createMethodDTOs(report.getMethodsInfo(), false));
    }

    /**
     * Create a {@link ClassInterfaceDTO} from a {@link ClassReport}
     *
     * @param report the {@link ClassReport}
     * @return a {@link ClassInterfaceDTO}
     */
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
