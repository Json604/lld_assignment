public class DeluxeRoom implements RoomPricing {
    @Override
    public Money basePrice() { return new Money(16000); }
}
