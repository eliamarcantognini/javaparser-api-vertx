package reports.info.interfaces;

import reports.interfaces.Report;

import java.util.Optional;

public interface FieldInfo {

    /**
     * Get the name of the field
     *
     * @return the name
     */
    String getFieldName();

    /**
     * Get the type of the field
     *
     * @return the type name
     */
    String getFieldType();

    /**
     * Get all the modifiers of the method, if present
     *
     * @return an {@link Optional<String>} if the modifiers are present, Empty otherwise
     */
    Optional<String> getFieldModifiers();

    /**
     * Get the full parent {@link reports.interfaces.ClassReport}
     *
     * @return the parent report
     */
    Report getParentClassReport();
}
