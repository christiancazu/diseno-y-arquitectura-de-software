package acciones;

import dao.IGeneroDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Genero;
import entidades.Pelicula;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import utils.FileManager;

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
            int idPelicula = Integer.parseInt(request.getParameter("id"));
            String nombrePelicula = request.getParameter("nombre");
            String descripcionPelicula = request.getParameter("descripcion");
            int generoPelicula = Integer.parseInt(request.getParameter("genero"));

            Part imagenPelicula = request.getPart("imagen");

            Pelicula pelicula = new Pelicula(idPelicula);
            pelicula.setNombre(nombrePelicula);
            pelicula.setDescripcion(descripcionPelicula);
            pelicula.setGenero(new Genero(generoPelicula));

            // if imagenPelicula exists in request
            if (!imagenPelicula.getSubmittedFileName().isEmpty()) {
                String imagenPeliculaGenerated = FileManager.generateFullFileName(imagenPelicula);
                pelicula.setImagen(imagenPeliculaGenerated);
            }
            IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();

            // keeping Pelicula image name that will deleted in server
            String peliculaImageNameToDelete = iPeliculaDAO.getById(pelicula.getId()).getImagen();

            boolean isPeliculaUpdated = iPeliculaDAO.actualizar(pelicula);

            // if Pelicula was updated in DB & exists in request 
            if (isPeliculaUpdated && !imagenPelicula.getSubmittedFileName().isEmpty()) {
                FileManager.delete(peliculaImageNameToDelete);
                FileManager.save(imagenPelicula, pelicula.getImagen());

                // forcing the delay to complete the load on the server to be rendered properly
                makeDelay(2000);
            }
            request.setAttribute("success", true);
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(RegistrarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // recall methodGet to set context to page again
            methodGet();
        }
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
