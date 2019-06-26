package acciones;

import dao.AlumnoDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class EliminarAlumnoAccion extends AlumnoAccion {

    private final HttpServletRequest request;

    public EliminarAlumnoAccion(HttpServletRequest request) {
        this.request = request;
    }
    
    @Override
    public RequestDispatcher ejecutar() {
        int id = Integer.parseInt(request.getParameter("id"));

        AlumnoDAO alumnoDAO = new AlumnoDAO();

        alumnoDAO.delete(id);
        
        ListarAlumnosAccion laa = new ListarAlumnosAccion(request);
        
        return laa.ejecutar();        
    }

}
