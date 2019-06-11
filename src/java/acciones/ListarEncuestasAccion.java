package acciones;

import dao.IEncuestaDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import modelos.FullPelicula;
import utils.GenerateFullPelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class ListarEncuestasAccion extends EncuestaAccion {

    private final DAOFabrica subFabrica;
    private final IPeliculaDAO iPeliculaDAO;
    private final IEncuestaDAO iEncuestaDAO;
    
    private final HttpServletRequest request;
    
    private final String PATH_ENCUESTAS = "/WEB-INF/pages/encuestas.jsp";
    
    public ListarEncuestasAccion(HttpServletRequest request) {
        this.subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        this.iPeliculaDAO = subFabrica.getPeliculaDAO();
        this.iEncuestaDAO = subFabrica.getEncuestaDAO();
        this.request = request;
    }
    
    @Override
    public RequestDispatcher ejecutar() {
        System.out.println("request.getMethod() " + request.getMethod());
        switch (request.getMethod()) {
            case "GET":
            case "POST":
                methodGet();
                break;
        }
        return request.getRequestDispatcher(PATH_ENCUESTAS);
    }

    private void methodGet() {        
        try {
            setContextToRequest();
        } catch (Exception ex) {
            Logger.getLogger(ListarEncuestasAccion.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * set List<Pelicula> & List<Encuesta> encuestas to the view
     * 
     * @param request
     * @param response
     * @throws Exception 
     */
    private void setContextToRequest() throws Exception {

        List<Pelicula> peliculas = iPeliculaDAO.getAll();
        List<Encuesta> encuestas = iEncuestaDAO.getAll();
        
        List<FullPelicula> fullPeliculas = GenerateFullPelicula.assignFullDataToPeliculas(peliculas, encuestas);

        // assign data: Pelicula, likes, dislikes for each Pelicula in FullPelicula structure
        request.setAttribute("fullPeliculas", fullPeliculas);
    }
}
