import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Genero;
import entidades.Pelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class Tester {
 
    static DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
    static IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();

    public static void main(String[] args) throws Exception {
        
        getById();
        
        getAll();
                               
        crear();
        
        actualizar();
        
        eliminar();
    }

    private static void getById() throws Exception {
        System.out.println(iPeliculaDAO.getById(2));
    }

    private static void getAll() throws Exception {
        System.out.println(iPeliculaDAO.getAll());
    }    
    
    private static void crear() throws Exception {
        Genero genero = new Genero(2);
        
        Pelicula pelicula = new Pelicula();
        pelicula.setNombre("wwwwwwww");
        pelicula.setDescripcion("xxxxxx");
        pelicula.setImagen("yyyyyyy");
        pelicula.setGenero(genero);
        
        System.out.println(iPeliculaDAO.crear(pelicula));
    }
    
    private static void actualizar() throws Exception {
        Genero genero = new Genero(1);
        
        Pelicula pelicula = new Pelicula();
        pelicula.setId(6);
        pelicula.setNombre("gxxx");
        pelicula.setDescripcion("gxxx");
        pelicula.setImagen("xxxxx");
        pelicula.setGenero(genero);       
        
        System.out.println(iPeliculaDAO.actualizar(pelicula));
    }

    private static void eliminar() throws Exception {
        System.out.println(iPeliculaDAO.eliminar(11));
    }
}
