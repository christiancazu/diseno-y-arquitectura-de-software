package acciones;

import dao.AlumnoDAO;
import entidades.Alumno;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class ActualizarAlumnoAccion extends AlumnoAccion {

    private final HttpServletRequest request;

    public ActualizarAlumnoAccion(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public RequestDispatcher ejecutar() {
        switch (request.getMethod()) {
            case "GET":
                methodGet();
                break;
            case "POST":
                methodPost();
                break;
        }
        return request.getRequestDispatcher("/pages/actualizarAlumno.jsp");
    }

    private void methodGet() {
        int id = Integer.parseInt(request.getParameter("id"));

        AlumnoDAO alumnoDAO = new AlumnoDAO();

        Alumno alumno = alumnoDAO.getById(id);

        request.setAttribute("alumno", alumno);
    }

    private void methodPost() {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        
        int id, edad;
        Alumno alumno = new Alumno();
        
        try {
            id = Integer.parseInt(request.getParameter("id")) | 0;
            edad = Integer.parseInt(request.getParameter("edad")) | 0;
                        
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumno.setEdad(edad);

            AlumnoDAO alumnoDAO = new AlumnoDAO();
            
            Alumno alumnoActualizado = alumnoDAO.update(id, alumno); 
            
            if (alumnoActualizado != null) {
                request.setAttribute("success", true);
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(ActualizarAlumnoAccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        methodGet();
    }

}
