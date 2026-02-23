import java.util.*;

public class PricingService {
    private final TaxCalculator taxCalc;
    private final DiscountCalculator discCalc;

    public PricingService(TaxCalculator tc, DiscountCalculator dc) {
        this.taxCalc = tc;
        this.discCalc = dc;
    }

    // calculates the subtotal by summing up price * qty for each line
    public double subtotal(List<OrderLine> lines, Map<String, MenuItem> menu) {
        double sum = 0;
        for (OrderLine line : lines) {
            MenuItem item = menu.get(line.itemId);
            sum += item.price * line.qty;
        }
        return sum;
    }

    public double getTaxPercent(String custType) {
        return taxCalc.taxPercent(custType);
    }

    public double calcTax(double subtotal, double taxPct) {
        return subtotal * taxPct / 100.0;
    }

    public double calcDiscount(String custType, double subtotal, int numLines) {
        return discCalc.discountAmount(custType, subtotal, numLines);
    }
}
