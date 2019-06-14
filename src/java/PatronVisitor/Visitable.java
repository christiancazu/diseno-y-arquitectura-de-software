package PatronVisitor;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface Visitable {
    
    public double accept(Visitor visitor);

}
