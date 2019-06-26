package acciones;

import dao.AlumnoDAO;
import entidades.Alumno;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class RegistrarAlumnoAccion extends AlumnoAccion {

    private final HttpServletRequest request;

    public RegistrarAlumnoAccion(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public RequestDispatcher ejecutar() {
        // getting current request method
        switch (request.getMethod()) {
            /*case "GET":
                methodGet();
                break;*/
            case "POST":
                methodPost();
                break;
        }
        return request.getRequestDispatcher("/pages/registrarAlumno.jsp");
    }

    private void methodPost() {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = 0;

        try {
            edad = Integer.parseInt(request.getParameter("edad"));

            Alumno alumno = new Alumno(nombre, apellido, edad);

            AlumnoDAO alumnoDAO = new AlumnoDAO();

            request.setAttribute("success", alumnoDAO.store(alumno));
        } catch (NumberFormatException e) {
            request.setAttribute("success", false);
        }
    }

}
