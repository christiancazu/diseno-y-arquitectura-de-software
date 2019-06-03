
import dao.IGeneroDAO;
import dao.fabrica.DAOFabrica;
import entidades.Genero;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class TesterGenero {

    static DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
    static IGeneroDAO iGeneroDAO = subFabrica.getGeneroDAO();

    public static void main(String[] args) throws Exception {
        
        getById();
        
        getAll();
                           
        crear();
        
        actualizar();
        
        eliminar();
    }

    private static void getById() throws Exception {
        Genero genero = iGeneroDAO.getById(2);
        System.out.println(
                    "generoId=" + genero.getId() +
                    " name=" + genero.getNombre());
    }

    private static void getAll() throws Exception {
        for (Genero genero : iGeneroDAO.getAll()) {
            System.out.println(
                    "generoId=" + genero.getId() +
                    " name=" + genero.getNombre());
        }
    }    
    
    private static void crear() throws Exception {        
        Genero genero = new Genero();
        genero.setNombre("qweqw");
        
        System.out.println(iGeneroDAO.crear(genero));
    }
    
    private static void actualizar() throws Exception {        
        Genero genero = new Genero();
        genero.setId(5);
        genero.setNombre("qqqqqqqqq");     
        
        System.out.println(iGeneroDAO.actualizar(genero));
    }

    private static void eliminar() throws Exception {
        System.out.println(iGeneroDAO.eliminar(5));
    }
}
