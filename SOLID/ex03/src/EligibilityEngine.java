import java.util.List;

public class EligibilityEngine {
    private final List<EligibilityRule> rules;

    public EligibilityEngine(List<EligibilityRule> rules) {
        this.rules = rules;
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        // check rules in order, stop at first failure
        for (EligibilityRule rule : rules) {
            String reason = rule.check(s);
            if (reason != null) {
                return new EligibilityEngineResult("NOT_ELIGIBLE", List.of(reason));
            }
        }
        return new EligibilityEngineResult("ELIGIBLE", List.of());
    }
}
