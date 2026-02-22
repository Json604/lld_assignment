import java.util.*;

public class InvoicePrinter {

    public String format(String invoiceNo, List<OrderLine> lines, Map<String, MenuItem> menu,
                         double subtotal, double taxPct, double tax, double discount, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# " + invoiceNo + "\n");
        for (OrderLine ol : lines) {
            MenuItem item = menu.get(ol.itemId);
            double lineTotal = item.price * ol.qty;
            sb.append(String.format("- %s x%d = %.2f\n", item.name, ol.qty, lineTotal));
        }
        sb.append(String.format("Subtotal: %.2f\n", subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        sb.append(String.format("Discount: -%.2f\n", discount));
        sb.append(String.format("TOTAL: %.2f", total));
        return sb.toString();
    }
}
