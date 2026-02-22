public class GymCharge implements AddOnCharge {
    @Override
    public Money price() { return new Money(300); }
}
