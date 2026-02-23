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
        // calculate all the pricing components
        double subtotal = pricing.subtotal(lines, menu);
        double taxPct = pricing.getTaxPercent(custType);
        double taxAmt = pricing.calcTax(subtotal, taxPct);
        double discAmt = pricing.calcDiscount(custType, subtotal, lines.size());
        double grandTotal = subtotal + taxAmt - discAmt;

        // format and print the invoice
        String invoiceText = invoicePrinter.format(invoiceNo, lines, menu, subtotal, taxPct, taxAmt, discAmt, grandTotal);
        System.out.println(invoiceText);

        // persist it
        store.save(invoiceNo, invoiceText);
        System.out.println("Invoice " + invoiceNo + " saved (" + store.countLines(invoiceNo) + " lines)");
    }
}
