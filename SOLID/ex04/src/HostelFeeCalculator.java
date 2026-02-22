import java.util.Map;

public class HostelFeeCalculator {
    private final Map<Integer, RoomPricing> roomPricings;
    private final Map<AddOn, AddOnCharge> addOnCharges;
    private static final Money DEPOSIT = new Money(5000);

    public HostelFeeCalculator(Map<Integer, RoomPricing> roomPricings,
                               Map<AddOn, AddOnCharge> addOnCharges) {
        this.roomPricings = roomPricings;
        this.addOnCharges = addOnCharges;
    }

    public Money computeMonthly(BookingRequest req) {
        Money total = roomPricings.get(req.roomType).basePrice();
        for (AddOn a : req.addOns) {
            total = total.plus(addOnCharges.get(a).price());
        }
        return total;
    }

    public Money deposit() {
        return DEPOSIT;
    }
}
