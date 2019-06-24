package acciones;

import dao.IGeneroDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Genero;
import entidades.Pelicula;
import java.io.IOException;
import java.util.List;
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
        request.setAttribute("success", false);
        try {
            // int idPelicula = Integer.parseInt(request.getParameter("id"));
            String nombrePelicula = request.getParameter("nombre");
            String descripcionPelicula = request.getParameter("descripcion");
            int generoPelicula = Integer.parseInt(request.getParameter("genero"));

            Part imagenPelicula = request.getPart("imagen");

            String imagenPeliculaGenerated = FileManager.generateFullFileName(imagenPelicula);

            Pelicula pelicula = new Pelicula();
            pelicula.setNombre(nombrePelicula);
            pelicula.setDescripcion(descripcionPelicula);
            pelicula.setGenero(new Genero(generoPelicula));
            pelicula.setImagen(imagenPeliculaGenerated);

            IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();

            boolean isPeliculaRegistered = iPeliculaDAO.crear(pelicula);

            if (isPeliculaRegistered) {
                FileManager.save(imagenPelicula, imagenPeliculaGenerated);
                request.setAttribute("success", true);
            }
        } catch (IOException ex) {
            Logger.getLogger(RegistrarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(RegistrarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarPeliculaAccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // recall methodGet to set context to page again
            methodGet();
        }
        
    }
    
}
