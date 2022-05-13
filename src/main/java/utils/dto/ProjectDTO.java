package utils.dto;

import java.util.List;

public record ProjectDTO(ClassInterfaceDTO mainClass, List<PackageDTO> packages) {
    ClassInterfaceDTO getClass(String name) {
        final ClassInterfaceDTO[] classDTO = new ClassInterfaceDTO[1];
        classDTO[0] = null;
        this.packages.forEach(p -> p.classes().forEach(c -> {
            if (c.name().equals(name)) classDTO[0] = c;
        }));
        return classDTO[0];
    }
}
