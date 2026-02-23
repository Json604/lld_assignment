import java.util.*;

public class Demo04 {
    public static void main(String[] args) {
        System.out.println("--- Hostel Fee Calculator ---");

        // register all room types
        Map<Integer, RoomPricing> rooms = new HashMap<>();
        rooms.put(LegacyRoomTypes.SINGLE, new SingleRoom());
        rooms.put(LegacyRoomTypes.DOUBLE, new DoubleRoom());
        rooms.put(LegacyRoomTypes.TRIPLE, new TripleRoom());
        rooms.put(LegacyRoomTypes.DELUXE, new DeluxeRoom());

        // register all add-on charges
        Map<AddOn, AddOnCharge> addOns = new HashMap<>();
        addOns.put(AddOn.MESS, new MessCharge());
        addOns.put(AddOn.LAUNDRY, new LaundryCharge());
        addOns.put(AddOn.GYM, new GymCharge());

        HostelFeeCalculator calc = new HostelFeeCalculator(rooms, addOns);

        // create a booking for a triple room with gym and mess
        BookingRequest req = new BookingRequest(
                LegacyRoomTypes.TRIPLE, List.of(AddOn.GYM, AddOn.MESS));

        Money monthly = calc.computeMonthly(req);
        Money deposit = calc.deposit();

        ReceiptPrinter.print(req, monthly, deposit);

        // save to fake repo
        String bookingId = "BK-" + (3000 + new Random(42).nextInt(500));
        new FakeBookingRepo().save(bookingId, req, monthly, deposit);
    }
}
