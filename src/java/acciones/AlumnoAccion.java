package acciones;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public abstract class AlumnoAccion {
    public abstract RequestDispatcher ejecutar();

    public static AlumnoAccion getAccion(HttpServletRequest request) {

        // when pathInfo is /alumnos
        if (request.getPathInfo() == null) {
            return new ListarAlumnosAccion(request);
        } // when pathInfo is /alumnos/*
        else {
            switch (request.getPathInfo()) {
                // managing get & post pathInfo cases
                case "/alumnos/registrar":
                case "/registrar":
                    return new RegistrarAlumnoAccion(request);
                case "/alumnos/actualizar":
                case "/actualizar":
                    //return new ActualizarAlumnoAccion(request);
                case "/eliminar":
                    //return new EliminarAlumnoAccion(request);
            }
        }
        return null;
    }
}
