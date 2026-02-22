public class TripleRoom implements RoomPricing {
    @Override
    public Money basePrice() { return new Money(12000); }
}
