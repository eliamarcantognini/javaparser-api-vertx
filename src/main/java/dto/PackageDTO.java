package dto;

import java.util.List;

public record PackageDTO(String name, String path, List<ClassInterfaceDTO> classes,
                         List<ClassInterfaceDTO> interfaces) {
}
