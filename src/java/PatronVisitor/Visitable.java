package PatronVisitor;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface Visitable {
    
    public double acceptVisitor(Visitor visitor);

}
