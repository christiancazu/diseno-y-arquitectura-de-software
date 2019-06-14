package PatronVisitor;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class AccelerationInPlane implements Visitor {

    private double horsePower;
    private double ejes = 2;

    public AccelerationInPlane(double horsePower) {
        this.horsePower = horsePower;
    }
    
    @Override
    public double visit(AutoAcceleration auto) {
        return horsePower / ejes;
    }

}
