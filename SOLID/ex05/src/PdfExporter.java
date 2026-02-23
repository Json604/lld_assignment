import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    // fake pdf export with a size limit
    private static final int MAX_LEN = 20;

    @Override
    public ExportResult export(ExportRequest req) {
        if (req.body != null && req.body.length() > MAX_LEN) {
            return ExportResult.error("PDF cannot handle content > " + MAX_LEN + " chars");
        }
        String content = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", content.getBytes(StandardCharsets.UTF_8));
    }
}
