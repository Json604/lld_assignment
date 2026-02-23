import java.util.*;

public class InvoicePrinter {

    // builds a formatted invoice string from the order details
    public String format(String invoiceNo, List<OrderLine> lines, Map<String, MenuItem> menu,
                         double subtotal, double taxPct, double tax, double discount, double total) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------- INVOICE ----------\n");
        sb.append("No: " + invoiceNo + "\n");
        sb.append("\n");
        for (OrderLine ol : lines) {
            MenuItem item = menu.get(ol.itemId);
            double cost = item.price * ol.qty;
            sb.append("  " + item.name + " (x" + ol.qty + ")  Rs." + String.format("%.2f", cost) + "\n");
        }
        sb.append("\n");
        sb.append("  Subtotal     : Rs." + String.format("%.2f", subtotal) + "\n");
        sb.append("  Tax @" + String.format("%.0f", taxPct) + "%     : Rs." + String.format("%.2f", tax) + "\n");
        sb.append("  Discount     : Rs." + String.format("%.2f", discount) + "\n");
        sb.append("-----------------------------\n");
        sb.append("  TOTAL        : Rs." + String.format("%.2f", total));
        return sb.toString();
    }
}
