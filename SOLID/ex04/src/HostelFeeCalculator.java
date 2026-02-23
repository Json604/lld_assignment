import java.util.Map;

public class HostelFeeCalculator {
    private final Map<Integer, RoomPricing> roomPricings;
    private final Map<AddOn, AddOnCharge> addOnCharges;

    // fixed deposit amount for all bookings
    private static final Money SECURITY_DEPOSIT = new Money(5000);

    public HostelFeeCalculator(Map<Integer, RoomPricing> roomPricings,
                               Map<AddOn, AddOnCharge> addOnCharges) {
        this.roomPricings = roomPricings;
        this.addOnCharges = addOnCharges;
    }

    public Money computeMonthly(BookingRequest req) {
        // start with the base room price
        Money total = roomPricings.get(req.roomType).basePrice();
        // add each selected add-on
        for (AddOn addon : req.addOns) {
            total = total.plus(addOnCharges.get(addon).price());
        }
        return total;
    }

    public Money deposit() {
        return SECURITY_DEPOSIT;
    }
}
