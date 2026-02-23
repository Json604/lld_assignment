public class IdUtil {
    public static String nextStudentId(int currentCount) {
        int next = currentCount + 1;
        String num = String.format("%04d", next);
        return "UNI-2026-" + num;
    }
}
