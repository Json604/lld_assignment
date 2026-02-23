public class TextTable {
    // renders a simple 3-column table from the student repo
    public static String render3(StudentRepository db) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-16s %-12s %-8s%n", "ID", "NAME", "PROGRAM"));
        sb.append("--------------------------------------\n");
        for (StudentRecord r : db.all()) {
            sb.append(String.format("%-16s %-12s %-8s%n", r.id, r.name, r.program));
        }
        return sb.toString();
    }
}
