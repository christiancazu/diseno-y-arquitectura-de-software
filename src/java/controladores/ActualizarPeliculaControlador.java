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

    private final DAOFabrica subFabrica;
    private final IGeneroDAO iGeneroDAO;
    private final IPeliculaDAO iPeliculaDAO;
    private Pelicula currentPelicula = null;

    public ActualizarPeliculaControlador() {
        this.subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        this.iGeneroDAO = subFabrica.getGeneroDAO();
        this.iPeliculaDAO = subFabrica.getPeliculaDAO();
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
            
            request.setAttribute("pelicula", currentPelicula);
            request.setAttribute("generos", iGeneroDAO.getAll());

            request.getRequestDispatcher("/WEB-INF/pages/actualizarPelicula.jsp").forward(request, response);
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
                IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();
                
                boolean isPeliculaRegistered = iPeliculaDAO.actualizar(peliculaResolved);

                if (isPeliculaRegistered && fileResolved != null) {
                    MultipartResolver.saveFileInServer(fileResolved, peliculaResolved.getImagen());
                    MultipartResolver.deleteFileInServer(currentPelicula.getImagen());

                    // forcing delay to load well the new image path in frontend
                    makeDelay(2000);
                }
                request.setAttribute("success", true);                
                request.setAttribute("pelicula", iPeliculaDAO.getById(currentPelicula.getId()));
                request.setAttribute("generos", iGeneroDAO.getAll());
            }
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(RegistrarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }  
        request.getRequestDispatcher("/WEB-INF/pages/actualizarPelicula.jsp").forward(request, response);
    }

    private void makeDelay(int delayTime) throws InterruptedException {
        Thread.sleep(delayTime);
    }
}
