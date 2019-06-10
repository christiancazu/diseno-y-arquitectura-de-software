package acciones;

import dao.IGeneroDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Pelicula;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import utils.MultipartResolver;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class ActualizarPeliculaAccion extends PeliculaAccion {

    private final DAOFabrica subFabrica;
    private final IGeneroDAO iGeneroDAO;
    private final IPeliculaDAO iPeliculaDAO;
    private final HttpServletRequest request;

    private final String PATH_ACTUALIZAR_PELICULA = "/WEB-INF/pages/actualizarPelicula.jsp";

    public ActualizarPeliculaAccion(HttpServletRequest request) {

        subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        iGeneroDAO = subFabrica.getGeneroDAO();
        iPeliculaDAO = subFabrica.getPeliculaDAO();
        this.request = request;
    }

    @Override
    public RequestDispatcher ejecutar() {        
        switch (request.getMethod()) {
            case "GET":
                methodGet();
                break;
            case "POST":
                methodPost();
                break;
        }
        return request.getRequestDispatcher(PATH_ACTUALIZAR_PELICULA);
    }

    private void methodGet() {
        try {
            int idPelicula = Integer.parseInt(request.getParameter("id"));
            Pelicula pelicula = iPeliculaDAO.getById(idPelicula);

            setContextToRequest(pelicula);
        } catch (Exception ex) {
            Logger.getLogger(ActualizarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void methodPost() {
        try {
            HashMap<String, Object> requestResolved = MultipartResolver.resolveForm(request);

            Pelicula peliculaResolved = (Pelicula) requestResolved.get("pelicula");
            FileItem fileResolved = (FileItem) requestResolved.get("file");
            
            // keeping the Pelicula to delete to get his Image String PATH that will be deleted in server
            Pelicula peliculaToDelete = iPeliculaDAO.getById(peliculaResolved.getId());
            
            if (requestResolved != null) {
                boolean isPeliculaRegistered = iPeliculaDAO.actualizar(peliculaResolved);

                if (isPeliculaRegistered && fileResolved != null) {      
                    MultipartResolver.saveFileInServer(fileResolved, peliculaResolved.getImagen());
                    MultipartResolver.deleteFileInServer(peliculaToDelete.getImagen());

                    // forcing the delay to complete the load on the server to be rendered properly
                    makeDelay(2000);
                }
                request.setAttribute("success", true);
                setContextToRequest(iPeliculaDAO.getById(peliculaResolved.getId()));
            }
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(ActualizarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        }      
        // #TODO: error handling when Pelicula is not saved(priority low)
    }

    /**
     * set Pelicula to the view as context
     *
     * @param request
     * @param response
     * @param Pelicula asked in parameter request
     * @throws Exception
     */
    private void setContextToRequest(Pelicula pelicula) throws Exception {
        
        request.setAttribute("pelicula", pelicula);
        request.setAttribute("generos", iGeneroDAO.getAll());
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
