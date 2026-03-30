package com.example.movie;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final String id;
    private final String customer;
    private final String showId;
    private final List<Seat> seats;
    private final int amount;

    public Booking(String id, String customer, String showId,
                   List<Seat> seats, int amount) {
        this.id = id;
        this.customer = customer;
        this.showId = showId;
        this.seats = new ArrayList<>(seats);
        this.amount = amount;
    }

    public String getId()         { return id; }
    public String getCustomer()   { return customer; }
    public String getShowId()     { return showId; }
    public List<Seat> getSeats()  { return seats; }
    public int getAmount()        { return amount; }

    @Override
    public String toString() {
        return "Booking{" + id
                + ", customer=" + customer
                + ", show=" + showId
                + ", seats=" + seats
                + ", total=Rs " + amount + "}";
    }
}
