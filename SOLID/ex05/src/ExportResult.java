public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean success;
    public final String errorMsg;

    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.success = true;
        this.errorMsg = null;
    }

    private ExportResult(String errorMsg) {
        this.contentType = null;
        this.bytes = new byte[0];
        this.success = false;
        this.errorMsg = errorMsg;
    }

    public static ExportResult error(String msg) {
        return new ExportResult(msg);
    }
}
