package utils.dto;

import java.util.LinkedList;
import java.util.List;

//TODO: complete javadoc
/**
 *
 * @param name
 * @param path
 * @param methods
 * @param fields
 */
public record ClassInterfaceDTO(String name, String path, List<MethodDTO> methods, List<FieldDTO> fields) {
    /**
     *
     * @param name
     * @param path
     * @param methods
     */
    public ClassInterfaceDTO(String name, String path, List<MethodDTO> methods) {
        this(name, path, methods, new LinkedList<>());
    }
}
