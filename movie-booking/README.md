# Movie Ticket Booking System

## Class Diagram

```
+---------------------+
|      <<enum>>       |
|    SeatCategory     |
|---------------------|
| STANDARD (Rs 180)   |
| PREMIUM  (Rs 280)   |
| VIP      (Rs 450)   |
|---------------------|
| +getPrice(): int    |
+---------------------+
          |
          | has-a
          v
+---------------------+        +---------------------+
|        Seat         |        |       Movie         |
|---------------------|        |---------------------|
| -id: String         |        | -title: String      |
| -row: int           |        | -lengthMinutes: int |
| -col: int           |        +---------------------+
| -category           |                  |
| -reserved: boolean  |                  |
|---------------------|                  |
| +reserve()          |                  |
| +free()             |                  |
| +isReserved()       |                  |
+---------------------+                  |
          |                              |
          +-------------+   +-----------+
                        v   v
                 +---------------------+
                 |        Show         |
                 |---------------------|
                 | -showId: String     |
                 | -movie: Movie       |
                 | -hall: String       |
                 | -timing             |
                 | -seats: List<Seat>  |
                 |---------------------|
                 | +lookupSeat(id)     |
                 +---------------------+
                         |
                         | used by
                         v
+------------------------------------------------+
|               BookingService                   |
|------------------------------------------------|
| -registry: Map<String, Booking>                |
| -nextId: int                                   |
|------------------------------------------------|
| +bookSeats(show, seatIds, customer): Booking   |
| +cancelBooking(bookingId): void                |
| +availableSeats(show): List<Seat>              |
+------------------------------------------------+
                         |
                         | creates
                         v
+------------------------------------------------+
|                  Booking                       |
|------------------------------------------------|
| -id: String                                    |
| -customer: String                              |
| -showId: String                                |
| -seats: List<Seat>                             |
| -amount: int                                   |
+------------------------------------------------+
```

## Design Approach

### Entities
`Movie` holds metadata (title, length). `Show` represents a screening in a particular hall at a given time and owns the seat layout. `Seat` is mutable — it toggles between reserved and free. `Booking` is an immutable receipt of a confirmed reservation.

### Booking Flow
`BookingService.bookSeats()` uses a two-phase approach:
1. Validates every requested seat exists and is not yet reserved (fails atomically if any seat is taken).
2. Marks all seats as reserved and computes the total from each seat's category price.

This guarantees no partial reservations — either all requested seats are booked or none are.

### Cancellation
`cancelBooking()` frees all seats tied to a booking and removes the booking record, making those seats available again.

### Pricing
Each `Seat` carries a `SeatCategory` (STANDARD / PREMIUM / VIP) with a fixed price. A booking's total is the sum of its constituent seat prices.

## How to Build and Run

```bash
cd movie-booking
javac -d out src/com/example/movie/*.java
java -cp out com.example.movie.App
```
