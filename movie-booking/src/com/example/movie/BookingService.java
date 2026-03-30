package com.example.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    private final Map<String, Booking> registry;
    private int nextId;

    public BookingService() {
        this.registry = new HashMap<>();
        this.nextId = 1;
    }

    public Booking bookSeats(Show show, List<String> seatIds, String customer) {
        // validate all seats before modifying any state
        List<Seat> selected = new ArrayList<>();
        for (String sid : seatIds) {
            Seat seat = show.lookupSeat(sid);
            if (seat == null) {
                throw new IllegalArgumentException("No such seat " + sid + " in show " + show.getShowId());
            }
            if (seat.isReserved()) {
                throw new IllegalStateException("Seat " + sid + " is already reserved.");
            }
            selected.add(seat);
        }

        // mark reserved and tally price
        int total = 0;
        for (Seat s : selected) {
            s.reserve();
            total += s.getCategory().getPrice();
        }

        String bookingId = "BK-" + (nextId++);
        Booking booking = new Booking(bookingId, customer, show.getShowId(), selected, total);
        registry.put(bookingId, booking);

        System.out.println("Confirmed: " + booking);
        return booking;
    }

    public void cancelBooking(String bookingId) {
        Booking booking = registry.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("No booking found: " + bookingId);
        }
        for (Seat s : booking.getSeats()) {
            s.free();
        }
        registry.remove(bookingId);
        System.out.println("Cancelled " + bookingId + " — seats released.");
    }

    public List<Seat> availableSeats(Show show) {
        List<Seat> open = new ArrayList<>();
        for (Seat s : show.getSeats()) {
            if (!s.isReserved()) {
                open.add(s);
            }
        }
        return open;
    }
}
