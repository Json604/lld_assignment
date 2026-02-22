import java.util.List;

public class Demo03 {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        StudentProfile s = new StudentProfile(
                "23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);

        List<EligibilityRule> rules = List.of(
                new DisciplinaryRule(),
                new CgrRule(),
                new AttendanceRule(),
                new CreditsRule()
        );

        EligibilityEngine engine = new EligibilityEngine(rules);
        EligibilityEngineResult result = engine.evaluate(s);

        ReportPrinter printer = new ReportPrinter();
        printer.print(s, result);

        FakeEligibilityStore store = new FakeEligibilityStore();
        store.save(s.rollNo, result.status);
    }
}
