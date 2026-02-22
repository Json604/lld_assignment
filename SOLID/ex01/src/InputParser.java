import java.util.*;

public class InputParser {
    public static Map<String, String> parse(String raw) {
        Map<String, String> map = new HashMap<>();
        String[] parts = raw.split(";");
        for (String part : parts) {
            String[] kv = part.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        return map;
    }
}
