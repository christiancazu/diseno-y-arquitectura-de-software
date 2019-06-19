package PatronVisitor;

import entidades.Auto;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public interface Visitor {
    
    public double visit(Auto auto);

}
