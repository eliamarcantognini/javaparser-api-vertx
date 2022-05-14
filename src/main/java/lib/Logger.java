package lib;

import utils.dto.DTOParser;
import utils.dto.DTOs;
import lib.reports.info.interfaces.FieldInfo;
import lib.reports.info.interfaces.MethodInfo;
import lib.reports.interfaces.ClassReport;
import lib.reports.interfaces.InterfaceReport;
import lib.reports.interfaces.PackageReport;
import lib.reports.interfaces.ProjectReport;

/**
 * Interface for logger where send messages about new find during analysis
 */
@FunctionalInterface
public interface Logger {
    // TODO: complete javadoc
    /**
     *
     * @param message
     */
    void log(String message);

    /**
     *
     * @param method
     */
    default void log(MethodInfo method) {
        log(createMessage(CodeElementFound.METHOD.getCode(), DTOParser.parseString(DTOs.createMethodDTO(method))));
    }

    /**
     *
     * @param field
     */
    default void log(FieldInfo field) {
        log(createMessage(CodeElementFound.FIELD.getCode(), DTOParser.parseString(DTOs.createFieldDTO(field))));
    }

    /**
     *
     * @param interfaceReport
     */
    default void log(InterfaceReport interfaceReport) {
        log(createMessage(CodeElementFound.INTERFACE.getCode(), DTOParser.parseString(DTOs.createInterfaceDTO(interfaceReport))));
    }

    /**
     *
     * @param classReport
     */
    default void log(ClassReport classReport) {
        log(createMessage(CodeElementFound.CLASS.getCode(), DTOParser.parseString(DTOs.createClassDTO(classReport))));
    }

    /**
     *
     * @param packageReport
     */
    default void log(PackageReport packageReport) {
        log(createMessage(CodeElementFound.PACKAGE.getCode(), DTOParser.parseString(DTOs.createPackageDTO(packageReport))));
    }

    /**
     *
     * @param projectReport
     */
    default void log(ProjectReport projectReport) {
        log(createMessage(CodeElementFound.PROJECT.getCode(), DTOParser.parseString(DTOs.createProjectDTO(projectReport))));
    }

    /**
     *
     * @param id
     * @param json
     * @return
     */
    private String createMessage(String id, String json) {
        return id + json;
    }

    /**
     * Prefix as enum to manage new element found
     */
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

        /**
         * Get code of element found as string
         *
         * @return code as string
         */
        public String getCode(){
            return this.code;
        }
    }

}
