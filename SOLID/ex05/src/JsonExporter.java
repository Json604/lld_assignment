import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // manually build json (no library needed for this simple case)
        String t = req.title != null ? req.title : "";
        String b = req.body != null ? req.body : "";
        String json = "{\n  \"title\": \"" + escapeJson(t) + "\",\n  \"body\": \"" + escapeJson(b) + "\"\n}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}
