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

        // parse the raw input string into key-value pairs
        Map<String, String> data = InputParser.parse(rawInput);

        // validate the parsed data
        List<String> errors = StudentValidator.validate(data);
        if (!errors.isEmpty()) {
            System.out.println("Registration failed due to validation errors:");
            for (String err : errors) {
                System.out.println("  -> " + err);
            }
            return;
        }

        // generate id and create the record
        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(
            id, data.get("name"), data.get("email"),
            data.get("phone"), data.get("program")
        );

        repo.save(rec);
        printer.printCreated(id);
        printer.printSaved(repo.count());
        printer.printConfirmation(rec);
    }
}
