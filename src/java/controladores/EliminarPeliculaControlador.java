package controladores;

import dao.IPeliculaDAO;
import dao.fabrica.DAOFabrica;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MultipartResolver;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
@WebServlet(name = "EliminarPeliculaControlador", urlPatterns = {"/eliminarPelicula"})
public class EliminarPeliculaControlador extends HttpServlet {

    private DAOFabrica subFabrica;
    private IPeliculaDAO iPeliculaDAO;

    /**
     * servlet objects dao's initialization
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();

        subFabrica = DAOFabrica.getDAOFabrica(DAOFabrica.MYSQL);
        iPeliculaDAO = subFabrica.getPeliculaDAO();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            String peliculaImageNameToDelete = iPeliculaDAO.getById(id).getImagen();
            boolean isPeliculaDeleted = iPeliculaDAO.eliminar(id);

            if (isPeliculaDeleted) {
                MultipartResolver.deleteFileInServer(peliculaImageNameToDelete);
                request.setAttribute("success", true);
            }
        } catch (Exception ex) {
            request.setAttribute("success", false);
            Logger.getLogger(EliminarPeliculaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        ListarPeliculasControlador lpc = new ListarPeliculasControlador();
        lpc.init();
        lpc.doGet(request, response);
    }
}
