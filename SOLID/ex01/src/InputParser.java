import java.util.*;

public class InputParser {
    // splits "key=val;key2=val2" into a map
    public static Map<String, String> parse(String raw) {
        Map<String, String> result = new HashMap<>();
        if (raw == null || raw.isBlank()) return result;
        for (String token : raw.split(";")) {
            String[] pair = token.split("=", 2);
            if (pair.length == 2) {
                result.put(pair[0].trim(), pair[1].trim());
            }
        }
        return result;
    }
}
