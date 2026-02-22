import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu;
    private final PricingService pricing;
    private final InvoicePrinter invoicePrinter;
    private final InvoiceStore store;

    public CafeteriaSystem(Map<String, MenuItem> menu, PricingService pricing,
                           InvoicePrinter invoicePrinter, InvoiceStore store) {
        this.menu = menu;
        this.pricing = pricing;
        this.invoicePrinter = invoicePrinter;
        this.store = store;
    }

    public void processOrder(String invoiceNo, String custType, List<OrderLine> lines) {
        double sub = pricing.subtotal(lines, menu);
        double taxPct = pricing.getTaxPercent(custType);
        double tax = pricing.calcTax(sub, taxPct);
        double disc = pricing.calcDiscount(custType, sub, lines.size());
        double total = sub + tax - disc;

        String text = invoicePrinter.format(invoiceNo, lines, menu, sub, taxPct, tax, disc, total);
        System.out.println(text);

        store.save(invoiceNo, text);
        System.out.println("Saved invoice: " + invoiceNo + " (lines=" + store.countLines(invoiceNo) + ")");
    }
}
