package controladores;

import dao.IAlumnoDAO;
import dao.fabrica.DAOFabrica;
import entidades.Alumno;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="ListarAlumnosControlador", urlPatterns={"/alumnos"})
public class ListarAlumnosControlador extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IAlumnoDAO dao = subFabrica.getAlumnoDAO();

        List<Alumno> alumnos = dao.getAll();
        request.setAttribute("alumnos", alumnos);
        request.getRequestDispatcher("/WEB-INF/pages/alumnos.jsp").forward(request, response);
    }
}
