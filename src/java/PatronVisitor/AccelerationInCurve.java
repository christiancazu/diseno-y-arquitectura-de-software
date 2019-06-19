package PatronVisitor;

import entidades.Auto;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class AccelerationInCurve implements Visitor {

    private double horsePower;
    private double frictionFactor;

    public AccelerationInCurve(double horsePower, double frictionFactor) {
        this.horsePower = horsePower;
        this.frictionFactor = frictionFactor;
    }        
    
    @Override
    public double visit(Auto auto) {
        return (auto.getPeso() - horsePower) - frictionFactor;
    }

}
