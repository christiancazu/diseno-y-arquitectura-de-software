package controladores;

import dao.IEncuestaDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.FullPelicula;
import utils.GenerateFullPelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "EncuestaPeliculasControlador", urlPatterns = {"/encuestas"})
public class EncuestaPeliculasControlador extends HttpServlet {

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
            setContextToRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListarPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        Enumeration enumeration = request.getParameterNames();

        List<Encuesta> encuestas = new ArrayList<>();

        // assign request parameters to List<Encuesta>
        while (enumeration.hasMoreElements()) {
            String peliculaId = (String) enumeration.nextElement();

            Encuesta encuesta = new Encuesta();
            encuesta.setPelicula(new Pelicula(Integer.parseInt(peliculaId)));
            encuesta.setVoto(request.getParameter(peliculaId).charAt(0));

            encuestas.add(encuesta);
        }
        try {
            boolean encuestasCreadas = iEncuestaDAO.crearEncuestas(encuestas);
            
            request.setAttribute("success", encuestasCreadas);
            setContextToRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListarPeliculasControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * set List<Pelicula> & List<Encuesta> encuestas to the view
     * 
     * @param request
     * @param response
     * @throws Exception 
     */
    private void setContextToRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Pelicula> peliculas = iPeliculaDAO.getAll();
        List<Encuesta> encuestas = iEncuestaDAO.getAll();
        
        List<FullPelicula> fullPeliculas = GenerateFullPelicula.assignFullDataToPeliculas(peliculas, encuestas);

        // assign data: Pelicula, likes, dislikes for each Pelicula in FullPelicula structure
        request.setAttribute("fullPeliculas", fullPeliculas);
        
        request.getRequestDispatcher("WEB-INF/pages/encuestas.jsp").forward(request, response);
    }
}
