package controladores;

import dao.ICursoDAO;
import dao.fabrica.DAOFabrica;
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
@WebServlet(name = "ActualizarCursoControlador", urlPatterns = {"/actualizarCurso"})
public class ActualizarCursoControlador extends HttpServlet {

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
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        ICursoDAO dao = subFabrica.getCursoDAO();         
        
        request.setAttribute("curso", dao.getById(id));
                
        request.getRequestDispatcher("/WEB-INF/pages/actualizarCurso.jsp").forward(request, response);      
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
        String docente = request.getParameter("docente");
        String lugar = request.getParameter("lugar");
        
        int id;        
        Curso curso = new Curso();      

        try {
            id = Integer.parseInt(request.getParameter("id"));          
            
            curso.setNombre(nombre);
            curso.setDocente(docente);
            curso.setLugar(lugar); 
            curso.setId(id);

            DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
            ICursoDAO dao = subFabrica.getCursoDAO();

            if (dao.actualizar(curso) != -1) {
                request.setAttribute("success", true);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            request.setAttribute("success", false);
        }                     
        
        doGet(request, response);  
    }
}
