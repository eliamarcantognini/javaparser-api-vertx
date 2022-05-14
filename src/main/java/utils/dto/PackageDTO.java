package utils.dto;

import java.util.List;

//TODO: complete javadoc

/**
 *
 * @param name
 * @param path
 * @param classes
 * @param interfaces
 */
public record PackageDTO(String name, String path, List<ClassInterfaceDTO> classes,
                         List<ClassInterfaceDTO> interfaces) {
}
