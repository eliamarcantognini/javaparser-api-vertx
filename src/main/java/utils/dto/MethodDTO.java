package utils.dto;

//TODO: complete javadoc

/**
 *
 * @param name
 * @param srcBeginLine
 * @param endBeginLine
 * @param modifiers
 */
public record MethodDTO(String name, int srcBeginLine, int endBeginLine, String modifiers) {
    public MethodDTO(String name, int srcBeginLine, int endBeginLine) {
        this(name, srcBeginLine, endBeginLine, "");
    }
}
