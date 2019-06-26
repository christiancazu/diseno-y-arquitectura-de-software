package acciones;

import dao.AlumnoDAO;
import entidades.Alumno;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class ListarAlumnosAccion  extends AlumnoAccion {

    private final HttpServletRequest request;
    
    ListarAlumnosAccion(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public RequestDispatcher ejecutar() {
        String text = request.getParameter("text");
        String filtro = request.getParameter("filtro");

        if(filtro == null){
            request.setAttribute("text", "");
            return request.getRequestDispatcher("/pages/alumnos.jsp");
        }
        
        AlumnoDAO alumnoDAO = new AlumnoDAO();        
        List<Alumno> alumnos = null;
        
        if (filtro.equals("todos")) {
            alumnos = alumnoDAO.getAll();
            text = "";
        } else {
            alumnos = alumnoDAO.getAllByNameSurname(text, filtro);            
        }
        request.setAttribute("alumnos", alumnos);
        
        if (alumnos != null && !alumnos.isEmpty()) {
            request.setAttribute("result", true);            
        } else {
            request.setAttribute("result", false);
        }

        request.setAttribute("text", text);
        request.setAttribute("filtro", filtro);

        return request.getRequestDispatcher("/pages/alumnos.jsp");   
    }
    

}
