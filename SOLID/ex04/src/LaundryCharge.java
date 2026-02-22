public class LaundryCharge implements AddOnCharge {
    @Override
    public Money price() { return new Money(500); }
}
