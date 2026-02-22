import java.util.*;

public class PricingService {
    private final TaxCalculator taxCalc;
    private final DiscountCalculator discCalc;

    public PricingService(TaxCalculator tc, DiscountCalculator dc) {
        this.taxCalc = tc;
        this.discCalc = dc;
    }

    public double subtotal(List<OrderLine> lines, Map<String, MenuItem> menu) {
        double total = 0;
        for (OrderLine ol : lines) {
            total += menu.get(ol.itemId).price * ol.qty;
        }
        return total;
    }

    public double getTaxPercent(String custType) {
        return taxCalc.taxPercent(custType);
    }

    public double calcTax(double subtotal, double taxPct) {
        return subtotal * taxPct / 100.0;
    }

    public double calcDiscount(String custType, double sub, int numLines) {
        return discCalc.discountAmount(custType, sub, numLines);
    }
}
