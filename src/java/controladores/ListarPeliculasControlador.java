package controladores;

import dao.IEncuestaDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GenerateFullPelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "ListarPeliculasControlador", urlPatterns = {"/peliculas"})
public class ListarPeliculasControlador extends HttpServlet {

    private DAOFabrica subFabrica;
    private IPeliculaDAO iPeliculaDAO;
    private IEncuestaDAO iEncuestaDAO;

    /**
     * servlet objects dao's initialization
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();

        subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        iPeliculaDAO = subFabrica.getPeliculaDAO();
        iEncuestaDAO = subFabrica.getEncuestaDAO();
    }    
    
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

        try {
            List<Pelicula> peliculas = iPeliculaDAO.getAll();
            List<Encuesta> encuestas = iEncuestaDAO.getAll();

            // assign data: Pelicula, likes, dislikes for each Pelicula in FullPelicula structure
            request.setAttribute("fullPeliculas", GenerateFullPelicula.assignFullDataToPeliculas(peliculas, encuestas));
        } catch (Exception ex) {
            Logger.getLogger(ListarPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("WEB-INF/pages/peliculas.jsp").forward(request, response);
    }    
}
