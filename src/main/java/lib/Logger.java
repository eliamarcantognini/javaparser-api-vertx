package lib;

import utils.dto.DTOParser;
import utils.dto.DTOs;
import lib.reports.info.interfaces.FieldInfo;
import lib.reports.info.interfaces.MethodInfo;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

@FunctionalInterface
public interface Logger {

    void log(String message);

    default void log(MethodInfo method) {
        log(createMessage(CodeElementFound.METHOD.getCode(), DTOParser.parseString(DTOs.createMethodDTO(method))));
    }

    default void log(FieldInfo field) {
        log(createMessage(CodeElementFound.FIELD.getCode(), DTOParser.parseString(DTOs.createFieldDTO(field))));
    }

    default void log(InterfaceReport interfaceReport) {
        log(createMessage(CodeElementFound.INTERFACE.getCode(), DTOParser.parseString(DTOs.createInterfaceDTO(interfaceReport))));
    }

    default void log(ClassReport classReport) {
        log(createMessage(CodeElementFound.CLASS.getCode(), DTOParser.parseString(DTOs.createClassDTO(classReport))));
    }


    default void log(PackageReport packageReport) {
        log(createMessage(CodeElementFound.PACKAGE.getCode(), DTOParser.parseString(DTOs.createPackageDTO(packageReport))));
    }

    default void log(ProjectReport projectReport) {
        log(createMessage(CodeElementFound.PROJECT.getCode(), DTOParser.parseString(DTOs.createProjectDTO(projectReport))));
    }

    private String createMessage(String id, String json) {
        return id + json;
    }

    enum CodeElementFound{
        METHOD("METHOD_REPORT:"),
        FIELD("FIELD_REPORT:"),
        CLASS("CLASS_REPORT:"),
        INTERFACE("INTERFACE_REPORT:"),
        PACKAGE("PACKAGE_REPORT:"),
        PROJECT("PROJECT_REPORT:");

        private final String code;

        CodeElementFound (final String code){
            this.code = code;
        }

        public String getCode(){
            return this.code;
        }
    }

}
