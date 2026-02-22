import java.util.*;

public class Demo02 {
    public static void main(String[] args) {
        Map<String, MenuItem> menu = new HashMap<>();
        menu.put("VT", new MenuItem("VT", "Veg Thali", 80.0));
        menu.put("CF", new MenuItem("CF", "Coffee", 30.0));

        TaxRules taxRules = new TaxRules();
        DiscountRules discRules = new DiscountRules();
        PricingService pricing = new PricingService(taxRules, discRules);
        InvoicePrinter printer = new InvoicePrinter();
        FileStore store = new FileStore();

        CafeteriaSystem system = new CafeteriaSystem(menu, pricing, printer, store);

        System.out.println("=== Cafeteria Billing ===");

        List<OrderLine> order = new ArrayList<>();
        order.add(new OrderLine("VT", 2));
        order.add(new OrderLine("CF", 1));

        system.processOrder("INV-1001", "student", order);
    }
}
