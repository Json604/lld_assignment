import java.util.*;

public class Demo04 {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        Map<Integer, RoomPricing> rooms = new HashMap<>();
        rooms.put(LegacyRoomTypes.SINGLE, new SingleRoom());
        rooms.put(LegacyRoomTypes.DOUBLE, new DoubleRoom());
        rooms.put(LegacyRoomTypes.TRIPLE, new TripleRoom());
        rooms.put(LegacyRoomTypes.DELUXE, new DeluxeRoom());

        Map<AddOn, AddOnCharge> addOns = new HashMap<>();
        addOns.put(AddOn.MESS, new MessCharge());
        addOns.put(AddOn.LAUNDRY, new LaundryCharge());
        addOns.put(AddOn.GYM, new GymCharge());

        HostelFeeCalculator calc = new HostelFeeCalculator(rooms, addOns);

        BookingRequest req = new BookingRequest(
                LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        Money monthly = calc.computeMonthly(req);
        Money deposit = calc.deposit();

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        new FakeBookingRepo().save(bookingId, req, monthly, deposit);
    }
}
