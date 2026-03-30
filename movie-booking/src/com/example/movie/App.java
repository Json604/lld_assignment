package com.example.movie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Movie m1 = new Movie("The Dark Knight", 152);
        Movie m2 = new Movie("Dune: Part Two", 166);

        // hall 1 layout: rows 1-3, varying categories
        List<Seat> hall1 = new ArrayList<>();
        hall1.add(new Seat("R1", 1, 1, SeatCategory.STANDARD));
        hall1.add(new Seat("R2", 1, 2, SeatCategory.STANDARD));
        hall1.add(new Seat("R3", 1, 3, SeatCategory.STANDARD));
        hall1.add(new Seat("P1", 2, 1, SeatCategory.PREMIUM));
        hall1.add(new Seat("P2", 2, 2, SeatCategory.PREMIUM));
        hall1.add(new Seat("V1", 3, 1, SeatCategory.VIP));
        hall1.add(new Seat("V2", 3, 2, SeatCategory.VIP));

        Show s1 = new Show("SH1", m1, "Audi-1",
                LocalDateTime.of(2025, 7, 15, 19, 30), hall1);
        Show s2 = new Show("SH2", m2, "Audi-2",
                LocalDateTime.of(2025, 7, 15, 21, 0), createHall2());

        BookingService svc = new BookingService();

        System.out.println("=== Movie Ticket Booking ===\n");
        System.out.println("Show: " + s1);
        System.out.println("Open seats: " + svc.availableSeats(s1));

        // Ravi books R1 and P1
        System.out.println("\n-- Ravi books R1, P1 --");
        Booking b1 = svc.bookSeats(s1, Arrays.asList("R1", "P1"), "Ravi");

        // Sneha books V1
        System.out.println("\n-- Sneha books V1 --");
        Booking b2 = svc.bookSeats(s1, Arrays.asList("V1"), "Sneha");

        System.out.println("\nOpen seats now: " + svc.availableSeats(s1));

        // Amit tries to book R1 (taken)
        System.out.println("\n-- Amit tries R1 (already taken) --");
        try {
            svc.bookSeats(s1, Arrays.asList("R1"), "Amit");
        } catch (IllegalStateException ex) {
            System.out.println("Rejected: " + ex.getMessage());
        }

        // Ravi cancels
        System.out.println("\n-- Ravi cancels " + b1.getId() + " --");
        svc.cancelBooking(b1.getId());
        System.out.println("Open seats after cancel: " + svc.availableSeats(s1));

        // Amit rebooks R1
        System.out.println("\n-- Amit books R1 after cancellation --");
        svc.bookSeats(s1, Arrays.asList("R1"), "Amit");

        // second show booking
        System.out.println("\n-- Show 2: " + s2 + " --");
        svc.bookSeats(s2, Arrays.asList("X1", "X2"), "Priya");
    }

    private static List<Seat> createHall2() {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat("X1", 1, 1, SeatCategory.STANDARD));
        seats.add(new Seat("X2", 1, 2, SeatCategory.STANDARD));
        seats.add(new Seat("Y1", 2, 1, SeatCategory.PREMIUM));
        seats.add(new Seat("Z1", 3, 1, SeatCategory.VIP));
        return seats;
    }
}
