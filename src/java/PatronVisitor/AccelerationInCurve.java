package PatronVisitor;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class AccelerationInCurve implements Visitor {

    private double horsePower;
    private double ejes = 2;
    private double frictionFactor;

    public AccelerationInCurve(double horsePower, double frictionFactor) {
        this.horsePower = horsePower;
        this.frictionFactor = frictionFactor;
    }        
    
    @Override
    public double visit(AutoAcceleration auto) {
        return (horsePower / ejes) - frictionFactor;
    }

}
