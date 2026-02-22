public class OnboardingPrinter {

    public void printHeader() {
        System.out.println("=== Student Onboarding ===");
    }

    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printCreated(String id) {
        System.out.println("OK: created student " + id);
    }

    public void printSaved(int total) {
        System.out.println("Saved. Total students: " + total);
    }

    public void printConfirmation(StudentRecord rec) {
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }

    public void printDbDump(StudentRepository repo) {
        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(repo));
    }
}
