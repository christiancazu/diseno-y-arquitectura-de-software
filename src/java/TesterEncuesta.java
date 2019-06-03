
import dao.IEncuestaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class TesterEncuesta {
    static DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
    static IEncuestaDAO iEncuestaDAO = subFabrica.getEncuestaDAO();

    public static void main(String[] args) throws Exception {
        
        getById();
        
        getAll();
                           
        crear();
        
        actualizar();
        
        eliminar();
    }

    private static void getById() throws Exception {
        Encuesta encuesta = iEncuestaDAO.getById(2);
        System.out.println(
                "encuestaId=" + encuesta.getId() + 
                " peliId=" + encuesta.getPelicula().getId() + 
                " peliName=" + encuesta.getPelicula().getNombre()+
                " voto=" + encuesta.getVoto());
    }

    private static void getAll() throws Exception {
        for (Encuesta encuesta : iEncuestaDAO.getAll()) {
            System.out.println(
                    "encuestaId=" + encuesta.getId() + 
                    " peliId=" + encuesta.getPelicula().getId() + 
                    " peliName=" + encuesta.getPelicula().getNombre()+
                    " voto=" + encuesta.getVoto());
        }
    }    
    
    private static void crear() throws Exception {
        Pelicula pelicula = new Pelicula(2);
        
        Encuesta encuesta = new Encuesta();
        encuesta.setPelicula(pelicula);
        encuesta.setVoto('S');
        
        System.out.println(iEncuestaDAO.crear(encuesta));
    }
    
    private static void actualizar() throws Exception {
        Pelicula pelicula = new Pelicula(4);
        
        Encuesta encuesta = new Encuesta();
        encuesta.setId(13);
        encuesta.setPelicula(pelicula);
        encuesta.setVoto('N');      
        
        System.out.println(iEncuestaDAO.actualizar(encuesta));
    }

    private static void eliminar() throws Exception {
        System.out.println(iEncuestaDAO.eliminar(13));
    }
}
