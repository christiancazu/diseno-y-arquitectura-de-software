package PatronVisitor;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class AutoAcceleration implements Visitable {

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
        
}
