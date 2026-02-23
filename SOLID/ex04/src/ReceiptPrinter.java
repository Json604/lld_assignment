import java.util.*;

public class ReceiptPrinter {
    public static void print(BookingRequest req, Money monthly, Money deposit) {
        System.out.println("--- Hostel Booking Receipt ---");
        System.out.println("Room Type  : " + LegacyRoomTypes.nameOf(req.roomType));
        System.out.println("Add-ons    : " + formatAddOns(req.addOns));
        System.out.println("Monthly Fee: Rs." + monthly);
        System.out.println("Deposit    : Rs." + deposit);
        System.out.println("Due Now    : Rs." + monthly.plus(deposit));
    }

    private static String formatAddOns(List<AddOn> addOns) {
        if (addOns.isEmpty()) return "None";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addOns.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(addOns.get(i).name().toLowerCase());
        }
        return sb.toString();
    }
}
