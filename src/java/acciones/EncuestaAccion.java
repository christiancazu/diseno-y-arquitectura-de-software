package acciones;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public abstract class EncuestaAccion {
    
    public abstract RequestDispatcher ejecutar();

    public static EncuestaAccion getAccion(HttpServletRequest request) {

        // when pathInfo is /encuestas
        if (request.getPathInfo() == null) {
            return new ListarEncuestasAccion(request);
        } // when pathInfo is /encuestas/*
        else {}
        return null;
    }
}
