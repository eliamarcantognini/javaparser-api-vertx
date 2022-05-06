package dto;

import reports.info.interfaces.FieldInfo;
import reports.info.interfaces.MethodInfo;
import reports.interfaces.ClassReport;
import reports.interfaces.InterfaceReport;

import java.util.ArrayList;
import java.util.List;

public final class DTOs {

    private DTOs(){}

    public static ClassInterfaceDTO createClassDTO(ClassReport report){
        return new ClassInterfaceDTO(report.getName(), report.getSourceFullPath(), createMethodsDTO(report.getMethodsInfo(), true), createFieldsDTO(report.getFieldsInfo()));
    }

    public static ClassInterfaceDTO createInterfaceDTO(InterfaceReport report){
        return new ClassInterfaceDTO(report.getName(), report.getSourceFullPath(), createMethodsDTO(report.getMethodsInfo(), false));
    }

    private static List<MethodDTO> createMethodsDTO(List<MethodInfo> methods, boolean attachModifiers){
         List <MethodDTO> methodsDTO = new ArrayList<>();
        for (MethodInfo m : methods)
            if (attachModifiers)
                methodsDTO.add(new MethodDTO(m.getName(), m.getSrcBeginLine(), m.getEndBeginLine(), m.getModifiers().orElse("")));
            else
                methodsDTO.add(new MethodDTO(m.getName(), m.getSrcBeginLine(), m.getEndBeginLine()));
        return methodsDTO;
    }

    private static List<FieldDTO> createFieldsDTO(List<FieldInfo> fields){
        List<FieldDTO> fieldsDTO = new ArrayList<>();
        for (FieldInfo f: fields)
            fieldsDTO.add(new FieldDTO(f.getFieldName(), f.getFieldType(), f.getFieldModifiers().orElse("")));
        return  fieldsDTO;
    }
}
