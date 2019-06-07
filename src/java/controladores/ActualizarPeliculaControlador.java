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
@WebServlet(name = "ActualizarPeliculaControlador", urlPatterns = {"/actualizarPelicula"})
public class ActualizarPeliculaControlador extends HttpServlet {

    private DAOFabrica subFabrica;
    private IGeneroDAO iGeneroDAO;
    private IPeliculaDAO iPeliculaDAO;
    private Pelicula currentPelicula = null;

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
        iPeliculaDAO = subFabrica.getPeliculaDAO();
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
        int idPelicula = Integer.parseInt(request.getParameter("id"));

        try {
            currentPelicula = iPeliculaDAO.getById(idPelicula);

            setContextToRequest(request, response, currentPelicula);
        } catch (Exception ex) {
            Logger.getLogger(ActualizarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
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

        try {
            HashMap<String, Object> requestResolved = MultipartResolver.resolveForm(request);

            Pelicula peliculaResolved = (Pelicula) requestResolved.get("pelicula");
            FileItem fileResolved = (FileItem) requestResolved.get("file");

            if (requestResolved != null) {
                boolean isPeliculaRegistered = iPeliculaDAO.actualizar(peliculaResolved);

                if (isPeliculaRegistered && fileResolved != null) {
                    MultipartResolver.saveFileInServer(fileResolved, peliculaResolved.getImagen());
                    MultipartResolver.deleteFileInServer(currentPelicula.getImagen());

                    makeDelay(2000);
                }
                request.setAttribute("success", true);
            }
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(ActualizarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            setContextToRequest(request, response, iPeliculaDAO.getById(currentPelicula.getId()));
        } catch (Exception ex) {
            Logger.getLogger(ActualizarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * set Pelicula & List<Encuesta> to the view 
     * 
     * @param request
     * @param response
     * @param Pelicula asked in parameter request
     * @throws Exception 
     */
    private void setContextToRequest(HttpServletRequest request, HttpServletResponse response, Pelicula pelicula)
            throws Exception {

        request.setAttribute("pelicula", pelicula);
        request.setAttribute("generos", iGeneroDAO.getAll());

        request.getRequestDispatcher("WEB-INF/pages/actualizarPelicula.jsp").forward(request, response);
    }

    /**
     * forcing delay to load well the new image path in frontend
     *
     * @param delayTime
     * @throws InterruptedException
     */
    private void makeDelay(int delayTime) throws InterruptedException {
        Thread.sleep(delayTime);
    }
}
