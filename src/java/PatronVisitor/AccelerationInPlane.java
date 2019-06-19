package PatronVisitor;

import entidades.Auto;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class AccelerationInPlane implements Visitor {

    private double horsePower;

    public AccelerationInPlane(double horsePower) {
        this.horsePower = horsePower;
    }
    
    @Override
    public double visit(Auto auto) {  
        return auto.getPeso() - horsePower;
    }

}
