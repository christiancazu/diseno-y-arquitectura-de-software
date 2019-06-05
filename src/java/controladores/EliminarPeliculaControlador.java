package controladores;

import dao.IEncuestaDAO;
import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import entidades.Encuesta;
import entidades.Pelicula;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.GenerateFullPelicula;
import utils.MultipartResolver;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name="EliminarPeliculaControlador", urlPatterns={"/eliminarPelicula"})
public class EliminarPeliculaControlador extends HttpServlet {
   
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        DAOFabrica subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        IPeliculaDAO iPeliculaDAO = subFabrica.getPeliculaDAO();
        IEncuestaDAO iEncuestaDAO = subFabrica.getEncuestaDAO();

        try {
            String peliculaImageNameToDelete = iPeliculaDAO.getById(id).getImagen();
            boolean isPeliculaDeleted = iPeliculaDAO.eliminar(id);
            
            if (isPeliculaDeleted) {
                MultipartResolver.deleteFileInServer(peliculaImageNameToDelete);
                
                List<Pelicula> peliculas = iPeliculaDAO.getAll();
                List<Encuesta> encuestas = iEncuestaDAO.getAll();

                // assign data: Pelicula, likes, dislikes for each Pelicula
                request.setAttribute("fullPeliculas", GenerateFullPelicula.assignFullDataToPeliculas(peliculas, encuestas));
                
                request.setAttribute("success", true);
            }
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(EliminarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/WEB-INF/pages/peliculas.jsp").forward(request, response);
    }
}
