package dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class DTOParser {

    private DTOParser(){}

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Create a {@link ProjectDTO} from JSON string
     *
     * @param string the JSON
     * @return a {@link ProjectDTO}
     */
    public static ProjectDTO parseProjectDTO(final String string) {
        try {
            return mapper.readValue(string, ProjectDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ProjectDTO(null, null);
    }

    /**
     * Create a {@link PackageDTO} from JSON string
     *
     * @param string the JSON
     * @return a {@link PackageDTO}
     */
    public static PackageDTO parsePackageDTO(final String string) {
        try {
            return mapper.readValue(string, PackageDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new PackageDTO("ParseError", "", null, null);
    }

    /**
     * Create a {@link ClassInterfaceDTO} from JSON string
     *
     * @param string the JSON
     * @return a {@link ClassInterfaceDTO}
     */
    public static ClassInterfaceDTO parseClassInterfaceDTO(final String string) {
        try {
            return mapper.readValue(string, ClassInterfaceDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ClassInterfaceDTO("ParseError", "", null, null);
    }

    /**
     * Create a JSON String which represents the object passed
     *
     * @param dto the dto to be parsed
     * @return a JSON String
     */
    public static String parseString(final Object dto) {
        try {
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Create a formatted JSON String which represents the object passed
     *
     * @param dto the dto to be parsed
     * @return a formatted JSON String
     */
    public static String parseStringToPrettyJSON(final Object dto) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
