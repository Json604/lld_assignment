public class Demo01 {
    public static void main(String[] args) {
        FakeDb db = new FakeDb();
        OnboardingPrinter printer = new OnboardingPrinter();
        OnboardingService service = new OnboardingService(db, printer);

        printer.printHeader();
        String input = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        service.register(input);

        printer.printDbDump(db);
    }
}
