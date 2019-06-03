package controladores;

import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="ListarPeliculasControlador", urlPatterns={"/peliculas"})
public class ListarPeliculasControlador extends HttpServlet {
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();
        
        try {
            request.setAttribute("peliculas", iPeliculaDAO.getAll());
        } catch (Exception ex) {
            Logger.getLogger(ListarPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        request.getRequestDispatcher("WEB-INF/pages/peliculas.jsp").forward(request, response);
    }
}
