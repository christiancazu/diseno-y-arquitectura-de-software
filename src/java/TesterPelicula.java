import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Genero;
import entidades.Pelicula;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class TesterPelicula {
 
    static DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
    static IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();

    public static void main(String[] args) throws Exception {
        
        //getById();
        
        getAll();
                               
        /*crear();
        
        actualizar();
        
        eliminar();*/
    }

    private static void getById() throws Exception {
        Pelicula pelicula = iPeliculaDAO.getById(2);
        System.out.println(
                "peliculaId=" + pelicula.getId() + 
                " nombre=" + pelicula.getNombre() + 
                " descrip=" + pelicula.getDescripcion() +
                " imagen=" + pelicula.getImagen() +
                " generoId=" + pelicula.getGenero().getId());
    }

    private static void getAll() throws Exception {
        for (Pelicula pelicula : iPeliculaDAO.getAll()) {
            System.out.println(
                "peliculaId=" + pelicula.getId() + 
                " nombre=" + pelicula.getNombre() + 
                " descrip=" + pelicula.getDescripcion() +
                " imagen=" + pelicula.getImagen() +
                " generoId=" + pelicula.getGenero().getId());
        }
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
