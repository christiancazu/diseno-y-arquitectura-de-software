package acciones;

import dao.IEncuestaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class RegistrarEncuestasAccion extends EncuestaAccion {

    private final DAOFabrica subFabrica;
    private final IEncuestaDAO iEncuestaDAO;
    
    private final HttpServletRequest request;
    
    public RegistrarEncuestasAccion(HttpServletRequest request) {

        this.subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        this.iEncuestaDAO = subFabrica.getEncuestaDAO();
        this.request = request;
    }
    
    @Override
    public RequestDispatcher ejecutar() {
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
        } catch (Exception ex) {
            request.setAttribute("success", false);  
            Logger.getLogger(RegistrarEncuestasAccion.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        // instancing object to set the view again
        ListarEncuestasAccion lea = new ListarEncuestasAccion(request);
        return lea.ejecutar();
    }

}
