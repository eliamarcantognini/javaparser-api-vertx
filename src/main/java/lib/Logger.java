package lib;

import dto.DTOParser;
import dto.DTOs;
import lib.reports.info.interfaces.FieldInfo;
import lib.reports.info.interfaces.MethodInfo;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

@FunctionalInterface
public interface Logger {

    void log(String message);

    //to-do chiedere i DTO per questi--------------------------------------------------

    default void log(MethodInfo method) {
        log(DTOParser.parseString(method.toString()));
    }

    default void log(FieldInfo field) {
        log(DTOParser.parseString(field.toString()));
    }

    //--------------------------------------------------------------------------------

    default void log(InterfaceReport interfaceReport) {
        log(DTOParser.parseString(DTOs.createInterfaceDTO(interfaceReport)));
    }

    default void log(ClassReport classReport) {
        log(DTOParser.parseString(DTOs.createClassDTO(classReport)));
    }

    default void log(PackageReport packageReport) {
        log(DTOParser.parseString(DTOs.createPackageDTO(packageReport)));
    }

    default void log(ProjectReport projectReport) {
        log(DTOParser.parseString(DTOs.createProjectDTO(projectReport)));
    }
}
