package acciones;

import dao.IGeneroDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Genero;
import entidades.Pelicula;
import java.util.HashMap;
import java.util.List;
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
public class RegistrarPeliculaAccion extends PeliculaAccion {

    private final DAOFabrica subFabrica;
    private final IGeneroDAO iGeneroDAO;
    private final HttpServletRequest request;

    private final String PATH_REGISTRAR_PELICULA = "/WEB-INF/pages/registrarPelicula.jsp";

    public RegistrarPeliculaAccion(HttpServletRequest request) {

        this.subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        this.iGeneroDAO = subFabrica.getGeneroDAO();
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
        return request.getRequestDispatcher(PATH_REGISTRAR_PELICULA);
    }

    private void methodGet() {
        try {
            List<Genero> generos = iGeneroDAO.getAll();
            request.setAttribute("generos", generos);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void methodPost() {

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
                request.setAttribute("success", true);
            }            
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(RegistrarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        }  
        // recall methodGet to set context to page again
        methodGet();
    }
}
