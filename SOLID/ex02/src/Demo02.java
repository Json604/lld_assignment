import java.util.*;

public class Demo02 {
    public static void main(String[] args) {
        // set up the menu items
        Map<String, MenuItem> menu = new HashMap<>();
        menu.put("VT", new MenuItem("VT", "Veg Thali", 80.0));
        menu.put("CF", new MenuItem("CF", "Coffee", 30.0));
        menu.put("SM", new MenuItem("SM", "Samosa", 20.0));

        TaxRules taxRules = new TaxRules();
        DiscountRules discRules = new DiscountRules();
        PricingService pricing = new PricingService(taxRules, discRules);
        InvoicePrinter printer = new InvoicePrinter();
        FileStore store = new FileStore();

        CafeteriaSystem system = new CafeteriaSystem(menu, pricing, printer, store);

        System.out.println("--- Cafeteria Billing System ---");

        // sample order
        List<OrderLine> order = new ArrayList<>();
        order.add(new OrderLine("VT", 1));
        order.add(new OrderLine("CF", 2));
        order.add(new OrderLine("SM", 3));

        system.processOrder("INV-5001", "student", order);
    }
}
