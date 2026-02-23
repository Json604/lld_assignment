import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String cleanBody = req.body == null ? "" : cleanUp(req.body);
        String csv = "title,body\n" + req.title + "," + cleanBody + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    // strip characters that would break csv format
    private String cleanUp(String raw) {
        return raw.replace("\n", " ").replace(",", " ");
    }
}
