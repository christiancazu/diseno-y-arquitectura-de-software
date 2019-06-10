package acciones;

import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import utils.MultipartResolver;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class EliminarPeliculaAccion extends PeliculaAccion {

    private final DAOFabrica subFabrica;
    private final IPeliculaDAO iPeliculaDAO;
    private final HttpServletRequest request;
    
    public EliminarPeliculaAccion(HttpServletRequest request) {

        subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        iPeliculaDAO = subFabrica.getPeliculaDAO();
        this.request = request;
    }
    
    @Override
    public RequestDispatcher ejecutar() {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            String peliculaImageNameToDelete = iPeliculaDAO.getById(id).getImagen();
            boolean isPeliculaDeleted = iPeliculaDAO.eliminar(id);

            if (isPeliculaDeleted) {
                MultipartResolver.deleteFileInServer(peliculaImageNameToDelete);
                request.setAttribute("success", true);
            }
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(EliminarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        }

        // instancing object to set to the view again
        ListarPeliculasAccion lpa = new ListarPeliculasAccion(request);
        return lpa.ejecutar();
    }
}
