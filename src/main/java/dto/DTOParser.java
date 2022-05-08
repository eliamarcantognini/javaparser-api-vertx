package dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class DTOParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ProjectDTO parseProjectDTO(final String string) {
        try {
            return mapper.readValue(string, ProjectDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ProjectDTO(null, null);
    }

    public static PackageDTO parsePackageDTO(final String string) {
        try {
            return mapper.readValue(string, PackageDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new PackageDTO("ParseError", "", null, null);
    }

    public static ClassInterfaceDTO parseClassInterfaceDTO(final String string) {
        try {
            return mapper.readValue(string, ClassInterfaceDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ClassInterfaceDTO("ParseError", "", null, null);
    }

    public static String parseString(final Object dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
