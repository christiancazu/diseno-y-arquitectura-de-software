package acciones;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public abstract class PeliculaAccion {
    
    public abstract RequestDispatcher ejecutar();
    
    public static PeliculaAccion getAccion(HttpServletRequest request) {

        // when pathInfo is /peliculas
        if (request.getPathInfo() == null)  return new ListarPeliculasAccion(request);
        // when pathInfo is /peliculas/*
        else {}

        return null;
    }
}
