public class OnboardingPrinter {

    public void printHeader() {
        System.out.println("--- Student Onboarding System ---");
    }

    public void printInput(String raw) {
        System.out.println("Raw input => " + raw);
    }

    public void printCreated(String id) {
        System.out.println("Created student with id: " + id);
    }

    public void printSaved(int total) {
        System.out.println("Saved successfully. Count in DB: " + total);
    }

    public void printConfirmation(StudentRecord rec) {
        System.out.println("-- Confirmation --");
        System.out.println(rec);
    }

    public void printDbDump(StudentRepository repo) {
        System.out.println();
        System.out.println("== DB Contents ==");
        System.out.print(TextTable.render3(repo));
    }
}
