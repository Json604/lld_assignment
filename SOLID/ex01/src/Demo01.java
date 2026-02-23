public class Demo01 {
    public static void main(String[] args) {
        FakeDb db = new FakeDb();
        OnboardingPrinter printer = new OnboardingPrinter();
        OnboardingService service = new OnboardingService(db, printer);

        printer.printHeader();
        String input = "name=Kartikey;email=kartikey@university.edu;phone=9012345678;program=SWE";
        service.register(input);

        printer.printDbDump(db);
    }
}
