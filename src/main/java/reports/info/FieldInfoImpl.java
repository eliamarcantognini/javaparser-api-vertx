package reports.info;

import reports.info.interfaces.FieldInfo;
import reports.interfaces.ClassReport;

import java.util.List;
import java.util.Optional;

public class FieldInfoImpl implements FieldInfo {

    final String fieldName;
    final String fieldType;
    final String fieldModifiers;
    final ClassReport parentClassReport;

    public FieldInfoImpl(String fieldName, String fieldType, String fieldModifiers, ClassReport parentClassReport){
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldModifiers = fieldModifiers;
        this.parentClassReport = parentClassReport;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }

    @Override
    public Optional<String> getFieldModifiers() {
        return this.fieldModifiers != null ? Optional.of(this.fieldModifiers) : Optional.empty();
    }

    @Override
    public ClassReport getParentClassReport() {
        return parentClassReport;
    }
}
