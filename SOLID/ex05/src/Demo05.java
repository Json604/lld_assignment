public class Demo05 {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());

        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        printResult("PDF", pdf.export(req));
        printResult("CSV", csv.export(req));
        printResult("JSON", json.export(req));
    }

    private static void printResult(String label, ExportResult r) {
        if (r.success) {
            System.out.println(label + ": OK bytes=" + r.bytes.length);
        } else {
            System.out.println(label + ": ERROR: " + r.errorMsg);
        }
    }
}
