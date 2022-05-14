package utils.dto;

import java.util.List;

//TODO: complete javadoc

/**
 *
 * @param mainClass
 * @param packages
 */
public record ProjectDTO(ClassInterfaceDTO mainClass, List<PackageDTO> packages) {
    /**
     *
     * @param name
     * @return
     */
    ClassInterfaceDTO getClass(String name) {
        final ClassInterfaceDTO[] classDTO = new ClassInterfaceDTO[1];
        classDTO[0] = null;
        this.packages.forEach(p -> p.classes().forEach(c -> {
            if (c.name().equals(name)) classDTO[0] = c;
        }));
        return classDTO[0];
    }
}
