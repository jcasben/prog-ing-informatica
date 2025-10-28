package cat.uib.eps.gein.aas.designlab1.ex1.channel;

public class MobileChannel implements ChannelStrategy {

    @Override
    public double calculateTotal(double subtotal, double tax) {
        return subtotal + (subtotal * tax) - 3;
    }
}
