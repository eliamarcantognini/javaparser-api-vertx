package dto;

public record MethodDTO(String name, int srcBeginLine, int endBeginLine, String modifiers) {
    public MethodDTO(String name, int srcBeginLine, int endBeginLine) {
        this(name, srcBeginLine, endBeginLine, null);
    }
}
