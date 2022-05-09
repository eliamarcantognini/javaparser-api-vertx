package dto;

import java.util.List;

public record ClassInterfaceDTO(String name, String path, List<MethodDTO> methods, List<FieldDTO> fields) {
    public ClassInterfaceDTO(String name, String path, List<MethodDTO> methods) {
        this(name, path, methods, null);
    }
}
