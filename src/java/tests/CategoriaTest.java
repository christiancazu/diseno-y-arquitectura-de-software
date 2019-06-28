package tests;


import daoImpl.CategoriaDAO;
import entidades.Categoria;
import entidades.Producto;
import java.util.List;
import servicesImpl.CategoriaService;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class CategoriaTest {
    
    private static final CategoriaService categoriaService = new CategoriaService();
    
    public static void main(String[] args) {
               
        // getAll();
        // getById(6);
        // create();ç
        // update();
        //delete(4);
    }

    private static void getAll() {
        List<Categoria> categorias = categoriaService.findAll();
            
        categorias.stream().forEach((t) -> {
            System.out.println(t.getNombre());
        });
    }

    private static void getById(int id) {
        Categoria categoria = categoriaService.findById(id);
        
        System.out.println(categoria);
    }
    
    private static void create() {
        Categoria categoria = new Categoria();
        categoria.setNombre("dddddd");
        boolean result = categoriaService.create(categoria);
        
        System.out.println(result);
    }

    private static void update() {
        Categoria categoriax = new Categoria();
        categoriax.setId(3);
        categoriax.setNombre("aaaaaa");
        
        Categoria categoria = categoriaService.update(categoriax);
        System.out.println(categoria);    
    }

    private static void delete(int id) {
        System.out.println(categoriaService.deleteById(id));
    }
    
}
