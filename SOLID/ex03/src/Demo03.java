import java.util.List;

public class Demo03 {
    public static void main(String[] args) {
        System.out.println("--- Placement Eligibility Check ---");

        // sample student data
        StudentProfile s = new StudentProfile(
                "23SWE2045", "Kartikey", 7.85, 78, 22, LegacyFlags.NONE);

        // set up all the eligibility rules
        List<EligibilityRule> rules = List.of(
                new DisciplinaryRule(),
                new CgrRule(),
                new AttendanceRule(),
                new CreditsRule()
        );

        EligibilityEngine engine = new EligibilityEngine(rules);
        EligibilityEngineResult result = engine.evaluate(s);

        // print the report
        ReportPrinter printer = new ReportPrinter();
        printer.print(s, result);

        // save result
        FakeEligibilityStore store = new FakeEligibilityStore();
        store.save(s.rollNo, result.status);
    }
}
