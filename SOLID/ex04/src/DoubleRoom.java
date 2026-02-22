public class DoubleRoom implements RoomPricing {
    @Override
    public Money basePrice() { return new Money(15000); }
}
