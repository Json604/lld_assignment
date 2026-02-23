public class Demo05 {
    public static void main(String[] args) {
        System.out.println("--- Document Export Demo ---");

        ExportRequest req = new ExportRequest("Semester Report", SampleData.longBody());

        Exporter[] exporters = { new PdfExporter(), new CsvExporter(), new JsonExporter() };
        String[] labels = { "PDF", "CSV", "JSON" };

        for (int i = 0; i < exporters.length; i++) {
            ExportResult result = exporters[i].export(req);
            if (result.success) {
                System.out.println(labels[i] + " => OK (" + result.bytes.length + " bytes)");
            } else {
                System.out.println(labels[i] + " => FAILED: " + result.errorMsg);
            }
        }
    }
}
