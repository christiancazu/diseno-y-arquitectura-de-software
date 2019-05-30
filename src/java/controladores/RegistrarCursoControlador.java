package controladores;

import dao.fabrica.DAOFabrica;
import dao.ICursoDAO;
import entidades.Curso;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "RegistrarCursoControlador", urlPatterns = {"/registrarCurso"})
public class RegistrarCursoControlador extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/pages/registrarCurso.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("docente");
        String lugar = request.getParameter("lugar");     
        
        try {
            Curso curso = new Curso();
            curso.setNombre(nombre);
            curso.setDocente(apellido);
            curso.setLugar(lugar);

            DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
            ICursoDAO dao = subFabrica.getCursoDAO();

            request.setAttribute("success", dao.crear(curso) != -1);
        } catch (Exception e) {
            request.setAttribute("success", false);
        } 

        request.getRequestDispatcher("/pages/registrarCurso.jsp").forward(request, response);
    }
}
