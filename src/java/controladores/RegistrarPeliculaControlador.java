package controladores;

import dao.IGeneroDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Pelicula;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import utils.MultipartResolver;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "RegistrarPeliculaControlador", urlPatterns = {"/registrarPelicula"})
public class RegistrarPeliculaControlador extends HttpServlet {

    private DAOFabrica subFabrica;
    private IGeneroDAO iGeneroDAO;

    /**
     * servlet objects dao's initialization
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();

        subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        iGeneroDAO = subFabrica.getGeneroDAO();
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
            request.setAttribute("generos", iGeneroDAO.getAll());
        } catch (Exception ex) {
            Logger.getLogger(RegistrarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/WEB-INF/pages/registrarPelicula.jsp").forward(request, response);
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

        try {
            HashMap<String, Object> requestResolved = MultipartResolver.resolveForm(request);

            Pelicula peliculaResolved = (Pelicula) requestResolved.get("pelicula");
            FileItem fileResolved = (FileItem) requestResolved.get("file");

            if (requestResolved != null) {
                IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();

                boolean isPeliculaRegistered = iPeliculaDAO.crear(peliculaResolved);

                if (isPeliculaRegistered && fileResolved != null) {
                    MultipartResolver.saveFileInServer(fileResolved, peliculaResolved.getImagen());
                }
            }
            request.setAttribute("success", true);
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(RegistrarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        doGet(request, response);
    }
}
