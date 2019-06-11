package acciones;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public abstract class EstadisticaAccion {

    public abstract RequestDispatcher ejecutar();

    public static EstadisticaAccion getAccion(HttpServletRequest request) {

        // when pathInfo is /encuestas
        if (request.getPathInfo() == null) {
            return new ListarEstadisticasAccion(request);
        } 
        return null;
    }    
}
