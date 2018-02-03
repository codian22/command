package happyzoo.net.restcommand;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static JsonNode createJsonRootNode(String fileName) {

        try {
            return new ObjectMapper().readTree(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
