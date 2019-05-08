package Controlador;

import Entidad.Alumno;
import Modelo.ModelAlumno;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaAlumnoServlet
 */
@WebServlet("/listaAlumno")
public class ListaAlumnoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        // 1 Se obtiene una arraylist con los//alumnos de la base de datos
        ModelAlumno m = new ModelAlumno();
        List<Alumno> data = m.listaAlumno();
        // 2 Se guarda el arraylist en request con el alias alumnos
        // El request es una memoria temporal
        request.setAttribute("alumnos", data);
        // 3 Se reenvia el request al formulario
        request.getRequestDispatcher("/listaAlumno.jsp").forward(request, response);
    }
}
