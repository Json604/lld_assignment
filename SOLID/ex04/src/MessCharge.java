public class MessCharge implements AddOnCharge {
    @Override
    public Money price() { return new Money(1000); }
}
