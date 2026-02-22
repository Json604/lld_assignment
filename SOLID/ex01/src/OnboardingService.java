import java.util.*;

public class OnboardingService {
    private final StudentRepository repo;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repo, OnboardingPrinter printer) {
        this.repo = repo;
        this.printer = printer;
    }

    public void register(String rawInput) {
        printer.printInput(rawInput);

        Map<String, String> data = InputParser.parse(rawInput);
        List<String> errors = StudentValidator.validate(data);
        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(
            id, data.get("name"), data.get("email"),
            data.get("phone"), data.get("program")
        );

        printer.printCreated(id);
        repo.save(rec);
        printer.printSaved(repo.count());
        printer.printConfirmation(rec);
    }
}
